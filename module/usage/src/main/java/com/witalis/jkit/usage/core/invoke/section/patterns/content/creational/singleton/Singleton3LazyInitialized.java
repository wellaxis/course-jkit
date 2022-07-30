package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.singleton;

/**
 * Desc: Lazy Initialized singleton.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class Singleton3LazyInitialized {
    private static Singleton3LazyInitialized instance;

    private Singleton3LazyInitialized() {
    }

    public static Singleton3LazyInitialized getInstance() {
        if (instance == null) {
            instance = new Singleton3LazyInitialized();
        }
        return instance;
    }
}
