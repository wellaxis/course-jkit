package com.witalis.jkit.usage.core.invoke.section.references.content;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Reference 'super'
 * User: Wellaxis
 * Date: 7/10/2022
 */
@Slf4j
public class ReferenceSuper extends ReferenceThis {

    public ReferenceSuper(String name) {
        super(name);
    }

    public ReferenceSuper(String name, long value) {
        super(name, value);
    }

    public ReferenceSuper(int id, String name, long value) {
        super(id, name, value);
    }

    public String representation(ReferenceSuper referenceSuper) {
        return super.representation(referenceSuper);
    }

    public void print() {
        log.info(super.representation(this));
    }

    public ReferenceThis increment() {
        super.increment();
        return this;
    }
}
