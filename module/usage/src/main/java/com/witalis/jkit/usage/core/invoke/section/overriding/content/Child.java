package com.witalis.jkit.usage.core.invoke.section.overriding.content;

import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;

/**
 * Desc: Derived class
 * User: Wellaxis
 * Date: 4/10/2022
 */
@Slf4j
public class Child extends Parent {

    @Override
    protected String prefix() {
        return "Child: ";
    }

    // usual behaviour
    @Override
    public void overrideUsual(String text, int value, Boolean status) {
        log.info(prefix() + "text {}, value {}, status {}", text, value, status);
    }

    // parameter names [names can be changed]
    @Override
    public void overrideParam(Long parameter) {
        log.info(prefix() + parameter);
    }

    // modifier type extension [package -> protected -> public]
    @Override
    public void overrideModifier(String... args) {
        log.info(prefix() + Arrays.asList(args));
    }

    // return value constriction [Object -> Number -> Integer]
    @Override
    public Integer overrideReturn(String arg) {
        log.info(prefix() + arg);
        return Integer.parseInt(arg);
    }

    // throwing exceptions [can eliminate and/or add different]
    @Override
    public String overrideException(String arg, String def) {
        var value = arg != null ? arg : def;
        log.info(prefix() + value);
        return value;
    }

    // static method - shadowing (call by class)
    public static String nonOverrideStatic(String argument) {
        log.info("Child: " + argument);
        return argument;
    }
}
