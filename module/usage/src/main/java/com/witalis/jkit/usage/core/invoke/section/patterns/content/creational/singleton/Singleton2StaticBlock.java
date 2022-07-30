package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.singleton;

/**
 * Desc: Static Block singleton.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class Singleton2StaticBlock {
    private static Singleton2StaticBlock instance;

    private Singleton2StaticBlock() {
    }

    // static block initialization for exception handling
    static {
        try {
            instance = new Singleton2StaticBlock();
        } catch(Exception e) {
            throw new RuntimeException("Initialization errors");
        }
    }

    public static Singleton2StaticBlock getInstance() {
        return instance;
    }
}
