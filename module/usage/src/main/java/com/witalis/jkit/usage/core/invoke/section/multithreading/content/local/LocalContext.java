package com.witalis.jkit.usage.core.invoke.section.multithreading.content.local;

import java.util.UUID;

/**
 * Desc: Local context - pojo class.
 * User: Wellaxis
 * Date: 4/30/2022
 */
public class LocalContext {
    private final String id;
    private final String name;

    public LocalContext(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    @Override
    public String toString() {
        return "Context [id = '" + id + "', name = '" + name + "']";
    }
}
