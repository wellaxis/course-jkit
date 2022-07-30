package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.singleton;

/**
 * Desc: Bill Pugh singleton.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class Singleton6BillPugh {

    private Singleton6BillPugh() {
    }

    private static class SingletonHolder{
        private static final Singleton6BillPugh INSTANCE = new Singleton6BillPugh();
    }

    public static Singleton6BillPugh getInstance() {
        return Singleton6BillPugh.SingletonHolder.INSTANCE;
    }
}
