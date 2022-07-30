package com.witalis.jkit.usage.core.invoke.section.garbage.content;

import lombok.extern.slf4j.Slf4j;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;

/**
 * Desc: Garbage collector handler
 * User: Wellaxis
 * Date: 5/22/2022
 */
@Slf4j
public class GCHandler {

    public void printStatistics() {
        log.info("    Garbage Collection: statistics");

        long totalGarbageCollections = 0;
        long garbageCollectionTime = 0;

        for (GarbageCollectorMXBean gc : ManagementFactory.getGarbageCollectorMXBeans()) {
            long count = gc.getCollectionCount();
            if (count >= 0) {
                totalGarbageCollections += count;
            }
            long time = gc.getCollectionTime();
            if (time >= 0) {
                garbageCollectionTime += time;
            }
        }

        log.info("    Garbage Collections: total count = {}", totalGarbageCollections);
        log.info("    Garbage Collection: total time (ms) = {}", garbageCollectionTime);
    }
}
