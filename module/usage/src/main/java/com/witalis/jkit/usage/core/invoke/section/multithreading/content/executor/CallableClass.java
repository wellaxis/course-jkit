package com.witalis.jkit.usage.core.invoke.section.multithreading.content.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Desc: Callable class
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Slf4j
public class CallableClass implements Callable<String> {
    public static final String PREFIX = "CLBL";
    private final int id;

    // callable with overridden thread names
    public CallableClass(final int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        final Thread currentThread = Thread.currentThread();
        final String originalName = currentThread.getName();
        currentThread.setName(PREFIX + "-" + id);
        // process
        try {
            log.info("  Done [callable]: {}", id);
            TimeUnit.MILLISECONDS.sleep(100);
            return "  Done: " + id;
        } catch (InterruptedException e) {
            log.error("Task interrupted");
            Thread.currentThread().interrupt();
        } finally {
            currentThread.setName(originalName);
        }
        return "Unexpected";
    }
}
