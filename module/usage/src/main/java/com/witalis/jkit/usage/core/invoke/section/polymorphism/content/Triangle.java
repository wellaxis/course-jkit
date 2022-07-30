package com.witalis.jkit.usage.core.invoke.section.polymorphism.content;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Triangle
 * User: Wellaxis
 * Date: 4/9/2022
 */
@Slf4j
public class Triangle extends Shape {

    public Triangle() {
        super();
    }

    @Override
    public void draw() {
        log.info("Triangle: draw...");
    }

    @Override
    public void erase() {
        log.info("Triangle: erase...");
    }
}
