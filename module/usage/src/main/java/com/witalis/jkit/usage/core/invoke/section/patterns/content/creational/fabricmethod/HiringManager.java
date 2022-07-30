package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.fabricmethod;

/**
 * Desc: Hiring Manager class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public abstract class HiringManager {

    // fabric method
    protected abstract Interviewer makeInterviewer();

    public void takeInterviewer() {
        Interviewer interviewer = makeInterviewer();
        interviewer.askQuestions();
    }
}
