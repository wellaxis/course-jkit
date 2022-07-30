package com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.decorator;

/**
 * Desc: Drink decorator class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public abstract class DrinkDecorator implements Drink {
    protected Drink drink;

    protected DrinkDecorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    public double getCost() {
        return drink.getCost();
    }

    @Override
    public String getDescription() {
        return drink.getDescription();
    }
}
