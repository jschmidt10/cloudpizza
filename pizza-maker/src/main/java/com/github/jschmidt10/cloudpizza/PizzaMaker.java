package com.github.jschmidt10.cloudpizza;

/**
 * Makes pizzas.
 * <p>
 * This class needs to be injected with Strategy objects for fetching it's various ingredients.
 */
public class PizzaMaker {

    private final CheeseProvider cheeseProvider;
    private final MeatProvider meatProvider;

    /**
     * Constructs a new PizzaMaker with the given ingredient strategies.
     *
     * @param cheeseProvider The cheese providing strategy
     * @param meatProvider   The meat providing strategy
     */
    public PizzaMaker(CheeseProvider cheeseProvider, MeatProvider meatProvider) {
        this.cheeseProvider = cheeseProvider;
        this.meatProvider = meatProvider;
    }

    /**
     * Makes a brand new Pizza
     *
     * @return A fresh pizza
     */
    public Pizza makePizza() {
        return new Pizza(cheeseProvider.getCheese(), meatProvider.getMeats());
    }
}
