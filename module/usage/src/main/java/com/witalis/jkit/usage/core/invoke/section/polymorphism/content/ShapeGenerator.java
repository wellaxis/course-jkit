package com.witalis.jkit.usage.core.invoke.section.polymorphism.content;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Desc: Shape Generator
 * User: Wellaxis
 * Date: 4/9/2022
 */
public class ShapeGenerator {
    private final ThreadLocalRandom randomizer;

    public ShapeGenerator() {
        super();
        this.randomizer = ThreadLocalRandom.current();
    }

    public Shape next() {
        return switch (randomizer.nextInt(3)) {
            case 0 -> new Circle();
            case 1 -> new Square();
            case 2 -> new Triangle();
            default -> throw new IllegalStateException("Unexpected value");
        };
    }
}

