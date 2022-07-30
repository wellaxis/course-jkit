package com.witalis.jkit.usage.core.invoke.section.generics.content;

/**
 * Simple generic - not primitive types
 */
public class Gen<T> {
    protected T value;

    public Gen(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public String show() {
        var clazz = (value != null) ? value.getClass().getName() : "undefined";
        return "Info [" + clazz + "] -> " + getValue();
    }
}
