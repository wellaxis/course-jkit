package com.witalis.jkit.usage.core.invoke.section.generics.content;

/**
 * Bounded wildcards
 */
public class DimensionalThree<T extends Dimensional<T>> extends DimensionalTwo<T> implements Dimensional<T> {
    public int z;

    public DimensionalThree(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }
}