package com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.lazy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HolderNative {
    private Heavy heavy;

    public HolderNative() {
        log.info("  Holder [native]: created");
    }

    public synchronized Heavy getHeavy() {
        if (heavy == null) {
            heavy = new Heavy();
        }
        return heavy;
    }
}
