package com.witalis.jkit.usage.core.invoke.section.generics.content;

/**
 * Usual class, but generic method
 */
public class Comparator {

    public <T extends Comparable<T>, V extends T> boolean isIn (T x, V[] y) {
        for (var i = 0; i < y.length; i++) {
            if (x.equals(y[i])) return true;
        }
        return false;
    }
}