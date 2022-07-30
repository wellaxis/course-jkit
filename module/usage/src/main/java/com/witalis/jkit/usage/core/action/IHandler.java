package com.witalis.jkit.usage.core.action;

/**
 * Desc: handler
 * User: Wellaxis
 * Date: 2019/11/16
 */
public interface IHandler extends IFormalizable, IProcessor {
    void handle();

    @Override
    default void process() {
        handle();
    }
}
