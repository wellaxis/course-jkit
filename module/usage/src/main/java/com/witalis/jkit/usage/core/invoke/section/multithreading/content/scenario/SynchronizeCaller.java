package com.witalis.jkit.usage.core.invoke.section.multithreading.content.scenario;

import com.witalis.jkit.usage.core.invoke.section.multithreading.content.scenario.SynchronizeCalling;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Synchronize caller
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Slf4j
@Data
public class SynchronizeCaller implements Runnable {
    private final String message;
    private final SynchronizeCalling calling;
    private final Thread thread;

    // synchronization - synchronized statement
    public SynchronizeCaller(SynchronizeCalling callIt, String msg, int priority) {
        this.calling = callIt;
        this.message = msg;
        this.thread = new Thread(this, "Caller Thread - " + msg);

        thread.setPriority(priority);
        log.info("Caller: ID = {} -> {}", thread.getId(), thread);
        thread.start();
    }

    public void run() {
        // synchronized statement
        synchronized (calling) {
            calling.call(message);
        }
        calling.info(false);
    }
}
