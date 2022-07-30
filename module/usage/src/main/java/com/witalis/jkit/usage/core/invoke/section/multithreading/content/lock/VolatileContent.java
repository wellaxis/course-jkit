package com.witalis.jkit.usage.core.invoke.section.multithreading.content.lock;

/**
 * Desc: Volatile content
 * User: Wellaxis
 * Date: 18.01.2022
 */
public class VolatileContent {
    private volatile long l;

    public VolatileContent(long l) {
        this.l = l;
    }

    // atomic [READ]
    public long get() {
        return l;
    }

    // atomic [WRITE]
    public void set(long value) {
        l = value;
    }

    // non-atomic [INC]
    public void increment() {
        l++;
    }

    // non-atomic [DEC]
    public void decrement() {
        l--;
    }
}
