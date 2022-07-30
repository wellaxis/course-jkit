package com.witalis.jkit.usage.core.invoke.section.operators.content;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Checker record
 * User: Wellaxis
 * Date: 4/24/2022
 */
@Slf4j
public record Checker() {

    public boolean check(int i) {
        log.info("$ check {}", i); return i > 0;
    }
}
