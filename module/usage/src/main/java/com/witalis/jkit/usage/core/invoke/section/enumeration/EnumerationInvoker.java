package com.witalis.jkit.usage.core.invoke.section.enumeration;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.enumeration.content.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Desc: Enumerations
 * User: Wellaxis
 * Date: 2019/11/17
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class EnumerationInvoker extends Invoker {

    public EnumerationInvoker() {
        setTitle("Enumeration chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // nested
        log.info("## Nested Enum");
        invokeNested();
        // tab
        log.info("");
        // interfaces
        log.info("## Interfaces");
        invokeInterface();
    }

    /**
     * Basic postulates of enumerations.
     */
    private void invokeBasis() {
        // An enum type is a special data type that enables for a variable to be a set of predefined constants.
        // You should use enum types any time you need to represent a fixed set of constants.

        // Enumeration cannot extend any class (Enum extending already)

        Apple apple = Apple.Jonathan;
        switch (apple) {
            case Cortland -> log.info("" + Apple.Cortland);
            case RedDel -> log.info("" + Apple.RedDel);
        }

        // values of enumerations
        Apple[] apples = Apple.values();
        for (Apple ap : apples) {
            // ordinal - is an order number of element
            log.info("Apple[" + ap.ordinal() + "]: " + ap + " costs " + ap.getPrice());
        }

        // retrieve instance of enum element
        apple = Apple.valueOf("Cortland");
        log.info("Current apple is " + apple);

        // own enum methods
        double price = apple.getPrice();
        double cost = apple.getCost(0.2F);
        log.info("Apple[price vs cost]: {} vs {}", price, cost);
    }

    /**
     * Nested enumerations.
     */
    private void invokeNested() {
        // It is useful to define enums into classes & methods.

        enum Day {
            SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
            THURSDAY, FRIDAY, SATURDAY
        }

        log.info("Enumeration[days]: {}", Arrays.toString(Day.values()));
    }

    /**
     * Interface implementations.
     */
    private void invokeInterface() {
        // Enumeration can implement interfaces behaviour on every constant instance.

        double x = 5;
        double y = 2;
        log.info("* operation values: x = {}, y = {}", x, y);

        for (Operatable op : Operation.values()) {
            log.info(
                "{} {} {} = {}",
                x, op, y, op.apply(x, y)
            );
        }

        for (Operatable op : ExtendedOperation.values()) {
            log.info(
                "{} {} {} = {}",
                x, op, y, op.apply(x, y)
            );
        }
    }
}
