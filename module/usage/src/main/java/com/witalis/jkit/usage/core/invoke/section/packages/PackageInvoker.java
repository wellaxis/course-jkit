package com.witalis.jkit.usage.core.invoke.section.packages;

import com.witalis.jkit.usage.core.invoke.Invoker;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Java packages
 * User: Wellaxis
 * Date: 2022/06/19
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class PackageInvoker extends Invoker {

    public PackageInvoker() {
        setTitle("Package chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // naming
        log.info("## Naming");
        invokeNaming();
        // tab
        log.info("");
        // import
        log.info("## Import");
        invokeImport();
    }

    /**
     * Basic postulates of packages.
     */
    private void invokeBasis() {
        // In Java, APIs consist of one or more packages where packages consist of many classes,
        // classes contain several methods and fields.

        log.info(
            "A package is nothing but a physical folder structure (directories) that contains " +
            "a group of related classes, interfaces, and sub-packages according to their functionality."
        );

        // Advantage of using packages in Java:
        // * maintenance   [Java packages are used for proper maintenance]
        // * reusability   [after place the code in a folder, everybody can check that folder and use it]
        // * name conflict [packages help to resolve the naming conflict between the classes]
        // * organized     [it helps in organizing the files within the project]
        // * protection    [package provides access protection, it can be used to provide visibility control]

        log.info("Advantage of using packages in Java: maintenance, reusability, name conflict, organized, protection");

        // Types of Packages in Java:
        // * user-defined [the package which is defined by the user: contains user-defined classes and interfaces]
        // * built-in     [predefined packages in java are those which are developed by Sun Microsystem]

        log.info("Java supports a keyword 'package' which is used to create user-defined packages.");
        log.info("Java 8 contains: 14 packages, 150 sub-packages, 7000 classes, 700000 methods");

        // Compile the application:
        log.info("Compile: javac -d directory java-file-name");
    }

    /**
     * Package naming convention.
     */
    private void invokeNaming() {
        // Naming convention to declare package, (f.e. package com.ibm.itc.b2b.finance):
        // 1. Company specification [com]
        // 2. Company name          [ibm]
        // 3. Client name           [itc]
        // 4. Project name          [b2b]
        // 5. Module name           [finance]

        log.info("Convention: package specification.company.client.project.module");
    }

    /**
     * Package importing.
     */
    private void invokeImport() {
        // There are three approaches to import one package into another package in Java:
        // 1) import package.*;          [all the classes and interfaces of this package can be accessed (imported)]
        // 2) import package.classname;  [you can only access the declared class of this package]
        // 3) using fully qualified name [there is no need of using an import statement (only the declared class can be accessible)]

        log.info("There are 3 approaches to import classes: package.*, package.classname, fully qualified name");

        // Key points:
        log.info("1. Package declaration must be the first statement and followed by package import.");
        log.info("2. A class can have only one package statement but it can be more than one import package statement.");
        log.info("3. import can be written multiple times after the package statement and before the class statement.");
        log.info("4. Declare the package with root folder name (no subfolder name) and the last file name must be class name with a semicolon.");
        log.info("5. When you import, it does mean that memory is allocated. It just gives the path to reach the file.");
        log.info("6. `import com.dev.class` is always better than `import com.dev.*`");
    }
}
