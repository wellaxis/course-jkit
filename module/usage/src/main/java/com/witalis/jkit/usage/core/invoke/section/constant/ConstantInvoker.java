package com.witalis.jkit.usage.core.invoke.section.constant;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.constant.content.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Constants
 * User: Wellaxis
 * Date: 2019/11/17
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class ConstantInvoker extends Invoker {
    public static final String DEF_NOTE = "Final notes ...";

    public ConstantInvoker() {
        setTitle("Constants chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // interface
        log.info("## Interface");
        invokeInterface();
        // tab
        log.info("");
        // class
        log.info("## Class");
        invokeClass();
        // tab
        log.info("");
        // lambda
        log.info("## Lambda");
        invokeLambda();
    }

    /**
     * Basic postulates of constant values.
     */
    private void invokeBasis() {
        // Constant - a value which is fixed and does not change during the execution of a program.
        // Java supports various types of constants: integer, real, character, string, etc.

        log.info("Cannot assign a value to final variable.");
    }

    /**
     * Constants in interfaces.
     */
    private void invokeInterface() {
        // It is an Anti-pattern to use interfaces as constants containers.

        log.info("Interface constants [id]: {}", InterfaceConstant.ID);
        log.info("Interface constants [message]: {}", InterfaceConstant.MESSAGE);
        log.info("Interface constants [status]: {}", InterfaceConstant.ACTIVE);
    }

    /**
     * Constants in classes.
     */
    private void invokeClass() {
        // Constants in classes can have different modifiers.

        ClassConstant classConstant = new ClassConstant();
        log.info("Class constants [code]: {}", ClassConstant.CODE);
        log.info("Class constants [info]: {}", "ClassConstant.INFO");

        // Local constants into methods have the final modifier.

        log.info("Local constants [value]: {}", classConstant.getValue());
    }

    /**
     * Constants in lambdas.
     */
    private void invokeLambda() {
        // Variables used in lambda expression should be final or effectively final.
        // But in lambda it's possible to use containers, like atomics, arrays, collections.

        LambdaConstant lambdaConstant = new LambdaConstant(500);
        var value = lambdaConstant.generate(lambdaConstant.getNumber());
        log.info("Lambda constants [number]: {}", value);
    }
}
