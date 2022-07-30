package com.witalis.jkit.usage.core.invoke.section.multithreading.content.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Desc: Thread Callable (implements Callable)
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Slf4j
public class CallableThread implements Callable {
    public static final int DEF_INT = Integer.MAX_VALUE;

    @Override
    public Object call() throws Exception {
        try {
            TimeUnit.SECONDS.sleep(1);
            return DEF_INT;
        } catch (InterruptedException e) {
            log.error("Task interrupted");
            Thread.currentThread().interrupt();
        }
        return -1;
    }
}
