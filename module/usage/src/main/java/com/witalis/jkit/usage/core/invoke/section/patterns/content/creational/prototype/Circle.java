package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.prototype;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Desc: Circle class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class Circle extends Shape {
    private int radius;

    public Circle(Circle target) {
        super(target);
        if (target != null) {
            setRadius(target.getRadius());
        }
    }

    @Override
    public Shape clone() {
        return new Circle(this);
    }
}
