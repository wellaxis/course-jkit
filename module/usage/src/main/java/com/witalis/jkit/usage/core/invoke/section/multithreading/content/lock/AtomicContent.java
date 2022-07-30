package com.witalis.jkit.usage.core.invoke.section.multithreading.content.lock;

import lombok.Data;

/**
 * Desc: Atomic content
 * User: Wellaxis
 * Date: 4/30/2022
 */
@Data
public class AtomicContent {
    private String name;

    public AtomicContent(String name) {
        this.name = name;
    }
}
