package com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.decorator;

/**
 * Desc: Milk Coffee class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class MilkCoffee extends DrinkDecorator {

    public MilkCoffee(Drink drink) {
        super(drink);
    }

    @Override
    public double getCost() {
        return drink.getCost() + 4.25;
    }

    @Override
    public String getDescription() {
        return drink.getDescription() + " with milk";
    }
}
