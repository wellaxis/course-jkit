package com.witalis.jkit.usage.core.invoke.section.modifiers.content.abstracts;

/**
 * Desc: Abstract example
 * User: Wellaxis
 * Date: 4/12/2022
 */
public abstract class AbstractExample {
    public final int code;

    protected AbstractExample(int code) {
        this.code = code;
    }

    // abstract method
    public abstract void processNote();

    public int getCode() {
        return code;
    }
}
