package com.github.jschmidt10.cloudpizza;

/**
 * A service that knows how to provide a type of cheese.
 */
public interface CheeseProvider {

    /**
     * @return a pizza cheese
     */
    String getCheese();
}
