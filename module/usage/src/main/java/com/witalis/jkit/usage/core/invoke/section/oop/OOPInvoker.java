package com.witalis.jkit.usage.core.invoke.section.oop;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.abstraction.AbstractionInvoker;
import com.witalis.jkit.usage.core.invoke.section.encapsulation.EncapsulationInvoker;
import com.witalis.jkit.usage.core.invoke.section.inheritance.InheritanceInvoker;
import com.witalis.jkit.usage.core.invoke.section.polymorphism.PolymorphismInvoker;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Object-oriented programming
 * User: Wellaxis
 * Date: 2019/11/18
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class OOPInvoker extends Invoker {

    public OOPInvoker() {
        setTitle("Object-Oriented Programming chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // encapsulation
        invokeEncapsulation();
        // tab
        log.info("");
        // inheritance
        invokeInheritance();
        // tab
        log.info("");
        // polymorphism
        invokePolymorphism();
        // tab
        log.info("");
        // abstraction
        invokeAbstraction();
    }

    /**
     * Basic details.
     */
    public void invokeBasis() {
        // Object-oriented programming is based on the idea of an object.
        // An object is an entity with some data and operations.

        class Main {
            private int x;

            public Main(int x) {
                this.x = x;
            }

            public int getX() {
                return x;
            }
        }

        Main main = new Main(100);
        log.info("Main: {}", main.getX());
    }

    /**
     * OOP encapsulation.
     */
    private void invokeEncapsulation() {
        new EncapsulationInvoker().run();
    }

    /**
     * OOP inheritance.
     */
    private void invokeInheritance() {
        new InheritanceInvoker().run();
    }

    /**
     * OOP polymorphism.
     */
    private void invokePolymorphism() {
        new PolymorphismInvoker().run();
    }

    /**
     * OOP abstraction.
     */
    private void invokeAbstraction() {
        new AbstractionInvoker().run();
    }
}
