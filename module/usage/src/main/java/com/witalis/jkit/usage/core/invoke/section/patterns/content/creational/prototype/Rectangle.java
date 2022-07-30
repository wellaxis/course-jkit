package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.prototype;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Desc: Rectangle class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class Rectangle extends Shape {
    private int width;
    private int height;

    public Rectangle(Rectangle target) {
        super(target);
        if (target != null) {
            setWidth(target.getWidth());
            setHeight(target.getHeight());
        }
    }

    @Override
    public Shape clone() {
        return new Rectangle(this);
    }
}
