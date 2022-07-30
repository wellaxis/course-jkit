package com.witalis.jkit.usage.core.invoke.section.modifiers.content.statics;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Static example
 * User: Wellaxis
 * Date: 4/12/2022
 */
@Slf4j
public class StaticExample {
    // static variable
    public static int counter;

    // static initialization block
    static {
        counter = 100;
    }

    // static nested class
    static class Nested {
        public static String code;

        public Nested() {
            super();
        }

        public static void initialise(String code) {
            Nested.code = code;
        }
    }

    // static method
    public static int getCounter() {
        return counter;
    }
}
