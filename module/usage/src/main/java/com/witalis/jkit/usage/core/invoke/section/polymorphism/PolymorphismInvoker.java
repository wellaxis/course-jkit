package com.witalis.jkit.usage.core.invoke.section.polymorphism;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.overloading.OverloadingInvoker;
import com.witalis.jkit.usage.core.invoke.section.overriding.OverridingInvoker;
import com.witalis.jkit.usage.core.invoke.section.polymorphism.content.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Polymorphism
 * User: Wellaxis
 * Date: 2019/11/17
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class PolymorphismInvoker extends Invoker {

    public PolymorphismInvoker() {
        setTitle("Polymorphism chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // overloading
        invokeOverloading();
        // tab
        log.info("");
        // overriding
        invokeOverriding();
    }

    /**
     * Basic postulates of polymorphism.
     */
    private void invokeBasis() {
        // Polymorphism means "many forms", and it occurs when we have many classes
        // that are related to each other by inheritance.

        // There are two types of polymorphism in Java:
        // * compile-time polymorphism (method overloading)
        // * runtime polymorphism (method overriding)

        log.info("---- Basic postulates");

        // aspect - one interface, multiple methods
        ShapeGenerator generator = new ShapeGenerator();
        Shape[] shapes = new Shape[10];
        for (int i = 0; i < shapes.length; ++i) {
            shapes[i] = generator.next();
        }
        // dynamic method dispatch - is made at run time
        for (Shape shape : shapes) shape.draw();
    }

    /**
     * Overloading polymorphism.
     */
    private void invokeOverloading() {
        new OverloadingInvoker().run();
    }

    /**
     * Overriding polymorphism.
     */
    private void invokeOverriding() {
        new OverridingInvoker().run();
    }
}
