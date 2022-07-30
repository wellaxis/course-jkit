package com.witalis.jkit.usage.core.invoke.section.multithreading.content.lock;

/**
 * Desc: Synchronized content
 * User: Wellaxis
 * Date: 4/30/2022
 */
public class SynchronizedContent {
    public int count = 0;

    // non-synchronized method
    public void increment() {
        count++;
    }

    // synchronized method
    public synchronized void methodIncrement() {
        count++;
    }

    // synchronized block
    public void blockIncrement() {
        synchronized (this) {
            count++;
        }
    }
}
