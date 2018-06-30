package com.github.jschmidt10.cloudpizza.web;

import com.lambdista.util.Try;
import io.javalin.Context;

/**
 * A utility for sending an HTTP response back to the client.
 */
class HttpResponse {

    private final int status;
    private final String body;

    /**
     * Constructs a new HttpResponse with the given status and using a failing Try's error as the body.
     * <p>
     * This method will fetch the exception's message and use it as the response body.
     *
     * @param status    The HTTP status
     * @param failedTry The Try that failed
     */
    HttpResponse(int status, Try<?> failedTry) {
        this(status, failedTry.failed().get().getMessage());
    }

    /**
     * Constructs a new HttpResponse with the given status and body.
     *
     * @param status The HTTP status
     * @param body   The response body
     */
    HttpResponse(int status, String body) {
        this.status = status;
        this.body = body;
    }

    /**
     * Sends the response using the Context
     *
     * @param ctx Context
     */
    void send(Context ctx) {
        ctx.status(status).result(body);
    }
}
