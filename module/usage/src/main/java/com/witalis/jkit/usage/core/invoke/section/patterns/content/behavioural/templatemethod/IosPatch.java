package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.templatemethod;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Ios Patch class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Slf4j
public class IosPatch extends Patch {

    @Override
    public void test() {
        log.info("\tRun of IOS tests");
    }

    @Override
    public void build() {
        log.info("\tBuild IOS classes");
    }

    @Override
    public void deploy() {
        log.info("\tDeploy IOS changes");
    }
}
