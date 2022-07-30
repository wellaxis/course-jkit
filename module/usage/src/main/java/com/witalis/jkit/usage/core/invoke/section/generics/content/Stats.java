package com.witalis.jkit.usage.core.invoke.section.generics.content;

import java.io.Serializable;

/**
 * Restrictions on base types - extends, & for each interface
 */
public class Stats<T extends Number & Comparable<T> & Serializable> {
    T[] nums;

    public Stats(T[] _nums) {
        super();
        this.nums = _nums;
    }

    public double average() {
        double sum = 0.0;
        for (int i = 0; i < this.nums.length; ++i) {
            sum += this.nums[i].doubleValue();
        }
        return sum / this.nums.length;
    }

    // argument the same type - for the same type
    public boolean sameAvg(Stats<T> ob) {
        return this.average() == ob.average();
    }

    // wildcard argument - for any class type
    public boolean wildAvg(Stats<?> ob) {
        return this.average() == ob.average();
    }
}