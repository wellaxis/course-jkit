package com.witalis.jkit.usage.core.invoke.section.variable.content;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Local example
 * User: Wellaxis
 * Date: 4/12/2022
 */
@Slf4j
public class LocalExample {

    // local variable in non-static block [1]
    {
        final long code = 300L;
        log.info("Local [block]: {}", code);
    }

    // local variable in constructor [2]
    public LocalExample() {
        int index = 10_000;
        log.info("Local [constructor]: {}", index);
    }

    // local variable in method [3]
    public void information() {
        String information = "Example";
        log.info("Local [information]: {}", information);
    }
}
