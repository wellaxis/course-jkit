package com.witalis.jkit.usage.core.invoke.section.blocks;

import com.witalis.jkit.usage.core.invoke.Invoker;

import com.witalis.jkit.usage.core.invoke.section.blocks.content.BlockHolder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: blocks declaration
 * User: Wellaxis
 * Date: 2022/10/07
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class BlockInvoker extends Invoker {

    public BlockInvoker() {
        setTitle("Blocks chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // local
        log.info("## Local");
        invokeLocal();
        // tab
        log.info("");
        // instance
        log.info("## Instance");
        invokeInstance();
        // tab
        log.info("");
        // static
        log.info("## Static");
        invokeStatic();
        // tab
        log.info("");
        // scenario
        log.info("## Scenario");
        invokeScenario();
    }

    /**
     * Basic postulates of block.
     */
    private void invokeBasis() {
        log.info("Block in Java is a set of code enclosed within curly braces { } within any class, method, or constructor.");

        // Block begins with an opening brace '{' and ends with an closing braces '}'.

        log.info("In Java, every class has a class block that groups the data and methods inside the class.");
        log.info("In Java, every method has a method block that contains a group of statements.");

        log.info("There are 3 types of blocks in Java: local, instance initialization, static initialization");
    }

    /**
     * Local block.
     */
    private void invokeLocal() {
        log.info("Local block is a block defined inside a method, block, or constructor.");

        log.info("All the variables declared inside a block are local variables (they can be accessed only within that block).");

        {
            // local block
            {
                // nested local block
            }
        }

        log.info("Local block:");
        log.info("1. It is also called inner block in Java.");
        log.info("2. It is not similar to the instance block.");
        log.info("3. It cannot be static.");
        log.info("4. It can also be nested.");
        log.info("5. It can never overlap.");
    }

    /**
     * Instance initialization block.
     */
    private void invokeInstance() {
        log.info("An instance initialization block (IIB) is also known as non-static block.");

        // Instance blocks will be executed before the execution of any constructor.
        // Constructor logic is specific to an object but IIB logic is common for all objects.

        log.info("Instance initialization block:");
        log.info("1. It is used to write that logic which we want to execute during the object creation.");
        log.info("2. Instance block cannot take any argument (cannot replace constructor concept).");
        log.info("3. It will be executed after the execution of the static block if any static block is declared inside the class.");
        log.info("4. Static and Non-static variables can be accessed inside the non-static block.");
        log.info("5. Instance block is executed during the object creation before the constructor execution.");
        log.info("6. The order of the execution of instance block will always be towards the top to bottom.");
        log.info("7. The memory is allocated for instance block during object creation.");

        // Advantage:
        // 1. Instance block is used to write that logic which has to be executed during object creation before the execution of constructor.
        // 2. It is used to initialize the value to a variable.
    }

    /**
     * Static initialization block.
     */
    private void invokeStatic() {
        log.info("A static initialization block (SIB) is a block declared with the static keyword.");

        log.info("The static block gets executed only once by JVM when the class is loaded into the memory by Java ClassLoader.");

        // Static block always gets executed first in Java because it is stored in the memory at the time of class loading and before the object creation.

        log.info("Static initialization block:");
        log.info("1. Static initialization block is executed before the main method (after loading class file into memory).");
        log.info("2. Static initialization blocks will execute in the same sequence as written in the program.");
        log.info("3. Static block cannot access instance (non-static) variables and methods.");
        log.info("4. SIB is used to write logic that you want to execute during the class loading.");
        log.info("5. SIB is used to initialize static variables of the class.");
        log.info("6. A static block in java can not be declared inside any method.");

        // It is possible to execute static block without a main method inside the class up to Java 1.5 version.
    }

    /**
     * Block scenarios.
     */
    private void invokeScenario() {
        // block holder
        {
            log.info("1. Block Holder");
            BlockHolder holder = new BlockHolder(50);
            BlockHolder.print(holder);
        }
    }
}
