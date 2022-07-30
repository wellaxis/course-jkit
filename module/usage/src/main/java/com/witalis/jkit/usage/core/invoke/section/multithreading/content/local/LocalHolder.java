package com.witalis.jkit.usage.core.invoke.section.multithreading.content.local;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Local Holder
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Slf4j
@Data
public class LocalHolder {
    // Simple variable - overridden value by threads.
    public Integer simple;

    // Different threads cannot see each other's values.
    // Thus, they set and get different values.
    public ThreadLocal<Integer> local;

    public LocalHolder(Integer initial) {
        this.simple = initial;
        this.local = ThreadLocal.withInitial(() -> initial);
    }
}
