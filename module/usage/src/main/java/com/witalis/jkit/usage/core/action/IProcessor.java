package com.witalis.jkit.usage.core.action;

/**
 * Desc: Java Kit Processor
 * User: Wellaxis
 * Date: 2019/11/16
 */
public interface IProcessor {
    default void process() {
        throw new UnsupportedOperationException("Implement processing algorithm");
    }

    default boolean active() {
        return true;
    }
}
