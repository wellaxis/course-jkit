package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.simplefactory;

/**
 * Desc: Wooden Door class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class WoodenDoor implements Door {
    protected float width;
    protected float height;

    public WoodenDoor(float width, float height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }
}
