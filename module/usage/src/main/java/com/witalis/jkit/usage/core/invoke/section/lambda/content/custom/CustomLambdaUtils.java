package com.witalis.jkit.usage.core.invoke.section.lambda.content.custom;

import java.util.function.Predicate;

/**
 * Desc: Custom lambda utils
 * User: Wellaxis
 * Date: 5/14/2022
 */
public final class CustomLambdaUtils {

    private CustomLambdaUtils() {
        super();
    }

    // check - core implementation via predicate
    public static boolean checkNumber(Predicate<Integer> predicate, Integer value) {
        return predicate.test(value);
    }

    // check - custom implementation via own checking interface
    public static boolean checkNumberOverloading(LambdaOverloading overloading, Integer value) {
        return overloading.test(value);
    }
}
