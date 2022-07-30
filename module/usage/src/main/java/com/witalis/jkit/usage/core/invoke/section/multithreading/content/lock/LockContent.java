package com.witalis.jkit.usage.core.invoke.section.multithreading.content.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * Desc: Lock content
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Slf4j
public class LockContent {
    public int count = 0;

    // lock
    public ReentrantLock lock = new ReentrantLock();

    public void lockIncrement() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    // read write lock
    public ReadWriteLock readWritelock = new ReentrantReadWriteLock();

    public void readWrite() {
        Lock rLock1 = readWritelock.readLock();
        Lock rLock2 = readWritelock.readLock();
        Lock wLock1 = readWritelock.writeLock();
        Lock wLock2 = readWritelock.writeLock();
        log.info("Mode [read]: " + (rLock1 == rLock2));
        log.info("Mode [read]: " + (wLock1 == wLock2));
    }

    // stamped lock
    public StampedLock stampedLock = new StampedLock();

    public void stampedRead() {
        // optimistic read - not blocking thread, if blocked - stamp = 0
        long stamp = stampedLock.tryOptimisticRead();
        try {
            log.info("      Optimistic Lock Valid: " + stampedLock.validate(stamp));
            TimeUnit.SECONDS.sleep(1);
            log.info("      Optimistic Lock Valid: " + stampedLock.validate(stamp));
            TimeUnit.SECONDS.sleep(2);
            log.info("      Optimistic Lock Valid: " + stampedLock.validate(stamp));
        } catch (InterruptedException e) {
            log.error("    Execution errors: " + e.getClass().getName());
            Thread.currentThread().interrupt();
        } finally {
            stampedLock.unlock(stamp);
            log.info("    Read done: " + stamp);
        }
    }

    public void stampedWrite() {
        long stamp = stampedLock.writeLock();
        try {
            log.info("    Write Lock acquired: " + stamp);
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.error("    Execution errors: " + e.getClass().getName());
            Thread.currentThread().interrupt();
        } finally {
            stampedLock.unlock(stamp);
            log.info("    Write done: " + stamp);
        }
    }
}
