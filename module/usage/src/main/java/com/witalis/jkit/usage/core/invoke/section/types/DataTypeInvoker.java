package com.witalis.jkit.usage.core.invoke.section.types;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.bits.BitInvoker;
import com.witalis.jkit.usage.core.invoke.section.dates.DateInvoker;
import com.witalis.jkit.usage.core.invoke.section.numbers.NumberInvoker;
import com.witalis.jkit.usage.core.invoke.section.primitives.PrimitiveInvoker;
import com.witalis.jkit.usage.core.invoke.section.strings.StringInvoker;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Data types declaration
 * User: Wellaxis
 * Date: 2019/11/16
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class DataTypeInvoker extends Invoker {

    public DataTypeInvoker() {
        setTitle("Type chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // primitives
        log.info("## Primitives");
        invokePrimitives();
        // tab
        log.info("");
        // non primitives
        log.info("## Non Primitives");
        invokeNonPrimitives();
        // tab
        log.info("");
        // bits
        log.info("## Bits");
        invokeBits();
        // tab
        log.info("");
        // strings
        log.info("## Strings");
        invokeStrings();
        // tab
        log.info("");
        // numbers
        log.info("## Numbers");
        invokeNumbers();
        // tab
        log.info("");
        // dates
        log.info("## Date & Time");
        invokeDates();
    }

    /**
     * Basic data types definitions.
     */
    private void invokeBasis() {
        // Internally, a variable represents a memory location where data is stored.

        // Data types:
        // 1. Primitive data types (also called intrinsic or built-in types)
        // 2. Non-primitive data types (also called derived or reference data type)

        log.info("Data Types: Primitive (intrinsic) & Non-primitive (derived)");
    }

    /**
     * Operations with primitives.
     */
    private void invokePrimitives() {
        new PrimitiveInvoker().run();
    }

    /**
     * Operations with non primitives.
     */
    private void invokeNonPrimitives() {
        // Non-primitive data types are created by programmers. They are not predefined in java like primitive data types.

        log.info("Non-primitive data type variable is also called referenced data type in Java or simply object reference variable.");
        log.info("There are five non-primitive types: class, object, string, array, interface");

        // 1. The default value of any reference variable is null.
        // 2. Whenever you will pass a non-primitive data type to a method, you are actually passing an address of that object where data is stored.
    }

    /**
     * Operations with bits.
     */
    private void invokeBits() {
        new BitInvoker().run();
    }

    /**
     * Operations with strings.
     */
    private void invokeStrings() {
        new StringInvoker().run();
    }

    /**
     * Operations with numbers.
     */
    private void invokeNumbers() {
        new NumberInvoker().run();
    }

    /**
     * Operations with dates.
     */
    private void invokeDates() {
        new DateInvoker().run();
    }
}
