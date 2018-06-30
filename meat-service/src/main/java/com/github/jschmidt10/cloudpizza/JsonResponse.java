package com.github.jschmidt10.cloudpizza;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A JSON response.
 */
abstract public class JsonResponse {

    private static final ObjectMapper MAPPER = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    /**
     * Gets the object that will be serialized as JSON.
     *
     * @return The object that should be serialized to JSON.
     */
    abstract Object getResponse();

    /**
     * Converts this response into a JSON string.
     *
     * @return response as JSON
     * @throws RuntimeException if any JSON parsing errors occur
     */
    final String asJson() {
        try {
            return MAPPER.writeValueAsString(getResponse());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("An error occurred while parsing the JSON of " + this, e);
        }
    }

}
