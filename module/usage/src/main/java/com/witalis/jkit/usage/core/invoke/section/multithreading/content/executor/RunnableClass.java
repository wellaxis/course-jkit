package com.witalis.jkit.usage.core.invoke.section.multithreading.content.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Desc: Runnable class
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Slf4j
public class RunnableClass implements Runnable {
    public static final String PREFIX = "RNBL";
    private final int id;

    // runnable with overridden thread names
    public RunnableClass(final int id) {
        this.id = id;
    }

    @Override
    public void run() {
        final Thread currentThread = Thread.currentThread();
        final String originalName = currentThread.getName();
        currentThread.setName(PREFIX + "-" + id);
        try {
            log.info("  Done [runnable]: {}", id);
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            log.error("Task interrupted");
            Thread.currentThread().interrupt();
        } finally {
            currentThread.setName(originalName);
        }
    }
}
