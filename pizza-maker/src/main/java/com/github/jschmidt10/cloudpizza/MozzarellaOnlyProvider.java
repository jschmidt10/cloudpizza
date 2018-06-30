package com.github.jschmidt10.cloudpizza;

/**
 * Our initial cheese implementation can only make mozzarella cheese.
 */
public class MozzarellaOnlyProvider implements CheeseProvider {
    @Override
    public String getCheese() {
        return "mozzarella";
    }
}
