package com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.decorator;

/**
 * Desc: Coffee class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class Coffee implements Drink {

    @Override
    public double getCost() {
        return 9.99;
    }

    @Override
    public String getDescription() {
        return "It's coffee";
    }
}
