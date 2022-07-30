package com.witalis.jkit.usage.core.invoke.section.polymorphism.content;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Square
 * User: Wellaxis
 * Date: 4/9/2022
 */
@Slf4j
public class Square extends Shape {

    public Square() {
        super();
    }

    @Override
    public void draw() {
        log.info("Square: draw...");
    }

    @Override
    public void erase() {
        log.info("Square: erase...");
    }
}
