package com.witalis.jkit.usage.core.invoke.section.modifiers.content.volatiles;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Volatile example
 * User: Wellaxis
 * Date: 4/12/2022
 */
@Slf4j
@Data
public class VolatileExample {
    public int code;
    public volatile long counter;

    public VolatileExample(int code, long counter) {
        this.code = code;
        this.counter = counter;
    }

    public void specification() {
        log.info("Volatile [action]: {} | {}", code, counter);
    }
}
