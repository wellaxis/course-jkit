package com.witalis.jkit.usage.core.invoke.section.generics.content;

public class GenericEmulation<T> {
    T value;

    // possible to use generic for static methods - BUT - owns only
    static <R> R getInfo(R information) {
        return information;
    }
}
