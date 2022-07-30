package com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.error;

/**
 * Desc: Lambda to handle exceptions
 * User: Wellaxis
 * Date: 5/14/2022
 */
@FunctionalInterface
public interface HandleError<T, X extends Throwable> {
    void accept(T instance) throws X;
}
