package com.witalis.jkit.usage.core.invoke.section.overriding.content;

import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

/**
 * Desc: Base class
 * User: Wellaxis
 * Date: 4/10/2022
 */
@Slf4j
public class Parent {

    protected String prefix() {
        return "Parent: ";
    }

    // Overridden

    // usual behaviour
    public void overrideUsual(String text, int value, Boolean status) {
        log.info(prefix() + "text {}, value {}, status {}", text, value, status);
    }

    // parameter names
    public void overrideParam(Long argument) {
        log.info(prefix() + argument);
    }

    // modifier type
    protected void overrideModifier(String... args) {
        log.info(prefix() + Arrays.asList(args));
    }

    // return value
    public Number overrideReturn(String arg) {
        log.info(prefix() + arg);
        return Integer.parseInt(arg);
    }

    // throwing exceptions
    public String overrideException(String arg, String def) throws ParseException, IOException {
        var value = arg != null ? arg : def;
        log.info(prefix() + value);
        return value;
    }

    // Non Overridden

    // private method - not overridden
    private String nonOverridePrivate(String argument) {
        log.info(prefix() + argument);
        return argument;
    }

    // final method - not overridden
    public final String nonOverrideFinal(String argument) {
        log.info(prefix() + argument);
        return argument;
    }

    // static method - not overridden
    public static String nonOverrideStatic(String argument) {
        log.info("Parent: " + argument);
        return argument;
    }
}
