package com.github.jschmidt10.cloudpizza;

import java.util.Map;
import java.util.TreeMap;

/**
 * The response that will be delivered from the MeatRestController if an error occurs.
 * <p>
 * Example:
 * <pre>
 *     {
 *         "errorMessage": "The meat service cannot deliver veggies",
 *         "hostname": "some host"
 *     }
 * </pre>
 */
class ErrorResponse extends JsonResponse {

    private final Map<String, Object> response;

    /**
     * Constructs a new ErrorResponse
     *
     * @param errorMessage The error message
     * @param hostname     The hostname that produced this response
     */
    ErrorResponse(String errorMessage, String hostname) {
        this.response = new TreeMap<>();
        this.response.put("errorMessage", errorMessage);
        this.response.put("hostname", hostname);
    }

    @Override
    public Object getResponse() {
        return response;
    }
}
