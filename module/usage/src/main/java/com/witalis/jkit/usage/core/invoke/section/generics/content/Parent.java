package com.witalis.jkit.usage.core.invoke.section.generics.content;

import lombok.extern.slf4j.Slf4j;

/**
 * Generic hierarchies
 */
@Slf4j
public class Parent<T> {
    T value;

    public Parent(T value) {
        this.value = value;
    }

    public T show() {
        return value;
    }

    public void over() {
        var clazz = (value != null) ? value.getClass().getName() : "undefined";
        log.info("I'm - parent. Class - " + clazz);
    }
}