package com.witalis.jkit.usage.core.invoke.section.variable;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.constant.content.ClassConstant;
import com.witalis.jkit.usage.core.invoke.section.constant.content.InterfaceConstant;
import com.witalis.jkit.usage.core.invoke.section.constant.content.LambdaConstant;

import com.witalis.jkit.usage.core.invoke.section.variable.content.InstanceExample;
import com.witalis.jkit.usage.core.invoke.section.variable.content.LocalExample;
import com.witalis.jkit.usage.core.invoke.section.variable.content.StaticExample;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Variables
 * User: Wellaxis
 * Date: 2019/11/17
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class VariableInvoker extends Invoker {

    public VariableInvoker() {
        setTitle("Variables chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // local
        log.info("## Local Variables");
        invokeLocal();
        // tab
        log.info("");
        // instant
        log.info("## Instant Variables");
        invokeInstant();
        // tab
        log.info("");
        // class
        log.info("## Static (Class) Variables");
        invokeStatic();
    }

    /**
     * Basic postulates of constant values.
     */
    private void invokeBasis() {
        // Variable is a container that holds the value during the execution of program.
        // Variables must be declared to the java compiler before they can be used in the program.

        log.info("Variable is the name of the memory location reserved for storing value.");

        // Naming convention for the declaring variables:
        // 1. The variable name should start with a lower case letter (others - in camelCase).
        // 2. The variable name should not contain a blank space.
        // 3. The variable name can begin with a special character such as $ and _.
        // 4. The first character must be a letter.
        // 5. Java keywords should not be used as a variable name.
        // 6. The variable names are case-sensitive in Java.

        log.info("Variables: name, addressLine, $price, _temporary, etc.");

        // Scope of a variable means simply the region of the program where a variable is accessible.
    }

    /**
     * Local variables.
     */
    private void invokeLocal() {
        // Local variable - a variable that is declared and used inside the body of methods, constructors, or blocks.

        // 1. Local variables are not available for use from outside.
        // 2. Local variables must be assigned a value at the time of creating.
        // 3. No access modifiers can be used with local variables (but can be final).
        // 4. The local variables are visible only within the declared constructor, method, or block.
        // 5. A local variable is not equivalent to an instance variable.
        // 6. A local variable cannot be static.

        log.info("Local variables are stored in stack memory.");

        // They are visible only within the method, constructor, or block.
        // We cannot access local variables from outside the method, constructor, or block.

        log.info("Scope of local variables: inside a method, constructor or block.");

        LocalExample localExample = new LocalExample();
        localExample.information();
    }

    /**
     * Instant variables.
     */
    private void invokeInstant() {
        // Instance variable - a variable that is declared inside the class but outside the body of the method, constructor, or any block.

        // 1. They are available for the entire class methods, constructors, and blocks.
        // 2. Instance variables are created when an object is created using the keyword 'new' and destroyed when the object is destroyed.
        // 3. We can also use access modifiers with instance variables.
        // 4. It is not necessary to initialize the instance variable.

        log.info("Instance variables are stored in heap memory.");

        // They are visible inside all the methods, constructors, and from the beginning of its program block to the end of program block in the class.
        // All the methods, constructors, and blocks inside the class can access instance variables.

        log.info("Scope of instance variables: inside the class.");

        InstanceExample instanceExample = new InstanceExample(1, 25L, "secret");
        instanceExample.information();
    }

    /**
     * Static (class) variables.
     */
    private void invokeStatic() {
        // Static variable - a variable which is declared with a static keyword.

        // 1. A static variable is also called class variable because it is associated with the class.
        // 2. Static variables are always declared inside the class but outside any methods, constructors, or blocks.
        // 3. Static variable will get the memory only once.

        log.info("Instance variables are stored in metaspace memory.");

        // All the methods, constructors, and blocks inside the class can access static variables by using the class name.
        // The visibility of the static variable is similar to the instance variable.

        log.info("Scope of static variables: within the class.");

        StaticExample staticExample = new StaticExample();
        StaticExample.information();
    }
}
