package com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.flyweight;

import lombok.Data;
import java.util.UUID;

/**
 * Desc: Truck class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Data
public class Truck implements Vehicle {
    private String id;
    private double cost;
    private String color;

    public Truck(double cost, String color) {
        this.id = UUID.randomUUID().toString();
        this.cost = cost;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Truck: [" + getId() + "], color " + getColor() + ", cost = " + getCost() + "$";
    }
}
