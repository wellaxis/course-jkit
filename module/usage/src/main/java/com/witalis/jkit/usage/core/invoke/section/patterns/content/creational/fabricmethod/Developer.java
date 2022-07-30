package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.fabricmethod;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Developer class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Slf4j
public class Developer implements Interviewer {

    @Override
    public void askQuestions() {
        log.info("Developer: asks about patterns");
    }
}
