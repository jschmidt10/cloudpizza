package com.github.jschmidt10.cloudpizza;

import java.util.Set;

/**
 * A service that knows how to provide meats for a Pizza.
 */
public interface MeatProvider {

    /**
     * @return some pizza meats
     */
    Set<String> getMeats();
}
