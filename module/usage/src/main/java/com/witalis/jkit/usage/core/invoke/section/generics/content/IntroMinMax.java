package com.witalis.jkit.usage.core.invoke.section.generics.content;

/**
 * Generic interface
 */
public class IntroMinMax<T extends Comparable<T>> implements MinMax<T> {
    T[] values;

    public IntroMinMax(final T[] values) {
        super();
        this.values = values;
    }

    public T min() {
        T value = (T)this.values[0];
        for (int i = 1; i < this.values.length; ++i) {
            if (this.values[i].compareTo(value) < 0) {
                value = (T)this.values[i];
            }
        }
        return value;
    }

    public T max() {
        T value = (T)this.values[0];
        for (int i = 1; i < this.values.length; ++i) {
            if (this.values[i].compareTo(value) > 0) {
                value = (T)this.values[i];
            }
        }
        return value;
    }
}