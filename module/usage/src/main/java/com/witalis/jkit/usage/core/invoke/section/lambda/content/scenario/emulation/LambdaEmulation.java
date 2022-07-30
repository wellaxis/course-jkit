package com.witalis.jkit.usage.core.invoke.section.lambda.content.scenario.emulation;

/**
 * Desc: Lambda class emulation
 * User: Wellaxis
 * Date: 5/14/2022
 */
@FunctionalInterface
public interface LambdaEmulation<T> {
    T emulate(T value);
}
