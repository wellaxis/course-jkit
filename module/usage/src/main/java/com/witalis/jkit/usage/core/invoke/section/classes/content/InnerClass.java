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
public class InnerClass {
    // static variable
    private static long index;
    public static char mode;
    // instance variable
    private int id;
    public String name;
    // association
    private Inner inner;

    static {
        index = 948445L;
        mode = 'T';
    }

    public InnerClass(int id, String name) {
        this.id = id;
        this.name = name;
        this.inner = new Inner(id, name);
    }

    public static long getIndex() {
        return index;
    }

    public static char getMode() {
        return mode;
    }

    // method can be instanced
    public int handle(Object object) {
        return Objects.hashCode(object);
    }

    @Data
    @ToString
    public class Inner {
        private int code;
        private String message;

        public Inner(int code, String message) {
            this.code = code;
            this.message = message;
        }

        // has access to static parameters
        public String processStatic() {
            return "" + InnerClass.getIndex() +
                "/" + InnerClass.mode +
                "|" + handle(InnerClass.class);
        }

        // has access to outer parameters
        public String processOuter() {
            return "" + InnerClass.this.getId() +
                "/" + InnerClass.this.name +
                "|" + handle(InnerClass.this);
        }

        public String processInner() {
            return "" + this.getCode() +
                "/" + this.message +
                "|" + handle(this);
        }
    }
}
