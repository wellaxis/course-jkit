package com.witalis.jkit.usage.core.invoke.section.constructors;

import com.witalis.jkit.usage.core.invoke.Invoker;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: constructors declaration
 * User: Wellaxis
 * Date: 2022/10/07
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class ConstructorInvoker extends Invoker {

    public ConstructorInvoker() {
        setTitle("Constructors chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // declaration
        log.info("## Declaration");
        invokeDeclaration();
        // tab
        log.info("");
        // types
        log.info("## Types");
        invokeType();
        // tab
        log.info("");
        // method
        log.info("## Method");
        invokeMethod();
        // tab
        log.info("");
        // overloading
        log.info("## Overloading");
        invokeOverloading();
        // tab
        log.info("");
        // chaining
        log.info("## Chaining");
        invokeChaining();
        // tab
        log.info("");
        // copy
        log.info("## Copy");
        invokeCopy();
    }

    /**
     * Basic postulates of constructor.
     */
    private void invokeBasis() {
        // A constructor in Java is a block of code, similar to a method that is used to initialize the state of an object in a class through a new operator.

        log.info("Constructor is a special type of method that is used to initialize instance variables in a class.");

        // If we try to create an object of the class without specifying any constructor, JVM will create a constructor for us.
        // The sole purpose of the constructor is to initialize the data fields of objects in the class.
        // A constructor within a class allows constructing the object of the class at runtime.
        // It is invoked when an instance of a class is created using the new operator.

        log.info("Constructors can also accept arguments like methods and can be overloaded.");

        log.info("1. The task of a constructor in Java is to initialize instance variables of an object.");
        log.info("2. A constructor of class is automatically executed by JVM when an object is instantiated.");
        log.info("3. When an object of class is instantiated, constructor is called before any methods.");
        log.info("4. this and super keyword must be the first line in the constructor.");
        log.info("5. Constructor overloading is possible but overriding is not possible.");
        log.info("6. It cannot be inherited in the subclass.");
        log.info("7. A Constructor is not a keyword in Java.");
    }

    /**
     * Constructor declarations.
     */
    private void invokeDeclaration() {
        // Java constructor can be declared inside the class.

        log.info("Constructor has 5 parts: modifiers, class name, parameter list, exception list, and body.");

        // Use of constructor

        log.info("* The constructor is used in java programming to assign the default value of instance variables.");
        log.info("* Constructor is used to initializing objects of a class and allocate appropriate memory to objects.");
        log.info("* If you need to execute some code at the time of object creation, you can write them inside the constructor.");
        log.info("* Use of private constructor");

        // Call of constructor

        log.info("new -> It is a special keyword that allocates the memory to store objects whose type is specified by a constructor.");
        log.info("After allocation of memory, it calls constructor to initialize objects, which are stored in the heap.");

        // this keyword

        log.info("'this' reference can be used inside any method or constructor to refer to the current object.");

        // Private constructor

        log.info("Private constructor is a constructor with a private access modifier in a class.");

        // Private constructor is visible within the same class where it has been declared.
        // It is useful when we want to prevent users from creating an object of class from outside.
        // It can be used in single-tone classes where the object of the class cannot be created outside the class.

        log.info("1. A class cannot be extended when a constructor is declared as private.");
        log.info("2. We cannot create an instance of class when we declare constructor private.");
    }

    /**
     * Constructor types.
     */
    private void invokeType() {
        log.info("There are two basic types of constructors in Java: default & parameterized");

        log.info("Default constructor is a constructor that has no parameters.");
        log.info("When a class does not declare a constructor, Java compiler automatically creates a default constructor for that class.");

        log.info("Parameterized constructor is a constructor that takes 1 or more parameters.");
        log.info("To call the parameterized constructor, we pass arguments while creating the object.");

        // We cannot define two constructors with the same number of parameters and the same types.
    }

    /**
     * The differences with methods.
     */
    private void invokeMethod() {
        log.info("Difference between Constructor and Method:");

        log.info("1. Constructor is a special type of method that is used to initialize the state of an object.");
        log.info("2. It has no return type even void.");
        log.info("3. If class doesn't have any constructor, Java compiler creates a default constructor for this class.");
        log.info("4. Constructor name must be the same as name of the class.");
        log.info("5. Constructor is not inherited by subclasses.");
    }

    /**
     * Constructor overloading.
     */
    private void invokeOverloading() {
        // Overloading means more than one form. It refers to use of the same thing for a different purpose.

        log.info("Constructor overloading in Java means to define multiple constructors of a class but each one must have a different signature.");

        // Constructor overloading is a technique in Java in which a class can have more than one constructor that differ in the parameter lists.
        // Java compiler differentiates these constructors based on the number of the parameter lists and their types.

        log.info("The signature of a constructor consists of its name and sequence of parameter types.");

        // Constructor overloading allows initializing objects with different types of data.

        log.info("1. Overloaded constructors means more than one constructor of a class with different signatures.");
        log.info("2. To compile each constructor must have different parameter lists.");
        log.info("3. A parameter list consists of order and types of arguments.");
        log.info("4. We cannot have two constructors in a class with the same parameter lists.");
    }

    /**
     * Constructor chaining.
     */
    private void invokeChaining() {
        log.info("Constructor chaining is a technique of calling one constructor from within another constructor by using 'this' and 'super' keywords.");

        // The keyword 'this' is used to call a constructor from within another constructor in the same class.
        // The keyword 'super' is used to call the parent (super) class constructor from within child (subclass) class constructor.

        log.info("* Using this() keyword to call the current class constructor within the 'same class'.");
        log.info("* Using super() keyword to call the superclass constructor from the 'base class'.");

        // Order of execution

        // When we create a subclass object, the subclass constructor first calls its superclass constructor before performing its own tasks.
        // If the superclass is derived from another class, the superclass constructor calls its superclass constructor before performing its own tasks.

        log.info("1. We must use this() keyword to call the current class constructor.");
        log.info("2. We must use the super() keyword to call the superclass constructor.");
        log.info("3. Keywords this() or super() must be in the first line of the constructor.");
        log.info("3. There must be at least one constructor without this keyword.");
        log.info("4. Java Constructor chaining can be done in any order.");

        // Inheritance chaining

        log.info("If you do not put a super() keyword, JVM will put automatically the super() keyword.");
    }

    /**
     * Copy constructor.
     */
    private void invokeCopy() {
        log.info("Copy constructor is a constructor which is used to copy the data of one object to another object of the same class type.");

        // Copy constructor is called when a single object reference of the same class is passed as an argument.
        // A copy constructor simply takes a single argument whose type is class containing constructor.

        // Copy constructor is also called one-argument constructor.
        // By default, Java compiler does not create any copy constructor in a class.

        log.info("1. A copy constructor is used to declare and initialize an object from another object.");
        log.info("2. It can be used to copy data from one memory location to another memory location in Java.");

        // Copy constructor in Java provides an easy and attractive mechanism to make a copy (cloning)
        // of one object from another object of the same class type.
    }
}
