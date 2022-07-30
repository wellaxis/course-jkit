package com.witalis.jkit.usage.core.invoke.section.multithreading.content.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Desc: Thread utilities.
 * User: Wellaxis
 * Date: 4/30/2022
 */
@Slf4j
public final class ThreadUtils {
    public static final int TIMEOUT = 10;

    private ThreadUtils() {
    }

    public static void finalizeExecutor(ExecutorService executor, boolean logging) {
        if (logging) {
            log.info("|> Executor info: " + executor.getClass().getCanonicalName());
        }
        // termination
        try {
            if (logging) {
                log.info("|> Attempt to shutdown executor");
            }
            executor.shutdown();
            var termination = executor.awaitTermination(TIMEOUT, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("|> Tasks interrupted");
            Thread.currentThread().interrupt();
        } finally {
            if (!executor.isTerminated()) {
                log.error("|> Cancel non-finished tasks");
            }
            executor.shutdownNow();
            if (logging) {
                log.info("|> Shutdown finished");
            }
        }
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.error("|> Thread interrupted");
            Thread.currentThread().interrupt();
        }
    }
}
