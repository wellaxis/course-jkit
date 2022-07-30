package com.witalis.jkit.usage.core.invoke.section.modules;

import com.witalis.jkit.usage.core.invoke.Invoker;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: modules
 * User: Wellaxis
 * Date: 2019/11/20
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class ModuleInvoker extends Invoker {

    public ModuleInvoker() {
        setTitle("Modules chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // scenario
        log.info("## Scenario");
        invokeScenario();
    }

    /**
     * Jigsaw: context-sensitive (reserved words in module-info class only).
     * <p/>
     * module     - definition of a module into file: module-info.java
     * requires   - (depends relationship) one module depends on another [reads]
     * transitive - [requires] allows modules to declare such dependencies in intermediate modules [encapsulation].
     * exports    - access control for another module (the whole module or its packages)
     * to         - [export] a list of modules separated by commas - there is a restriction of modules that can use the exported module.
     * provides   - service provider - defines a module as an implementation of a service. That is, the module becomes a "service provider".
     * with       - the "provides" part defines an interface or abstract class, and the "with" part defines a class that implements the promised functionality.
     * uses       - service consumer - the directive defines the service used by the module. The module then becomes a "service consumer".
     * opens      - the directive will make the package and classes available at runtime. In addition, all the contents of the package will be available through reflection.
     * open       - The most radical way to open reflection in a module is to declare the entire module "open" - all packages and classes will be available for reflection.
     */
    private void invokeBasis() {
        // Since Java 9 - Jigsaw: introduces a new level of abstraction above packages,
        // formerly known as the Java Platform Module System (JPMS), or “Modules” for short.

        log.info("Module is a group of closely related packages and resources along with a new module descriptor file.");

        // Each module is responsible for its resources, like media or configuration files.

        // Descriptor file defines several aspects of module:
        // * Name – the name of module
        // * Dependencies – a list of other modules that module depends on
        // * Public packages – a list of all packages want accessible from outside the module
        // * Services offered – can provide service implementations that can be consumed by other modules
        // * Services consumed – allows the current module to be a consumer of a service
        // * Reflection permissions – explicitly allows other classes to use reflection to access the private members

        log.info("There are 4 types of modules: system, application, automatic, unnamed.");

        // 1) System modules – the modules listed when we run the list-modules command above.
        //     They include the Java SE and JDK modules.
        // 2) Application modules – the modules we usually want to build when we decide to use modules.
        //     They are named and defined in the compiled module-info.class file included in the assembled JAR.
        // 3) Automatic modules – we can include unofficial modules by adding existing JAR files to the module path.
        //     The name of the module will be derived from the name of the JAR.
        //     Automatic modules will have full read access to every other module loaded by the path.
        // 4) Unnamed module – when a class or JAR is loaded onto the classpath, but not the module path.
        //     It's automatically added to the unnamed module.
        //     It's a catch-all module to maintain backward compatibility with previously-written Java code.
    }

    private void invokeScenario() {
        log.info("It's necessary to specify several application modules.");
    }
}
