package com.github.jschmidt10.cloudpizza.web;

/**
 * Utility to parse the numMeats parameter that was received.
 */
class NumMeatsParser {

    /**
     * Constructs a new NumMeatsParser
     */
    NumMeatsParser() {
    }

    /**
     * Parses the numMeats parameter.
     * <p>
     * If the parameter is null, we assume they wanted 0 meats.
     *
     * @param numMeatsStr The numMeats parameter provided by the client
     * @return A valid number of meats as a String
     * @throws NumberFormatException if the parameter was not null and not a valid number
     */
    String parse(String numMeatsStr) {
        if (numMeatsStr == null) {
            return "1";
        } else {
            Integer.parseInt(numMeatsStr);
            return numMeatsStr;
        }
    }
}
