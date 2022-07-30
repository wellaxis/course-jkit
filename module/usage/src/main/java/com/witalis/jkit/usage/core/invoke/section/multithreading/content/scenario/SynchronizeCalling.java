package com.witalis.jkit.usage.core.invoke.section.multithreading.content.scenario;

import lombok.extern.slf4j.Slf4j;
import static java.lang.Thread.sleep;

/**
 * Desc: Call It (synchronization)
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Slf4j
public class SynchronizeCalling {

    // synchronization - synchronized method
    public SynchronizeCalling() {
    }

    // synchronized method
    public synchronized void call(String message) {
        int indent = Thread.MAX_PRIORITY - Thread.currentThread().getPriority();

        var builder = new StringBuilder(" ".repeat(indent));
        builder.append("[");
        builder.append(message);
        try {
            sleep(1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        builder.append("]");

        log.info(builder.toString());
    }

    public void info(boolean output) {
        if (output) log.warn("* INFO: " + Thread.currentThread());
    }
}
