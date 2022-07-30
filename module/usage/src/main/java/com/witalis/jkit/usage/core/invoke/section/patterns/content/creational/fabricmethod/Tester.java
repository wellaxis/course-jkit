package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.fabricmethod;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Tester class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Slf4j
public class Tester implements Interviewer {

    @Override
    public void askQuestions() {
        log.info("Tester: asks about tests");
    }
}
