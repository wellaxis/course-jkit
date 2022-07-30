package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.visitor;

/**
 * Desc: Lion class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class Lion implements Animal {

    @Override
    public void accept(AnimalOperation operation) {
        operation.visitLion(this);
    }

    public String shout() {
        return "G-Rrrrrr";
    }
}
