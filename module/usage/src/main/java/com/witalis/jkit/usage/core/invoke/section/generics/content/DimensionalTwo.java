package com.witalis.jkit.usage.core.invoke.section.generics.content;

/**
 * Bounded wildcards
 */
public class DimensionalTwo<T extends Dimensional<T>> implements Dimensional<T> {
    public int x, y;

    public DimensionalTwo(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
