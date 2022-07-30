package com.witalis.jkit.usage.core.invoke.section.generics.content;

/**
 * Bounded wildcards
 */
public class DimensionalFour<T extends Dimensional<T>> extends DimensionalThree<T> implements Dimensional<T> {
    public int t;

    public DimensionalFour(int x, int y, int z, int t) {
        super(x, y, z);
        this.t = t;
    }
}
