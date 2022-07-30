package com.witalis.jkit.usage.core.invoke.section.methods;

import com.witalis.jkit.usage.core.invoke.Invoker;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: methods declaration
 * User: Wellaxis
 * Date: 2022/10/07
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class MethodInvoker extends Invoker {

    public MethodInvoker() {
        setTitle("Methods chapter.");
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
        // signature
        log.info("## Signature");
        invokeSignature();
        // tab
        log.info("");
        // types
        log.info("## Types");
        invokeType();
        // tab
        log.info("");
        // main
        log.info("## Main");
        invokeMain();
        // tab
        log.info("");
        // parameters
        log.info("## Parameters");
        invokeParameter();
        // tab
        log.info("");
        // call
        log.info("## Call");
        invokeCall();
    }

    /**
     * Basic postulates of methods.
     */
    private void invokeBasis() {
        // In Java,  a method is a set of code used to write the logic of the applications which perform some specific tasks or operations.
        // The main() method is the first method that is executed by JVM (Java Virtual Machine) in java program.

        log.info("Methods in Java are the building block of a Java application.");
    }

    /**
     * Method declarations.
     */
    private void invokeDeclaration() {
        // A method must be declared inside the class.

        log.info("Method has 6 parts: modifiers, return type, method name, parameter list, exception list, and body.");

        // 1) The method modifier defines the access type of a method, but it is optional to use.
        // It may be declared as static, final, abstract, synchronized,
        // or one of the access specifiers: private, default, protected, and public.

        // 2) If the method is abstract, the implementation is omitted
        // and the method body is replaced with a single semicolon.

        // 3) The return type defines the type of data that comes back from the method after calling it.
    }

    /**
     * Method signature.
     */
    private void invokeSignature() {
        // Method signature - the method name with parameter list
        // (number of parameters, type of parameters, and order of parameters ).

        log.info("Method signature - the method name with parameter list.");

        // The parameter list is a comma-separated list of zero or more parameter declaration.
        // The parameter-modifier may be final. If the parameter is final, it cannot be modified inside the method.
        // Each parameter-name must be a distinct name.

        // The method body is a block-statement that contains statements
        // as well as the declaration of the local variables and local classes.

        log.info("1. The return type and exceptions are not part of the method signature.");
        log.info("2. The method name should be functionality name related to logic.");
        log.info("3. The exceptions may be thrown by the methods. In this case, you can specify the exceptions.");
    }

    /**
     * Method types.
     */
    private void invokeType() {
        log.info("There are two basic types of methods in Java: predefined & user-defined");

        log.info("Predefined methods in Java are those methods that are already defined in the Java API.");

        // Java has over 160 predefined packages that contain more than 3000 predefined classes.
        // These classes have many more thousands of individual predefined methods.

        log.info("User-defined methods in Java are those methods that are defined inside a class to perform a special task or function.");
        log.info("There are two types of user-defined methods: instance method & static method.");

        log.info("An instance method is used to implement behaviors of each object/instance of the class.");
        log.info("1. An instance method can refer to static variables (class variables) as well as for instance variables of the class.");
        log.info("2. Call the instance method with a reference variable using the dot operator.");
        log.info("3. Instance method uses dynamic binding (late binding).");

        // An instance method is also known as non-static method.
        // It is allocated in the heap memory during the object creation.

        log.info("A static method is a method with a static modifier. It is linked with class.");
        log.info("1. A static method can refer to only static variables.");
        log.info("2. To call a static method, use dot operator with the class name.");
        log.info("3. Class method uses static binding (early binding).");

        // A static method is also known as class method.
    }

    /**
     * The main method.
     */
    private void invokeMain() {
        log.info("A main() method in java is an entry point to start the execution of a program.");

        // Every Java application has at least one class and at least one main method.

        log.info("Syntax: public static void main(String[] args) { ... }");

        // class can have any number of main() methods but the execution always starts from
        // public static void main(String[ ] args) only.

        log.info("1. public: The public modifier makes it accessible from anywhere in the application.");
        log.info("2. static: The static modifier makes it a class method so that it can be called using the class name.");
        log.info("3. void: The return type of the main method is void which means that it does not return a value to its caller.");
        log.info("4. main: It is the name of a method where execution will start. In Java, the main method is called by JVM.");
        log.info("5. String[ ] args: The main method accepts one argument of type String array (String[ ] or String ...).");
        log.info("6. { body }: The region between the opening and closing brace that contains a set of program statements.");

        // It is possible to overload the main() method, but we cannot override it.
        // We can declare any number of main() method in a class, but the method signature must be different.
    }

    /**
     * Method parameters.
     */
    private void invokeParameter() {
        log.info("Arguments in Java are the actual values that are passed to variables defined in the method header.");

        // The passed argument values replace those parameters which have been used during method definition
        // and the body of method is then executed with these values. Sometimes, an argument is also called actual parameter.

        log.info("Parameter is a variable name with type that is declared within the method signature.");

        // Each parameter consists of two parts: type name, and variable name. It is also often called formal parameter.
        // Parameters declared in the method signature are always local variables that receive values when the method is called.

        // There is no standard limit to specify the number of parameters in the definition of a method.
        // But you should limit the number of parameters almost 6 to 7 (negative effect on the readability).

        log.info("1. Variables defines in the method header are called formal parameters or simply parameters.");
        log.info("2. Parameters in a method header are optional; that is, a method may or may not contain parameters.");
        log.info("3. If a method contains parameters, we need to pass values (arguments) to parameters when a method is invoked.");
        log.info("4. A method header represents modifiers, return type value, method name, and parameters of the method.");
        log.info("5. The method name and parameter list together is called method signature.");
        log.info("6. Parameters defined in the method header are always local variables.");
    }

    /**
     * Method colling.
     */
    private void invokeCall() {
        // Call by Value vs Call by Reference.

        log.info("In Java, all argument values are passed to a method using only call by value (pass by value) mechanism.");

        // "Call by value" in Java means that argument's value is copied and is passed to the parameter list of a method.
        // The values of the parameters can be modified only inside the scope of the method
        // but such modification inside the method doesn't affect the original passing argument.

        log.info("There is no call by reference (pass by reference) mechanism in Java.");

        // In Java "Call by Reference" means passing a reference (i.e. address) of the object by value to a method.
        // We know that a variable of class type contains a reference (i.e. address) to an object, not object itself.

        log.info("1. Java passes the arguments both primitives and the copy of object reference by value.");
        log.info("2. Java never passes the object itself.");
        log.info("3. In realtime project, we pass class objects as parameters to method call in java.");

        // Java supports the inner class concept but does not support the inner method concept.

        log.info("In Java, return is a keyword that is used to exit from the method only with or without returning a value.");
    }
}
