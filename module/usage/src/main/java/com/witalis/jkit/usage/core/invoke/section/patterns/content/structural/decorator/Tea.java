package com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.decorator;

/**
 * Desc: Tea class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class Tea implements Drink {

    @Override
    public double getCost() {
        return 3.00;
    }

    @Override
    public String getDescription() {
        return "It's tea";
    }
}
