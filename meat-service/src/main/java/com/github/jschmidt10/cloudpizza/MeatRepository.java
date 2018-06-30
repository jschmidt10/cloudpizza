package com.github.jschmidt10.cloudpizza;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Houses all the meats we have available for pizza making.
 * <p>
 * This repository can only have meats selected at random.
 */
class MeatRepository {

    private static final List<String> MEATS = Collections.unmodifiableList(Arrays.asList("pepperoni", "sausage", "chicken", "ham"));

    /***
     * Constructs a new MeatRepository with the default hard-coded meats available.
     */
    MeatRepository() {
    }

    /**
     * Gets random meats from the repository.
     *
     * @param numMeats The number of desired meats. This must be between 0 and the total number of meats available.
     * @return Random meats
     * @throws IllegalArgumentException if numMeats is less than 0 or greater than the number of available meats
     */
    Set<String> getRandomMeats(int numMeats) {
        if (numMeats > MEATS.size()) {
            throw new IllegalArgumentException("We only have " + MEATS.size() + " meats but you asked for " + numMeats);
        } else if (numMeats < 0) {
            throw new IllegalArgumentException("You cannot have negative meats. That does not make sense.");
        }

        Set<String> selectedMeats = new TreeSet<>();
        for (int i = 0; i < numMeats; ++i) {
            while (selectedMeats.size() == i) {
                String randomMeat = MEATS.get(ThreadLocalRandom.current().nextInt(MEATS.size()));
                selectedMeats.add(randomMeat);
            }
        }
        return selectedMeats;
    }
}
