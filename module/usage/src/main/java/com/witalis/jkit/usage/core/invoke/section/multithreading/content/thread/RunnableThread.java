package com.witalis.jkit.usage.core.invoke.section.multithreading.content.thread;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

/**
 * Desc: Thread Runnable (implements Runnable)
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Slf4j
public class RunnableThread implements Runnable {
    public Thread t;

    // new thread [+] runnable interface - not return values
    public RunnableThread() {
        t = new Thread(this, "Child Interface");
        t.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 3; i > 0; i--) {
                log.info("Child[I] Sleep[" + i + "]");
                sleep(200);
            }
        } catch (InterruptedException ie) {
            log.error("Child[I] is interrupted");
            Thread.currentThread().interrupt();
        } finally {
            log.info("Child[I] Done");
        }
    }
}
