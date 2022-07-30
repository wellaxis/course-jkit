package com.witalis.jkit.usage.core.invoke.section.enumeration.content;

/**
 * Desc: Enumeration
 * User: Wellaxis
 * Date: 21.12.2021
 */
// enum with attributes
public enum Apple {
    // self-typed constants
    Jonathan, GoldenDel, RedDel(20), Winesap(33.33), Cortland(90);

    public static final double PRICE_UNDEFINED = -1;
    private final double price;

    Apple() {
        this.price = PRICE_UNDEFINED;
    }

    Apple(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public double getCost(float mass) {
        return mass * price;
    }
}
