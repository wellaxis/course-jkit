package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.fabricmethod;

/**
 * Desc: Development Manager class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class DevelopmentManager extends HiringManager {

    @Override
    public Interviewer makeInterviewer() {
        return new Developer();
    }
}
