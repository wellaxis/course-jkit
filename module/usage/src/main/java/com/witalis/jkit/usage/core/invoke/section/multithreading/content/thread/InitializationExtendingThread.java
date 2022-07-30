package com.witalis.jkit.usage.core.invoke.section.multithreading.content.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Initialization based on thread.
 * User: Wellaxis
 * Date: 4/30/2022
 */
@Slf4j
public class InitializationExtendingThread extends Thread {

    @Override
    public void run() {
        log.info("Initialization [extending thread]: non-preference");
    }
}
