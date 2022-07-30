package com.witalis.jkit.usage.core.invoke.section.core;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.blocks.BlockInvoker;
import com.witalis.jkit.usage.core.invoke.section.constructors.ConstructorInvoker;
import com.witalis.jkit.usage.core.invoke.section.core.content.*;
import com.witalis.jkit.usage.core.invoke.section.exception.ExceptionInvoker;
import com.witalis.jkit.usage.core.invoke.section.generics.GenericInvoker;
import com.witalis.jkit.usage.core.invoke.section.methods.MethodInvoker;
import com.witalis.jkit.usage.core.invoke.section.modifiers.ModifierInvoker;
import com.witalis.jkit.usage.core.invoke.section.multithreading.MultithreadingInvoker;
import com.witalis.jkit.usage.core.invoke.section.objects.ObjectInvoker;
import com.witalis.jkit.usage.core.invoke.section.operators.OperatorInvoker;
import com.witalis.jkit.usage.core.invoke.section.packages.PackageInvoker;
import com.witalis.jkit.usage.core.invoke.section.references.ReferenceInvoker;
import com.witalis.jkit.usage.core.invoke.section.statements.StatementInvoker;
import com.witalis.jkit.usage.core.invoke.section.streams.StreamInvoker;
import com.witalis.jkit.usage.core.invoke.section.tokens.TokenInvoker;
import com.witalis.jkit.usage.core.invoke.section.types.DataTypeInvoker;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Java core
 * User: Wellaxis
 * Date: 2019/11/17
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class CoreInvoker extends Invoker {

    public CoreInvoker() {
        setTitle("Core chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // token
        invokeToken();
        // tab
        log.info("");
        // package
        invokePackage();
        // tab
        log.info("");
        // data type
        invokeDataType();
        // tab
        log.info("");
        // operators
        invokeOperator();
        // tab
        log.info("");
        // statements
        invokeStatement();
        // tab
        log.info("");
        // object
        invokeObject();
        // tab
        log.info("");
        // reference
        invokeReference();
        // tab
        log.info("");
        // methods
        invokeMethod();
        // tab
        log.info("");
        // constructors
        invokeConstructor();
        // tab
        log.info("");
        // blocks
        invokeBlock();
        // tab
        log.info("");
        // modifiers
        invokeModifier();
        // tab
        log.info("");
        // exceptions
        invokeException();
        // tab
        log.info("");
        // multithreading
        invokeMultithreading();
        // tab
        log.info("");
        // generics
        invokeGenerics();
        // tab
        log.info("");
        // streams
        invokeStreams();
    }

    /**
     * Base functionality.
     */
    private void invokeBasis() {
        log.info("Java is a powerful, versatile, and simple general-purpose programming language.");

        log.info("");

        // codename
        Java.history();

        log.info("");

        // Java features:
        // * Simple              [simple for the professional programmer to learn and use effectively]
        // * Object-oriented     [purely object-oriented programming language (OOP)]
        // * Distributed         [designed to support the distributed environment of the Internet]
        // * Robust              [programs are strong because they do not crash easily like C or C++ programs]
        // * Secure              [provides multiple security features]
        // * System independence [compiler compiles the source code into byte code that is independent of any machine architecture]
        // * Portability         [program gives the same result on every system machine]
        // * Interpreted         [byte code can be executed on any system machine]
        // * High Performance    [compiler improves the performance of interpreting byte code]
        // * Multithreaded       [write programs to do several works simultaneously]
        // * Dynamic             [create animation dynamically on the Internet]

        log.info("JVM (Java Virtual Machine): is an abstract computer machine that is responsible for executing bytecode.");
        log.info("JRE (Java Runtime Environment): used only for executing the application programs.");
        log.info("JDK (Java Development Kit): used for building (developing) java software applications.");

        // example
        Basic basic = new Basic();
        log.info("Base: {}", basic);
    }

    /**
     * Token definitions.
     */
    private void invokeToken() {
        new TokenInvoker().run();
    }

    /**
     * Package definitions.
     */
    private void invokePackage() {
        new PackageInvoker().run();
    }

    /**
     * Data Type definitions.
     */
    private void invokeDataType() {
        new DataTypeInvoker().run();
    }

    /**
     * Operator constructions.
     */
    private void invokeOperator() {
        new OperatorInvoker().run();
    }

    /**
     * Statement constructions.
     */
    private void invokeStatement() {
        new StatementInvoker().run();
    }

    /**
     * Object definitions.
     */
    private void invokeObject() {
        new ObjectInvoker().run();
    }

    /**
     * Reference definitions.
     */
    private void invokeReference() {
        new ReferenceInvoker().run();
    }

    /**
     * Method definitions.
     */
    private void invokeMethod() {
        new MethodInvoker().run();
    }

    /**
     * Constructor definitions.
     */
    private void invokeConstructor() {
        new ConstructorInvoker().run();
    }

    /**
     * Block definitions.
     */
    private void invokeBlock() {
        new BlockInvoker().run();
    }

    /**
     * Modifier definitions.
     */
    private void invokeModifier() {
        new ModifierInvoker().run();
    }

    /**
     * Exception handling.
     */
    private void invokeException() {
        new ExceptionInvoker().run();
    }

    /**
     * Multithreading definitions.
     */
    private void invokeMultithreading() {
        new MultithreadingInvoker().run();
    }

    /**
     * Generics definitions.
     */
    private void invokeGenerics() {
        new GenericInvoker().run();
    }

    /**
     * Streams definitions.
     */
    private void invokeStreams() {
        new StreamInvoker().run();
    }
}
