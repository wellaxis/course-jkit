package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.simplefactory;

/**
 * Desc: Door factory class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class DoorFactory {

    public static Door makeDoor(float width, float height) {
        return new WoodenDoor(width, height);
    }
}
