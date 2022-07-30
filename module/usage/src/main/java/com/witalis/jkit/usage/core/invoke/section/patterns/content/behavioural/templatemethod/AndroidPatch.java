package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.templatemethod;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Android Patch class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Slf4j
public class AndroidPatch extends Patch {

    @Override
    public void test() {
        log.info("\tRun of Android tests");
    }

    @Override
    public void build() {
        log.info("\tBuild Android classes");
    }

    @Override
    public void deploy() {
        log.info("\tDeploy Android changes");
    }
}
