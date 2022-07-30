package com.witalis.jkit.usage.core.invoke.section.multithreading.content.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Initialization based on runnable.
 * User: Wellaxis
 * Date: 4/30/2022
 */
@Slf4j
public class InitializationImplementingRunnable implements Runnable {

    @Override
    public void run() {
        log.info("Initialization [implementing runnable]: preference");
    }
}
