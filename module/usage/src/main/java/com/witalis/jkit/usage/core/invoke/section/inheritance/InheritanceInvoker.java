package com.witalis.jkit.usage.core.invoke.section.inheritance;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.inheritance.content.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Inheritance
 * User: Wellaxis
 * Date: 2019/11/17
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class InheritanceInvoker extends Invoker {

    public InheritanceInvoker() {
        setTitle("Inheritance chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // single
        log.info("## Single");
        invokeSingle();
        // tab
        log.info("");
        // multilevel
        log.info("## Multilevel");
        invokeMultilevel();
        // tab
        log.info("");
        // hierarchical
        log.info("## Hierarchical");
        invokeHierarchical();
        // tab
        log.info("");
        // multiple
        log.info("## Multiple");
        invokeMultiple();
        // tab
        log.info("");
        // hybrid
        log.info("## Hybrid");
        invokeHybrid();
        // tab
        log.info("");
        // casting
        log.info("## Casting");
        invokeCasting();
    }

    /**
     * Basic postulates of inheritance.
     */
    private void invokeBasis() {
        // Super class: the class whose features are inherited is known as superclass(or a base class or a parent class).
        // Subclass: the class that inherits the other class is known as a subclass(or a derived class, extended class, or child class).

        // Inheritance supports the concept of 're-usability'.

        log.info("---- Basic postulates");

        OneDimensional point = new OneDimensional(10);
        point.reflect();
        TwoDimensional square = new TwoDimensional(10, 20);
        square.reflect();
        ThreeDimensional volume = new ThreeDimensional(10, 20, 30);
        volume.reflect();
        FourDimensional location = new FourDimensional(10, 20, 30, 40);
        location.reflect();
    }

    /**
     * Single inheritance.
     */
    private void invokeSingle() {
        // In single inheritance, subclasses inherit the features of one superclass.

        log.info("---- Single inheritance");

        // In the image below, class A serves as a base class for the derived class B.

        class A {
            public void showA() {
                log.info("Show: A");
            }
        }

        class B extends A {
            public void showB() {
                log.info("Show: B");
            }
        }

        B b = new B();
        b.showA();
        b.showB();
    }

    /**
     * Multilevel inheritance.
     */
    private void invokeMultilevel() {
        // In multilevel inheritance, a derived class will be inheriting a base class
        // and as well as the derived class also act as the base class to other class.

        log.info("---- Multilevel inheritance");

        // In the below image, class A serves as a base class for the derived class B,
        // which in turn serves as a base class for the derived class C.

        class A {
            public void showA() {
                log.info("Show: A");
            }
        }

        class B extends A {
            public void showB() {
                log.info("Show: B");
            }
        }

        class C extends B {
            public void showC() {
                log.info("Show: C");
            }
        }

        C c = new C();
        c.showA();
        c.showB();
        c.showC();
    }

    /**
     * Hierarchical inheritance.
     */
    private void invokeHierarchical() {
        // In hierarchical inheritance, one class serves as a superclass (base class) for more than one subclass.

        log.info("---- Hierarchical inheritance");

        // In the below image, class A serves as a base class for the derived class B and C.

        class A {
            public void showA() {
                log.info("Show: A");
            }
        }

        class B extends A {
            public void showB() {
                log.info("Show: B");
            }
        }

        class C extends A {
            public void showC() {
                log.info("Show: C");
            }
        }

        B b = new B();
        b.showA();
        b.showB();

        C c = new C();
        c.showA();
        c.showC();
    }

    /**
     * Multiple inheritance.
     */
    private void invokeMultiple() {
        // In multiple inheritances, one class can have more than one superclass and inherit features from all parent classes.
        // Java does not support multiple inheritances with classes.
        // In java, we can achieve multiple inheritances only through interfaces.

        log.info("---- Multiple inheritance");

        // In the image below, Class D is derived from interface A, B and C.

        interface A {
            void showA();
        }

        interface B {
            void showB();
        }

        interface C extends A, B {
            void showA();
            void showC();
        }

        class D implements C {

            @Override
            public void showA() {
                log.info("Show: A");
            }

            @Override
            public void showB() {
                log.info("Show: B");
            }

            @Override
            public void showC() {
                log.info("Show: C");
            }
        }

        D d = new D();
        d.showA();
        d.showB();
        d.showC();
    }

    /**
     * Hybrid inheritance.
     */
    private void invokeHybrid() {
        // It is a mix of two or more of the above types of inheritance.
        // Since java doesn't support multiple inheritances with classes, hybrid inheritance is also not possible with classes.
        // In java, we can achieve hybrid inheritance only through Interfaces.

        // Diamond problem - default method of multiple interfaces with the same signature.

        log.info("---- Hybrid inheritance");

        interface A {
            void show();
            default void showExtra() {
                log.info("Show: extra A");
            }
        }

        interface B {
            void show();
            default void showExtra() {
                log.info("Show: extra B");
            }
        }

        class C implements A, B {

            @Override
            public void show() {
                log.info("Show: C");
            }

            @Override
            public void showExtra() {
                log.info("Show: extra C");
            }

            public void showExtraA() {
                A.super.showExtra();
            }

            public void showExtraB() {
                B.super.showExtra();
            }
        }

        C c = new C();
        c.show();
        c.showExtra();
        c.showExtraA();
        c.showExtraB();

        A ai = () -> log.info("Show: lambda A");
        ai.show();
        ai.showExtra();

        A a = c;
        a.show();
        a.showExtra();

        B bi = () -> log.info("Show: lambda B");
        bi.show();
        bi.showExtra();

        B b = c;
        b.show();
        b.showExtra();
    }

    /**
     * Typecasting of inheritance.
     */
    private void invokeCasting() {
        // Java allows to reference a subclass as an instance of its superclass,
        // essentially treating the subclass as if it were of the superclass type.
        // This process is known as typecasting.

        log.info("---- Typecasting inheritance");

        class A {
            public void showA() {
                log.info("Show: A");
            }
        }

        class B extends A {
            public void showB() {
                log.info("Show: B");
            }
        }

        // initialization
        B b = new B();
        // upcast
        A upcast = b;
        upcast.showA();
        // downcast
        B downcast = (B) upcast;
        downcast.showB();
    }
}
