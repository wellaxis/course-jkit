package com.witalis.jkit.usage.core.invoke.section.classes.content;

import lombok.Data;
import lombok.ToString;

import java.util.Objects;

/**
 * Desc:
 * User: Wellaxis
 * Date: 4/11/2022
 */
@Data
@ToString
public class NestedClass {
    // static variable
    private static long index;
    public static char mode;
    // instance variable
    private int id;
    public String name;
    // association
    private Nested nested;

    static {
        index = 835672L;
        mode = 'W';
    }

    public NestedClass(int id, String name) {
        this.id = id;
        this.name = name;
        this.nested = new Nested(id, name);
    }

    public static long getIndex() {
        return index;
    }

    public static char getMode() {
        return mode;
    }

    // method should be static
    public static int handle(Object object) {
        return Objects.hashCode(object);
    }

    @Data
    @ToString
    public static class Nested {
        private int code;
        private String message;

        public Nested(int code, String message) {
            this.code = code;
            this.message = message;
        }

        // has access to static parameters
        public String processStatic() {
            return "" + NestedClass.getIndex() +
                "/" + NestedClass.mode +
                "|" + handle(NestedClass.class);
        }
        // has no access to outer parameters
        public String processOuter() {
            return "== No access ==";
        }

        public String processInner() {
            return "" + this.getCode() +
                "/" + this.message +
                "|" + handle(this);
        }
    }
}
