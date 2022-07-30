package com.witalis.jkit.usage.core.invoke.section.generics.content;

import lombok.extern.slf4j.Slf4j;

/**
 * Generic constructor
 */
@Slf4j
public class GenCons {
    private double value;

    public <T extends Number> GenCons(T arg) {
        log.info("Class is " + arg.getClass().getName());
        value = arg.doubleValue();
    }

    public void show() {
        log.info("  Value is " + value);
    }
}
