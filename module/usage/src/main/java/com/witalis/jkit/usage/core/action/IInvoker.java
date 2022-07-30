package com.witalis.jkit.usage.core.action;

/**
 * Desc: invoker
 * User: Wellaxis
 * Date: 2019/11/16
 */
public interface IInvoker extends IFormalizable, IProcessor {
    void invoke();

    @Override
    default void process() {
        invoke();
    }
}
