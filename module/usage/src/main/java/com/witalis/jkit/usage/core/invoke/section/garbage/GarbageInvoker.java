package com.witalis.jkit.usage.core.invoke.section.garbage;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.garbage.content.GCHandler;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: garbage collector
 * User: Wellaxis
 * Date: 2019/11/16
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class GarbageInvoker extends Invoker {

    public GarbageInvoker() {
        setTitle("Garbage chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // statistics
        log.info("## Statistics");
        invokeStatistics();
    }

    /**
     * Basic postulates of garbage collection.
     */
    private void invokeBasis() {
        // Garbage Collection deals with finding and deleting the garbage from the memory.

        // JVM has five types of GC implementations:
        // * Serial Garbage Collector   => java -XX:+UseSerialGC -jar Application.java
        // * Parallel Garbage Collector => java -XX:+UseParallelGC -jar Application.java
        // * CMS Garbage Collector      => java -XX:+UseParNewGC -jar Application.java
        // * G1 Garbage Collector       => java -XX:+UseG1GC -jar Application.java
        // * Z Garbage Collector        => java -XX:+UseZGC -jar Application.java

        log.info("There are several types of GCs: serial, parallel, CMS, G1, Z, etc.");

        // Invocation of GC:
        // * System.gc();
        // * Runtime.getRuntime().gc();
    }

    private void invokeStatistics() {
        GCHandler gc = new GCHandler();

        log.info("Invocation [before]:");
        gc.printStatistics();

        System.gc();

        log.info("Invocation [after]:");
        gc.printStatistics();
    }
}
