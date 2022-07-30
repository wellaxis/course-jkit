package com.witalis.jkit.usage.core.invoke.section.lambda.content.custom;

/**
 * Desc: Lambda with generic parameters
 * User: Wellaxis
 * Date: 5/14/2022
 */
@FunctionalInterface
public interface LambdaGeneric<T> {
    T gen(T t);
}
