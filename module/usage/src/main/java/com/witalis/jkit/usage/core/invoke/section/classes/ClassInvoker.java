package com.witalis.jkit.usage.core.invoke.section.classes;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.classes.content.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Java tokens
 * User: Wellaxis
 * Date: 2021/04/01
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class ClassInvoker extends Invoker {

    public ClassInvoker() {
        setTitle("Class chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // initialization
        log.info("## Initialization");
        invokeInitialization();
        // tab
        log.info("");
        // abstract
        log.info("## Abstract");
        invokeAbstract();
        // tab
        log.info("");
        // anonymous
        log.info("## Anonymous");
        invokeAnonymous();
        // tab
        log.info("");
        // final
        log.info("## Final");
        invokeFinal();
        // tab
        log.info("");
        // nested
        log.info("## Normal Inner");
        invokeInner();
        // tab
        log.info("");
        // nested
        log.info("## Static Nested");
        invokeNested();
    }

    /**
     * Basic postulates of classes.
     */
    private void invokeBasis() {
        // According to OOPs concept in Java, a class is the blueprint/template of an object.

        log.info("Class in Java is a fundamental building block of object-oriented programming (OOP) language.");

        // Some common types of classes in Java:
        // * wrapper, mutable, abstract, final, anonymous, string, system, etc.

        ConcreteClass sample = new ConcreteClass(1L, "Object");
        log.info("Class[sample]: {}", sample);
    }

    /**
     * Object initialization.
     */
    private void invokeInitialization() {
        // via new keyword
        ConcreteClass sample = new ConcreteClass(2L, "New");
        log.info("Class[new]: {}", sample);

        // via reflection
        try {
            Class<?> clazz = Class.forName("com.witalis.jkit.usage.core.invoke.section.classes.content.ConcreteClass");
            Object object = clazz.getDeclaredConstructor(long.class, String.class).newInstance(3L, "Reflection");
            ConcreteClass reflection = (ConcreteClass) object;
            log.info("Class[reflection]: {}", reflection);
        } catch (Exception e) {
            log.error("Reflection initialization failure", e);
        }

        // anonymous object
        String information = new ConcreteClass(4L, "Anonymous").getInformation();
        log.info("Class[anonymous]: {}", information);
    }

    /**
     * Abstract class as a template.
     */
    private void invokeAbstract() {

        // abstract class
        abstract class Message {
            private String message;

            protected Message(String message) {
                this.message = message;
            }

            public String getMessage() {
                return message;
            }

            abstract void send();
        }

        // concrete class
        class MailMessage extends Message {

            public MailMessage(String message) {
                super(message);
            }

            @Override
            void send() {
                log.info("Send mail message: {}", getMessage());
            }
        }

        Message mailMessage = new MailMessage("dear all!");
        mailMessage.send();
    }

    /**
     * Anonymous implementations.
     */
    private void invokeAnonymous() {
        // 1. A class that does not have named is called anonymous class in Java. It can be defined inside a method without a name.
        // 2. The object of an anonymous class is created in the same place where it is defined. It cannot have explicit constructors.
        // 3. An object that does not have reference variable is called anonymous object. It is not stored in a variable.
        // 4. Java anonymous object creation is useful when it is not used more than once.

        // existing class
        new ConcreteClass(5L, "Anonymous");

        // based on interface or abstract class
        interface Person {
            void perform();
        }

        Person person = new Person() {
            @Override
            public void perform() {
                log.info("A person performs something...");
            }
        };
        person.perform();
    }

    /**
     * Final classes
     */
    private void invokeFinal() {
        // When a class is declared with final keyword, it is called final class in Java.
        // Final class means Restricting Inheritance!. It does not allow itself to be inherited by another class.

        FinalClass finalClass = new FinalClass(100);
        log.info("Class[final]: {}", finalClass);

        // class Child extends FinalClass {} // cannot inherit from final class
    }

    /**
     * Normal inner classes.
     */
    private void invokeInner() {
        // Outer class does not have access to inner class parameters.
        // But inner class has access to outer parameters (even private).

        // Use of inner classes is a solution of multiple inheritance.

        InnerClass innerClass = new InnerClass(1, "Outer");
        log.info("Class[wrapper]: {}", innerClass);

        InnerClass.Inner inner = innerClass.new Inner(10, "Inner");
        log.info("Class[inner]: {}", inner);

        // access to fields and methods
        log.info("Inner[static]: {}", inner.processStatic());
        log.info("Inner[wrapper]: {}", inner.processOuter());
        log.info("Inner[inner]: {}", inner.processInner());
    }

    /**
     * Static nested classes.
     */
    private void invokeNested() {
        // The only real reason of use of static nested classes is such
        // a class has access to its containing class's private static members.

        NestedClass nestedClass = new NestedClass(1, "Outer");
        log.info("Class[wrapper]: {}", nestedClass);

        NestedClass.Nested nested = new NestedClass.Nested(10, "Nested");
        log.info("Class[nested]: {}", nested);

        // access to fields and methods
        log.info("Nested[static]: {}", nested.processStatic());
        log.info("Nested[wrapper]: {}", nested.processOuter());
        log.info("Nested[nested]: {}", nested.processInner());
    }
}
