package com.github.jschmidt10.cloudpizza.web;

import com.github.jschmidt10.cloudpizza.MeatProvider;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Set;
import java.util.TreeSet;

/**
 * A client for communicating with the meat service.
 */
class MeatServiceClient implements MeatProvider {

    private final String meatServiceUrl;
    private final String numMeats;

    /**
     * Constructs a new MeatServiceClient that will communicate with the given service url
     *
     * @param meatServiceUrl The meat service url
     * @param numMeats       The number of meats we want for our pizza
     */
    MeatServiceClient(String meatServiceUrl, String numMeats) {
        this.meatServiceUrl = meatServiceUrl;
        this.numMeats = numMeats;
    }

    /**
     * Attempts to fetch the meats for our pizza from the meat service.
     *
     * @return The meats returned from the meat service
     * @throws RuntimeException if any error occurs while fetching meats from the meat service
     */
    @Override
    public Set<String> getMeats() {
        try {
            com.mashape.unirest.http.HttpResponse<JsonNode> response = Unirest.get(meatServiceUrl)
                    .header("accept", "application/json")
                    .queryString("numMeats", numMeats)
                    .asJson();

            if (response.getStatus() < 200 || response.getStatus() >= 400) {
                throw new IllegalArgumentException("We got an HTTP/" + response.getStatus() + " from meat service.");
            }

            JSONObject obj = response.getBody().getObject();

            return toSet(obj.getJSONArray("meats"));
        } catch (UnirestException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private Set<String> toSet(JSONArray meats) {
        Set<String> meatSet = new TreeSet<>();

        for (Object meat : meats) {
            meatSet.add(meat.toString());
        }

        return meatSet;
    }
}
