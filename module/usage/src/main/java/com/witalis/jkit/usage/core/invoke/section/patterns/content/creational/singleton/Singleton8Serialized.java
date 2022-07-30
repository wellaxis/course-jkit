package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.singleton;

import java.io.Serial;
import java.io.Serializable;

/**
 * Desc: Serialized singleton.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class Singleton8Serialized implements Serializable {
    private static final long serialVersionUID = -7604766932017737115L;

    private Singleton8Serialized() {
    }

    private static class SingletonHolder {
        private static final Singleton8Serialized instance = new Singleton8Serialized();
    }

    public static Singleton8Serialized getInstance(){
        return Singleton8Serialized.SingletonHolder.instance;
    }

    @Serial
    public Object readResolve() {
        return Singleton8Serialized.getInstance();
    }
}
