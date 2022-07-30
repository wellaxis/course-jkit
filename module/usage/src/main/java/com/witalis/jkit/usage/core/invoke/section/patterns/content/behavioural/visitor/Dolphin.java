package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.visitor;

/**
 * Desc: Dolphin class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class Dolphin implements Animal {

    @Override
    public void accept(AnimalOperation operation) {
        operation.visitDolphin(this);
    }

    public String shout() {
        return "Tk-tk-tk";
    }
}
