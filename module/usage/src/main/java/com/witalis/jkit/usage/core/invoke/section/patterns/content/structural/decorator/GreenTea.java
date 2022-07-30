package com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.decorator;

/**
 * Desc: Green Tea class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class GreenTea extends DrinkDecorator {

    public GreenTea(Drink drink) {
        super(drink);
    }

    @Override
    public double getCost() {
        return drink.getCost() + 2.50;
    }

    @Override
    public String getDescription() {
        return drink.getDescription() + " with green leaves";
    }
}
