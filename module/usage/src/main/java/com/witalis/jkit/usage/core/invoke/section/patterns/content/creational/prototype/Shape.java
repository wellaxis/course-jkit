package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.prototype;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Desc: Shape class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@NoArgsConstructor
@Data
@ToString
public abstract class Shape {
    private int x;
    private int y;
    private String color;

    protected Shape(Shape target) {
        if (target != null) {
            setX(target.getX());
            setY(target.getY());
            setColor(target.getColor());
        }
    }

    protected abstract Shape clone();
}
