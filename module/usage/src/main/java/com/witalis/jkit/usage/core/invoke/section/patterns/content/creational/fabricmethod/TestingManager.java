package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.fabricmethod;

/**
 * Desc: Testing Manager class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class TestingManager extends HiringManager {

    @Override
    public Interviewer makeInterviewer() {
        return new Tester();
    }
}
