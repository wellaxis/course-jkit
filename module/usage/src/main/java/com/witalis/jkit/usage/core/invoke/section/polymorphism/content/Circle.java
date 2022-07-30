package com.witalis.jkit.usage.core.invoke.section.polymorphism.content;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Circle
 * User: Wellaxis
 * Date: 4/9/2022
 */
@Slf4j
public class Circle extends Shape {

    public Circle() {
        super();
    }

    @Override
    public void draw() {
        log.info("Circle: draw...");
    }

    @Override
    public void erase() {
        log.info("Circle: erase...");
    }
}
