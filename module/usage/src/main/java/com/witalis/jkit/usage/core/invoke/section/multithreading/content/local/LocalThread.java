package com.witalis.jkit.usage.core.invoke.section.multithreading.content.local;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Local Thread
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Slf4j
public class LocalThread extends Thread {
    private LocalHolder holder;

    public LocalThread(String name, LocalHolder holder) {
        super(name);
        this.holder = holder;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        // thread
        log.info("    T: " + Thread.currentThread());

        // init/get
        log.info("    {}[before]: simple = {}", name, holder.getSimple());
        log.info("    {}[before]: local = {}", name, holder.getLocal().get());

        // set/get
        holder.setSimple(holder.getSimple() + 1);
        log.info("    {}[after]: simple = {}", name, holder.getSimple());
        holder.getLocal().set(holder.getLocal().get() + 1);
        log.info("    {}[after]: local = {}", name, holder.getLocal().get());
    }
}
