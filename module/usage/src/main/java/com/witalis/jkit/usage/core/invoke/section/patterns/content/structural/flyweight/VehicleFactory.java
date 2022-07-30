package com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.flyweight;

import lombok.ToString;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc: Vehicle factory class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@ToString
public class VehicleFactory {
    private static Map<String, Vehicle> vehiclesCache = new HashMap<>();

    public static Vehicle createVehicle(String color) {
        Vehicle vehicle = vehiclesCache.computeIfAbsent(
            color,
            newColor -> {
                double cost = 1234.56D;
                return new Truck(cost, newColor);
            }
        );
        return vehicle;
    }
}
