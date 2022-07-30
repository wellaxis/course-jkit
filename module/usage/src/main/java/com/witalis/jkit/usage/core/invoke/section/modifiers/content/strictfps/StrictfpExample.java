package com.witalis.jkit.usage.core.invoke.section.modifiers.content.strictfps;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Strictfp example
 * User: Wellaxis
 * Date: 4/12/2022
 */
@Slf4j
@SuppressWarnings("strictfp")
public strictfp class StrictfpExample {
    public int counter;

    public StrictfpExample(int counter) {
        this.counter = counter;
        log.info("Strictfp [constructor]: {}", counter);
    }

    public strictfp int count(int value) {
        int result = counter + value;
        log.info("Strictfp [method]: {}", result);
        return result;
    }
}
