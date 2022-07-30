package com.witalis.jkit.usage.core.invoke.section.multithreading.content.group;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Group Thread
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Slf4j
public class GroupThread extends Thread {
    boolean suspendFlag;

    public GroupThread(ThreadGroup group, String name) {
        super(group, name);
        log.info("Thread: " + this);
        suspendFlag = false;
    }

    public void run() {
        try {
            for (int i = 3; i > 0; i--) {
                log.info("$ {}: {}", getName(), i);
                Thread.sleep(1000);
                synchronized (this) {
                    while (suspendFlag) wait();
                }
            }
        } catch (Exception e) {
            log.error("Thread group errors: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void toSuspend() {
        suspendFlag = true;
    }

    public synchronized void toResume() {
        suspendFlag = false;
        notifyAll();
    }
}
