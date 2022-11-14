package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.singleton;

import java.util.function.Supplier;

/**
 * Desc: Virtual proxy singleton.
 * User: Wellaxis
 * Date: 11/14/2022
 */
public class Singleton9VirtualProxy {

    private Singleton9VirtualProxy() {
    }

    private static class SingletonHolder {
        private static Supplier<Singleton9VirtualProxy> supplier = SingletonHolder::createInstance;

        public static Singleton9VirtualProxy getInstance() {
            return supplier.get();
        }

        private static synchronized Singleton9VirtualProxy createInstance() {
            class SingletonFactory implements Supplier<Singleton9VirtualProxy> {
                private final Singleton9VirtualProxy instance = new Singleton9VirtualProxy();

                @Override
                public Singleton9VirtualProxy get() {
                    return instance;
                }
            }

            if (!(supplier instanceof SingletonFactory)) {
                supplier = new SingletonFactory();
            }

            return supplier.get();
        }
    }

    public static Singleton9VirtualProxy getInstance() {
        return Singleton9VirtualProxy.SingletonHolder.getInstance();
    }
}
