package com.witalis.jkit.usage.core.invoke.section.multithreading.content.scenario;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;

/**
 * Desc: Deadlock sample
 * User: Wellaxis
 * Date: 17.01.2022
 */
@Slf4j
public class DeadLock {
    private final Object lockA;
    private final Object lockB;
    private final CyclicBarrier barrier;

    public DeadLock() {
        lockA = new Object();
        lockB = new Object();
        barrier = new CyclicBarrier(2);
    }

    /**
     * Deadlock emulation.
     */
    public void deadlock() {
        Thread thread1 = new Thread(
            () -> {
                synchronized (lockA) {
                    log.info("Monitor object A");
                    try {
                        barrier.await();
                        log.info("Barrier object A");
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                    synchronized (lockB) {
                        log.info("Try to take B monitor...");
                    }
                }
            },
            "Thread A"
        );

        Thread thread2 = new Thread(
            () -> {
                synchronized (lockB) {
                    log.info("Monitor object B");
                    try {
                        barrier.await();
                        log.info("Barrier object B");
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                    synchronized (lockA) {
                        log.info("Try to take A monitor...");
                    }
                }
            },
            "Thread B"
        );

        thread1.start();
        thread2.start();

        log.info("Deadlock upcoming to infinity...");
    }
}
