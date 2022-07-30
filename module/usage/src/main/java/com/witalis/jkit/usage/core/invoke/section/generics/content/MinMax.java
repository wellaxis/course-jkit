package com.witalis.jkit.usage.core.invoke.section.generics.content;

/**
 * Generic Interface
 */
public interface MinMax<T extends Comparable<T>> {
    T min();
    T max();
}