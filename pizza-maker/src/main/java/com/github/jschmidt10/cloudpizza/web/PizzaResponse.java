package com.github.jschmidt10.cloudpizza.web;

import com.github.jschmidt10.cloudpizza.Pizza;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The Response returned from the PizzaMakerWebapp
 */
class PizzaResponse {

    private final String json;

    /**
     * Constructs a new PizzaResponse with the given meats.
     *
     * @param pizza    The pizza that we made
     * @param hostname The host that made this pizza
     */
    PizzaResponse(Pizza pizza, String hostname) {
        this.json = buildJson(pizza, hostname);
    }

    private String buildJson(Pizza pizza, String hostname) {
        JSONObject pizzaObj = new JSONObject();
        pizzaObj.put("cheese", pizza.getCheese());
        pizzaObj.put("meats", new JSONArray(pizza.getMeats()));

        JSONObject obj = new JSONObject();
        obj.put("pizza", pizzaObj);
        obj.put("pizzaBuiltBy", hostname);
        return obj.toString();
    }

    /**
     * @return This response object serialized as JSON
     */
    String asJson() {
        return json;
    }
}
