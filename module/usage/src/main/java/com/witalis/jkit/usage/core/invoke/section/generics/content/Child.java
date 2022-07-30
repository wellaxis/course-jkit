package com.witalis.jkit.usage.core.invoke.section.generics.content;

import lombok.extern.slf4j.Slf4j;

/**
 * Generic hierarchies
 */
@Slf4j
public class Child<T> extends Parent<T> {
    T[] array;

    public Child(T[] array) {
        super(array[0]);
        this.array = array;
    }

    public T[] showAll() {
        return array;
    }

    @Override
    public void over() {
        var clazz = (value != null) ? value.getClass().getName() : "undefined";
        log.info("I'm - child. Class - " + clazz);
    }
}