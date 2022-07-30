package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.templatemethod;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Patch class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Slf4j
public abstract class Patch implements Patchable {
    @Override
    public boolean patch() {
        try {
            log.info("Patch: begin");
            test();
            build();
            deploy();
            log.info("Patch: done");
            return true;
        } catch (Exception e) {
            log.error("Unable to patch changes", e);
            return false;
        }
    }

    public abstract void test();

    public abstract void build();

    public abstract void deploy();
}
