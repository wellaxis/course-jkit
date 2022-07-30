package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.singleton;

/**
 * Desc: Double Checked Locking singleton.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class Singleton5DoubleCheckedLocking {
    private static Singleton5DoubleCheckedLocking instance;

    private Singleton5DoubleCheckedLocking() {
    }

    public static Singleton5DoubleCheckedLocking getInstance() {
        if (instance == null) {
            synchronized (Singleton5DoubleCheckedLocking.class) {
                if (instance == null) {
                    instance = new Singleton5DoubleCheckedLocking();
                }
            }
        }
        return instance;
    }
}
