package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.singleton;

/**
 * Desc: Thread Safe singleton.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class Singleton4ThreadSafe {
    private static Singleton4ThreadSafe instance;

    private Singleton4ThreadSafe() {
    }

    public static synchronized Singleton4ThreadSafe getInstance() {
        if (instance == null) {
            instance = new Singleton4ThreadSafe();
        }
        return instance;
    }
}
