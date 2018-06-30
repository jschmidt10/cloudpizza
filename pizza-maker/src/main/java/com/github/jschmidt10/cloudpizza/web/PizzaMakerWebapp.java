package com.github.jschmidt10.cloudpizza.web;

import com.github.jschmidt10.cloudpizza.MozzarellaOnlyProvider;
import com.github.jschmidt10.cloudpizza.Pizza;
import com.github.jschmidt10.cloudpizza.PizzaMaker;
import com.lambdista.util.Try;
import io.javalin.Context;
import io.javalin.Javalin;
import org.eclipse.jetty.http.HttpStatus;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * A service for creating pizzas in the cloud.
 */
public class PizzaMakerWebapp {

    private final NumMeatsParser numMeatsParser;
    private final int port;
    private final String meatServiceUrl;

    private Javalin app;

    /**
     * Constructs a new PizzaMakerWebapp service.
     *
     * @param port           The port to listen on
     * @param meatServiceUrl The URL for the meat service
     */
    PizzaMakerWebapp(int port, String meatServiceUrl) {
        this.port = port;
        this.meatServiceUrl = meatServiceUrl;
        this.numMeatsParser = new NumMeatsParser();
    }

    /**
     * Starts this service
     */
    void start() {
        app = Javalin.start(port);
        app.get("/", this::makePizza);
    }

    private void makePizza(Context ctx) {
        Try<String> parseNumMeatsResult = Try.apply(() -> numMeatsParser.parse(ctx.queryParam("numMeats")));

        if (parseNumMeatsResult.isFailure()) {
            new HttpResponse(HttpStatus.BAD_REQUEST_400, parseNumMeatsResult).send(ctx);
            return;
        }

        String numMeats = parseNumMeatsResult.get();
        PizzaMaker pizzaMaker = new PizzaMaker(new MozzarellaOnlyProvider(), new MeatServiceClient(meatServiceUrl, numMeats));

        Try<Pizza> makePizzaResult = Try.apply(pizzaMaker::makePizza);
        if (makePizzaResult.isFailure()) {
            new HttpResponse(HttpStatus.INTERNAL_SERVER_ERROR_500, makePizzaResult).send(ctx);
            return;
        }

        Pizza pizza = makePizzaResult.get();
        String pizzaJson = new PizzaResponse(pizza, getHostname()).asJson();
        new HttpResponse(HttpStatus.OK_200, pizzaJson).send(ctx);
    }

    private String getHostname() {
        try {
            return InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            return "unknown host";
        }
    }

    /**
     * Stops this service
     */
    void close() {
        app.stop();
    }

    public static void main(String[] args) {
        final PizzaMakerWebapp pizzaMaker = new PizzaMakerWebapp(8080, "http://meats:8080/");
        Runtime.getRuntime().addShutdownHook(new Thread(pizzaMaker::close));
        pizzaMaker.start();
    }
}
