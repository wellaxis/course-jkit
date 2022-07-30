package com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.lock;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.lock.Locker.runLocked;

@Slf4j
public class Locking {
    Lock lock = new ReentrantLock();

    protected void setLock(final Lock mock) {
        lock = mock;
    }

    public void doOp1() {
        lock.lock();
        try {
            log.info("...critical code...");
        } finally {
            lock.unlock();
        }
    }

    public void doOp2() {
        runLocked(
            lock,
            () -> log.info("Locking moment: {}", LocalDateTime.now())
        );
    }

    public void doOp3() {
        runLocked(
            lock,
            () -> {
                var text = "It's the multi-text";
                log.info(text);
            }
        );
    }

    public void doOp4() {
        runLocked(
            lock,
            () -> {
                doOp2();
                doOp3();
            }
        );
    }

    public void doOp5(Runnable task) {
        runLocked(
            lock,
            task
        );
    }
}
