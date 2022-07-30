package com.witalis.jkit.usage.core.invoke.section.variable.content;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Class example
 * User: Wellaxis
 * Date: 4/13/2022
 */
@Slf4j
public class StaticExample {
    // static variables
    public static final int id;
    public static long index;

    // in block - only initialization
    static {
        id = 1000;
    }

    public static long getIndex() {
        return index;
    }

    public static void setIndex(long index) {
        StaticExample.index = index;
    }

    // in method - only implementation
    public static void information() {
        log.info("Static [information]: {}, {}", id, index);
    }
}
