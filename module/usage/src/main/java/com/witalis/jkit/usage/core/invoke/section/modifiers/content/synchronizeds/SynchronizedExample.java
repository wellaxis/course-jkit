package com.witalis.jkit.usage.core.invoke.section.modifiers.content.synchronizeds;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Synchronized example
 * User: Wellaxis
 * Date: 4/12/2022
 */
@Slf4j
public class SynchronizedExample {
    public static final Object lock = new Object();
    private int counter;

    public SynchronizedExample(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    // synchronized method
    public synchronized void extraModification(int extra) {
        counter += extra;
        log.info("Synchronized [method]: {}", getCounter());
    }

    public void ultraModification(int ultra) {
        // synchronized block
        synchronized (lock) {
            counter += ultra;
            log.info("Synchronized [block]: {}", getCounter());
        }
    }
}
