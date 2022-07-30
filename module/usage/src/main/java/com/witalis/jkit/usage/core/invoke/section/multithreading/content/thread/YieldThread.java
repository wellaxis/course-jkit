package com.witalis.jkit.usage.core.invoke.section.multithreading.content.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Yield Thread
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Slf4j
public class YieldThread extends Thread {
    public boolean activateYield;

    public YieldThread(String name, boolean activateYield) {
        setName(name);
        this.activateYield = activateYield;
        start();
    }

    @Override
    public void run() {
        if (activateYield) {
            log.info("Thread " + getName() + ": gives way to others - yield");
            Thread.yield();
        } else {
            log.info("Thread " + getName() + ": is simple - not yield at all");
        }
        log.info("Thread " + getName() + ": access to others - done");
    }
}
