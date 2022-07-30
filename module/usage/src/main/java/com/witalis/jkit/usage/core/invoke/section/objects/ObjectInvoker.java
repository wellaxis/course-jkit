package com.witalis.jkit.usage.core.invoke.section.objects;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.enumeration.EnumerationInvoker;
import com.witalis.jkit.usage.core.invoke.section.classes.ClassInvoker;
import com.witalis.jkit.usage.core.invoke.section.interfaces.InterfaceInvoker;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Java objects
 * User: Wellaxis
 * Date: 2021/04/01
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class ObjectInvoker extends Invoker {

    public ObjectInvoker() {
        setTitle("Class & Object chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // interfaces
        log.info("## Interfaces");
        invokeInterface();
        // tab
        log.info("");
        // classes
        log.info("## Classes");
        invokeClass();
        // tab
        log.info("");
        // enumerations
        log.info("## Enumerations");
        invokeEnumeration();
    }

    /**
     * Basic postulates of objects.
     */
    private void invokeBasis() {
        // An object is a basic unit of an object-oriented programming language.
        // It is any real-world thing that has properties and actions.

        log.info("An object is a basic unit of an object-oriented programming language.");
        // It is any real-world thing that has properties and actions.

        // Object characteristics:
        // * state     [represents properties or attributes of an object (represented by instance variable)]
        // * behaviour [represents functionality or actions (represented by methods)]
        // * identity  [represents the unique name of an object (differentiates one object from the other)]
        log.info("An object in Java has three characteristics: state, behaviour, identity");
    }

    /**
     * Java interfaces.
     */
    private void invokeInterface() {
        new InterfaceInvoker().run();
    }

    /**
     * Java classes.
     */
    private void invokeClass() {
        new ClassInvoker().run();
    }

    /**
     * Java enumeration.
     */
    private void invokeEnumeration() {
        new EnumerationInvoker().run();
    }
}
