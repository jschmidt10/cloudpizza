package com.github.jschmidt10.cloudpizza;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * The response that will be delivered from the MeatRestController.
 * <p>
 * Example:
 * <pre>
 *     {
 *         "meats": [ "meat1", "meat2" ],
 *         "hostname": "some host"
 *     }
 * </pre>
 */
class MeatResponse extends JsonResponse {

    private final Map<String, Object> response;

    /**
     * Constructs a new MeatResponse
     *
     * @param selectedMeats The meats to return
     * @param hostname      The hostname that produced this response
     */
    MeatResponse(Set<String> selectedMeats, String hostname) {
        this.response = new TreeMap<>();
        this.response.put("meats", selectedMeats);
        this.response.put("hostname", hostname);
    }

    @Override
    public Object getResponse() {
        return response;
    }
}
