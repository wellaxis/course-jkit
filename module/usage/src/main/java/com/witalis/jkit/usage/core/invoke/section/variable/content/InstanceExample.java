package com.witalis.jkit.usage.core.invoke.section.variable.content;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Instance example
 * User: Wellaxis
 * Date: 4/13/2022
 */
@Slf4j
public class InstanceExample {
    // instance variables
    public final int id;
    private long index = 100L;
    protected volatile String password;

    public InstanceExample(int id, long index, String password) {
        this.id = id;
        this.index = index;
        this.password = password;
    }

    public void information() {
        log.info("Instance [information]: {}, {}, {}", id, index, password);
    }
}
