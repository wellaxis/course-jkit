package com.witalis.jkit.usage.core.invoke.section.lambda.content.custom;

/**
 * Desc: Lambda with exception
 * User: Wellaxis
 * Date: 5/14/2022
 */
@FunctionalInterface
public interface LambdaException {
    double func(double[] arr) throws EmptyArrayException;
}
