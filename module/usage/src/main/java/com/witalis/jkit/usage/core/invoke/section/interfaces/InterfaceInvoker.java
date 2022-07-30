package com.witalis.jkit.usage.core.invoke.section.interfaces;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.interfaces.content.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Interfaces
 * User: Wellaxis
 * Date: 2019/11/17
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class InterfaceInvoker extends Invoker {

    public InterfaceInvoker() {
        setTitle("Interface chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // default
        log.info("## Nested interface");
        invokeNested();
        // tab
        log.info("");
        // default
        log.info("## Default methods");
        invokeDefault();
        // tab
        log.info("");
        // static
        log.info("## Static methods");
        invokeStatic();
        // tab
        log.info("");
        // private
        log.info("## Private methods");
        invokePrivate();
        // tab
        log.info("");
        // diamond
        log.info("## Diamond Problem");
        invokeDiamond();
    }

    /**
     * Basic postulates of interfaces.
     */
    private void invokeBasis() {
        // Standard interface - in own file
        // Class can maintain state information, but an interface cannot.

        // interface constants
        int parameter = SharedConstants.PARAMETER;
        log.info("Interface[constant]: {}", parameter);

        // interface implementation - anonymous
        Call interfaceCaller = new Call() {

            @Override
            public void call(int parameter) {
                log.info("Interface[parameter]: {}", parameter);
            }
        };
        interfaceCaller.call(200);

        // lambda implementation
        Call lambdaCaller = (n) -> log.info("Lambda[parameter], {}", n);
        lambdaCaller.call(300);

        // abstract class implementation
        Call abstractCaller = new AbstractCaller() {

            @Override
            public void call(int parameter) {
                log.info("Abstract[parameter]: {}", parameter);
            }
        };
        abstractCaller.call(400);

        // class implementation
        Call classCaller = new Caller();
        classCaller.call(500);
    }

    /**
     * Nested interface.
     */
    private void invokeNested() {

        // nested interface - into class
        interface Callback {
            String callback();
        }

        // nested class - implements interface
        class Calling implements Callback {
            private String message;

            public Calling(String message) {
                this.message = message;
            }

            @Override
            public String callback() {
                return message;
            }
        }

        Callback calling = new Calling("Nested Definition");
        log.info("Interface[nested]: {}", calling.callback());
    }

    /**
     * Default methods.
     */
    private void invokeDefault() {

        class DefaultOne implements CallDefault {
        }

        class DefaultTwo implements CallDefault {

            @Override
            public String getMessage() {
                return "It's a class with overridden method.";
            }
        }

        CallDefault defaultOne = new DefaultOne();
        log.info("Default[1st]: {}", defaultOne.getMessage());

        CallDefault defaultTwo = new DefaultTwo();
        log.info("Default[2nd]: {}", defaultTwo.getMessage());
    }

    /**
     * Static methods.
     */
    private void invokeStatic() {

        class StaticOne implements CallStatic {
        }

        class StaticTwo implements CallStatic {

            // shadowing of functionality
            static int positiveNumber(int number) {
                return Math.abs(number * number);
            }
        }

        CallStatic staticOne = new StaticOne();
        var original = CallStatic.positiveNumber(100);
        // StaticOne.positiveNumber(200); // unable to invoke
        log.info("Static[1st]: {}", original);

        CallStatic staticTwo = new StaticTwo();
        var shadowing = StaticTwo.positiveNumber(200);
        log.info("Static[2nd]: {}", shadowing);
    }

    /**
     * Private methods.
     */
    private void invokePrivate() {

        class PrivateOne implements CallPrivate {
        }

        // privateOne.retrieveRandom(); // private method
        // CallPrivate.getRandom(); // private static method

        CallPrivate privateOne = new PrivateOne();
        int i = privateOne.randomInt();
        log.info("Private[1st]: {}", i);
        char c = CallPrivate.randomChr();
        log.info("Private[2nd]: {}", c);
    }

    /**
     * Diamond problem.
     */
    private void invokeDiamond() {
        // Java does not allow multiple inheritance for classes
        // so diamond problem can not exist in Java via interfaces

        interface A {
            default void foo() {
                log.info("Interface-A");
            }
        }

        interface B extends A {
            default void foo() {
                log.info("Interface-B");
            }
        }

        interface C extends A {
            default void foo() {
                log.info("Interface-C");
            }
        }

        // interfaces have the same method - foo
        // class overrides method to its own version
        class D implements B, C {
            @Override
            public void foo() {
                log.info("Class-D");
            }
        }

        // interfaces have the same method - foo
        // to invoke default methods use super keyword
        class E implements B, C {
            @Override
            public void foo() {
                B.super.foo();
                C.super.foo();
            }
        }

        D overriddenFoo = new D();
        overriddenFoo.foo();

        log.info("-----------");

        E superFoo = new E();
        superFoo.foo();
    }
}
