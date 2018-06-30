package com.github.jschmidt10.cloudpizza;

import java.util.Set;

/**
 * A fully made Pizza
 */
public class Pizza {

    private final String cheese;
    private final Set<String> meats;

    /**
     * Constructs a new pizza
     *
     * @param cheese The cheese to use
     * @param meats  Zero or meats
     */
    public Pizza(String cheese, Set<String> meats) {
        this.cheese = cheese;
        this.meats = meats;
    }

    /**
     * @return The cheese on this pizza
     */
    public String getCheese() {
        return cheese;
    }

    /**
     * @return The meats on this pizza
     */
    public Set<String> getMeats() {
        return meats;
    }
}
