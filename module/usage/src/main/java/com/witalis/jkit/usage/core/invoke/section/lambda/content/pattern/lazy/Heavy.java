package com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.lazy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Heavy {

    public Heavy() {
        log.info("  This resource is heavy created (f.e. connections pool)");
    }

    public void process() {
        log.info("  Heavy: in progress...");
    }
}
