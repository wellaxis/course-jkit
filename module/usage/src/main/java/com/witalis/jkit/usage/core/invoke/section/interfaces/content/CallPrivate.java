package com.witalis.jkit.usage.core.invoke.section.interfaces.content;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Desc: Interface with private methods
 * User: Wellaxis
 * Date: 21.12.2021
 */
// interface with private methods - since JDK 9
public interface CallPrivate {
    int DEF_BOUND = 50;

    default int randomInt() {
        return retrieveRandom();
    }

    static char randomChr() {
        return (char) getRandom();
    }

    // is invisible for extended classes -- for inner purposes only
    private int retrieveRandom() {
        return getRandom();
    }

    private static int getRandom() {
        var random = ThreadLocalRandom.current();
        return random.nextInt(DEF_BOUND);
    }
}
