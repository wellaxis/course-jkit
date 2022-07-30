package com.witalis.jkit.usage.core.invoke.section.interfaces.content;

/**
 * Desc: Interface with static methods
 * User: Wellaxis
 * Date: 21.12.2021
 */
// interface with static methods - since JDK 8
public interface CallStatic {

    // static method - may be invoked on containing interface class only
    static int positiveNumber(int number) {
        return Math.abs(number);
    }
}
