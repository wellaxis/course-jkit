package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.visitor;

/**
 * Desc: Monkey class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class Monkey implements Animal {

    @Override
    public void accept(AnimalOperation operation) {
        operation.visitMonkey(this);
    }

    public String shout() {
        return "Y-y-a-a";
    }
}
