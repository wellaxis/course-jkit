package com.witalis.jkit.usage.core.invoke.section.interfaces.content;

/**
 * Desc: Interface with default methods
 * User: Wellaxis
 * Date: 21.12.2021
 */
// interface with default methods - since JDK 8
public interface CallDefault {

    // extension method = default method (with behaviour)
    default String getMessage() {
        return "It's interface with default method";
    }
}
