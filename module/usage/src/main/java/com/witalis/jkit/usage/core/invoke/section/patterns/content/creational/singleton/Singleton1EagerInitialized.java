package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.singleton;

/**
 * Desc: Eager Initialized singleton.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class Singleton1EagerInitialized {
    private static final Singleton1EagerInitialized instance = new Singleton1EagerInitialized();

    private Singleton1EagerInitialized() {
    }

    public static Singleton1EagerInitialized getInstance() {
        return instance;
    }
}
