package com.witalis.jkit.usage.core.invoke.section.references.content;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Desc: Reference 'this'
 * User: Wellaxis
 * Date: 7/10/2022
 */
@Slf4j
public class ReferenceThis {
    private int id;
    private String name;
    private long value;

    public ReferenceThis(String name) {
        this.id = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
        this.value = 1L;
        this.name = name;
    }

    public ReferenceThis(String name, long value) {
        this(name);
        this.value = value;
    }

    public ReferenceThis(int id, String name, long value) {
        this(name, value);
        this.id = id;
    }

    public String representation(ReferenceThis referenceThis) {
        return String.format("This: [ID = %d, name = '%s']", referenceThis.id, referenceThis.name);
    }

    public void print() {
        log.info(this.representation(this));
    }

    public ReferenceThis increment() {
        value++;
        return this;
    }
}
