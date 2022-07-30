package com.witalis.jkit.usage.core.invoke.section.multithreading.content.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Thread Extended (extends Thread)
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Slf4j
public class ExtendedThread extends Thread {

    // new thread [-] extends thread
    public ExtendedThread() {
        super("Child Extended");
        start();
    }

    public void run() {
        try {
            for (int i = 3; i > 0; i--) {
                log.info("Child[E] Sleep[" + i + "]");
                sleep(200);
            }
        } catch (InterruptedException ie) {
            log.error("Child[E] is interrupted");
            Thread.currentThread().interrupt();
        } finally {
            log.info("Child[E] Done");
        }
    }
}
