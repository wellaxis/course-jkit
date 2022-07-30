package com.witalis.jkit.usage.core.invoke.section.modifiers;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.modifiers.content.abstracts.AbstractExample;
import com.witalis.jkit.usage.core.invoke.section.modifiers.content.abstracts.ConcreteExample;
import com.witalis.jkit.usage.core.invoke.section.modifiers.content.defaults.DefaultConstructor;
import com.witalis.jkit.usage.core.invoke.section.modifiers.content.defaults.DefaultMembers;
import com.witalis.jkit.usage.core.invoke.section.modifiers.content.finals.FinalExample;
import com.witalis.jkit.usage.core.invoke.section.modifiers.content.natives.NativeExample;
import com.witalis.jkit.usage.core.invoke.section.modifiers.content.privates.PrivateConstructor;
import com.witalis.jkit.usage.core.invoke.section.modifiers.content.privates.PrivateMembers;
import com.witalis.jkit.usage.core.invoke.section.modifiers.content.protecteds.ProtectedConstructor;
import com.witalis.jkit.usage.core.invoke.section.modifiers.content.protecteds.ProtectedMembers;
import com.witalis.jkit.usage.core.invoke.section.modifiers.content.publics.PublicConstructor;
import com.witalis.jkit.usage.core.invoke.section.modifiers.content.publics.PublicMembers;
import com.witalis.jkit.usage.core.invoke.section.modifiers.content.statics.StaticExample;
import com.witalis.jkit.usage.core.invoke.section.modifiers.content.strictfps.StrictfpExample;
import com.witalis.jkit.usage.core.invoke.section.modifiers.content.synchronizeds.SynchronizedExample;
import com.witalis.jkit.usage.core.invoke.section.modifiers.content.transients.TransientExample;
import com.witalis.jkit.usage.core.invoke.section.modifiers.content.volatiles.VolatileExample;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Desc: Modifiers
 * User: Wellaxis
 * Date: 2019/11/17
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class ModifierInvoker extends Invoker {

    public ModifierInvoker() {
        setTitle("Modifiers chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // access
        log.info("## Access Modifiers");
        invokeAccess();
        // tab
        log.info("");
        // non access
        log.info("## Non Access Modifiers");
        invokeNonAccess();
    }

    /**
     * Basic postulates of constant values.
     */
    private void invokeBasis() {
        // Modifier is a keyword that we add to those definitions that we need to change their meaning.

        log.info("Modifier limits the visibility of classes, fields, constructors, or methods in the Java program.");

        // Twelve modifiers in java can be divided into two categories:
        // * Access modifiers
        // * Non-access modifiers
    }

    /**
     * Access modifiers.
     */
    private void invokeAccess() {
        // Access modifiers/specifiers in java define the boundary for accessing members of a class and a class itself.
        // They used to restrict the visibility (accessibility) of classes, fields, methods, or constructors.

        log.info("Visibility of access modifiers: private -> default -> protected -> public");

        // private
        log.info("---- Private Access");
        invokePrivate();
        // tab
        log.info("");
        // package
        log.info("---- Package Access");
        invokePackage();
        // tab
        log.info("");
        // protected
        log.info("---- Protected Access");
        invokeProtected();
        // tab
        log.info("");
        // public
        log.info("---- Public Access");
        invokePublic();
    }

    /**
     * Private access modifier.
     */
    private void invokePrivate() {
        // 1. Private access modifier in java can apply to a variable, method, constructor, inner class but not the outer class that is class itself.
        // 2. The instance variable can be private but a local variable cannot be private.
        // 3. Private members (field, method, or constructor) of a class cannot be accessed from outside the class.
        // 4. Private members of a superclass cannot be inherited to the subclass.
        // 5. If we make any constructor as private, we cannot create an object of that class from another class.
        // 6. A class cannot be private except for inner classes. Inner classes are members of the outer class.
        // 7. We cannot call the private method from outside the class (it behaves as final method).

        // unable to get access to private variables/methods
        PrivateMembers privateMembers = new PrivateMembers(1);
        // int code = privateMembers.code;
        // String message = privateMembers.getMessage();

        // unable to instantiate a class with private constructor
        PrivateConstructor privateConstructor;
        // privateConstructor = new PrivateConstructor();

        // inner & local environment are permitted for access
        class PrivateLocal {
            private int id = 100;

            private String getNotes() {
                return "Private notes";
            }
        }

        PrivateLocal privateLocal = new PrivateLocal();
        int id = privateLocal.id;
        String notes = privateLocal.getNotes();
        log.info("Access [private]: {}, {}", id, notes);
    }

    /**
     * Package (default) access modifier.
     */
    private void invokePackage() {
        // 1. When access modifier is not specified to members of a class or a class itself, it is called default access modifier.
        // 2. The default can apply to the instance variable, local variable, constructor, methods, inner class, or outer class.
        // 3. Default members of a class are visible inside the class and everywhere within classes in the same package or folder only.
        // 4. Default members can be inherited to the subclass within the same package only. It cannot be inherited from outside the package.

        // members cannot be accessed from outside package
        DefaultMembers defaultMembers = new DefaultMembers(2);
        // int code = defaultMembers.code;
        // String message = defaultMembers.getMessage();

        // unable to instantiate a class with default constructor from outside
        DefaultConstructor defaultConstructor;
        // defaultConstructor = new DefaultConstructor();

        // inner & local environment are permitted for access
        class DefaultLocal {
            int id = 100;

            String getNotes() {
                return "Default notes";
            }
        }

        DefaultLocal defaultLocal = new DefaultLocal();
        int id = defaultLocal.id;
        String notes = defaultLocal.getNotes();
        log.info("Access [default]: {}, {}", id, notes);
    }

    /**
     * Protected access modifier.
     */
    private void invokeProtected() {
        // 1. Protected access modifier can be applied to instance variables, local variables, constructors, methods, inner classes but not the outer class.
        // 2. Protected members are accessible inside the class and everywhere within classes in the same package and outside the package but through inheritance only.
        // 3. Protected members can be inherited to the subclass.
        // 4.  we make constructor as protected then we can create the subclass of that class within the same package but not outside the package.

        // members cannot be accessed from different package
        ProtectedMembers protectedMembers = new ProtectedMembers(3);
        // protectedMembers.code;
        // protectedMembers.getMessage();

        // unable to instantiate a class with protected constructor from different package
        ProtectedConstructor protectedConstructor;
        // protectedConstructor= new ProtectedConstructor();

        // inner & local environment are permitted for access
        class ProtectedLocal {
            protected int id = 100;

            protected String getNotes() {
                return "Proteced notes";
            }
        }

        ProtectedLocal protectedLocal = new ProtectedLocal();
        int id = protectedLocal.id;
        String notes = protectedLocal.getNotes();
        log.info("Access [protected]: {}, {}", id, notes);

        // inheritance access
        class ProtectedInheritance extends ProtectedMembers {

            protected ProtectedInheritance(int code) {
                super(code);
            }
        }
    }

    /**
     * Public access modifier.
     */
    private void invokePublic() {
        // 1. Public access modifier can apply to instance variables, constructors, inner classes, outer class, methods but not with local variables.
        // 2. Public members of a class can be used anywhere.
        // 3. Public members of a class can be inherited to any subclass.

        // access is permitted
        PublicMembers publicMembers = new PublicMembers(4);
        int code = publicMembers.code;
        String message = publicMembers.getMessage();
        log.info("Modifier [public]: {}, {}", code, message);

        // able to instantiate a class with public constructor from outside
        PublicConstructor publicConstructor = new PublicConstructor();

        // inner & local environment are permitted for access
        class PublicLocal {
            public int id = 100;

            public String getNotes() {
                return "Default notes";
            }
        }

        PublicLocal publicLocal = new PublicLocal();
        int id = publicLocal.id;
        String notes = publicLocal.getNotes();
        log.info("Access [public]: {}, {}", id, notes);
    }

    /**
     * Non access modifiers.
     */
    private void invokeNonAccess() {
        // Not access modifiers: abstract, final, native, static, strictfp, synchronized, transient, and volatile.

        log.info("Not access modifiers: abstract, final, native, static, strictfp, synchronized, transient, and volatile.");

        // static
        log.info("---- Static Modifier");
        invokeStatic();
        // tab
        log.info("");
        // abstract
        log.info("---- Abstract Modifier");
        invokeAbstract();
        // tab
        log.info("");
        // final
        log.info("---- Final Modifier");
        invokeFinal();
        // tab
        log.info("");
        // volatile
        log.info("---- Volatile Modifier");
        invokeVolatile();
        // tab
        log.info("");
        // synchronized
        log.info("---- Synchronized Modifier");
        invokeSynchronized();
        // tab
        log.info("");
        // transient
        log.info("---- Transient Modifier");
        invokeTransient();
        // tab
        log.info("");
        // strictfp
        log.info("---- Strictfp Modifier");
        invokeStrictfp();
        // tab
        log.info("");
        // native
        log.info("---- Native Modifier");
        invokeNative();
    }

    /**
     * Static modifier.
     */
    private void invokeStatic() {
        // 1. A static modifier can be applied with inner class, variable, method, block, and inner interface.
        // 2. It cannot be declared with top-level class, outer interface.
        // 3. A method declared with static keyword can be accessed through the class name. It is generally used to access the static variable.
        // 4. A static variable or static data member is also accessed through class name.

        StaticExample staticExample = new StaticExample();
        log.info("Static [before]: {}", StaticExample.getCounter());
        StaticExample.counter++;
        log.info("Static [after]: {}", StaticExample.getCounter());
    }

    /**
     * Abstract modifier.
     */
    private void invokeAbstract() {
        // 1. Abstract is a keyword that can be applied with outer class, inner class, method, outer interface, and inner interface.
        // 2. Abstract cannot be applied with variable, constructor, block, and enum.
        // 3. When a class is declared with a keyword abstract, it is called abstract class in java (unable to instantiate).
        // 4. An abstract keyword cannot be simultaneously declared with the final keyword.
        // 5. An interface in java is by default abstract and does not need to be declared abstract.
        // 6. When a method is declared with an abstract keyword, it is known as abstract method. It contains only a signature and not a body.
        // 7. Abstract keyword cannot be declared simultaneously with final, private, native, static, or synchronized.

        AbstractExample concreteClass = new ConcreteExample(1);
        concreteClass.processNote();
    }

    /**
     * Final modifier.
     */
    private void invokeFinal() {
        // 1. A final is a keyword that can be applied with outer class, inner class, variable, and method.
        // 2. It cannot be applied with interface, constructor, block, and enum.
        // 3. A final class cannot be extended.
        // 4. A final method cannot be overridden in java.
        // 5. Once a final variable is declared and initialized, it cannot be changed.

        // final class - unable to inherit
        {
            final class FinalSample {
            }

            log.info("Final [class]: {}", new FinalExample());
            // cannot inherit from final
            // class NotFinalSample extends FinalSample {}
        }

        // final variable - cannot be changed
        {
            // we cannot re-assign the value of a final variable once assigned
            final String message = "Final message";
            // message = "Another message";
            log.info("Final [variable]: {}", message);
        }

        // final collection - only reference is not changed
        {
            final List<String> messages = new ArrayList<>();
            messages.add("one");
            messages.add("two");
            messages.add("ten");
            log.info("Final [collection]: {}", messages);
        }

        // final method - cannot be overridden
        {
            class FinalParent {
                public final String information() {
                    return "Final method";
                }
            }
            class FinalChild extends FinalParent {
                // @Override public String information() { return "Final is not overridden"; }
            }
            log.info("Final [method]: {}", new FinalChild().information());
        }

        // final parameter - value of parameter cannot be changed
        {
            // unable to change parameters
            class FinalParameter {
                public String message(final String name) {
                    // name = "Changes";
                    return name;
                }
            }
            String value = new FinalParameter().message("value");
            log.info("Final [parameter]: {}", value);
        }
    }

    /**
     * Volatile modifier.
     */
    private void invokeVolatile() {
        // 1. A volatile modifier can be applied only with variables. It is a keyword.
        // 2. If a data member or variable is declared as volatile, all threads can see consistent the value of variable in java memory.
        // 3. A volatile keyword cannot be applied with a method declaration.
        // 4. Volatile does not create any kind of lock on the variable in the program.

        VolatileExample volatileExample = new VolatileExample(1, 0);
        volatileExample.specification();
    }

    /**
     * Synchronized modifier.
     */
    private void invokeSynchronized() {
        // 1. A synchronized modifier in java can be applied only with method and block.
        // 2. In the synchronized method block, only one thread is allowed to execute at a time. It makes thread-safe.
        // 3. Statements can also be synchronized in java.

        SynchronizedExample synchronizedExample = new SynchronizedExample(1);
        synchronizedExample.extraModification(2);
        synchronizedExample.ultraModification(3);
    }

    /**
     * Transient modifier.
     */
    private void invokeTransient() {
        // 1. The transient modifier can be applied only with variables or data members.
        // 2. When a class is serialized then transient data member is not serialized.

        // object
        TransientExample example = new TransientExample(
            1001L, 225, 345, true,
            "Username", "Password",
            33, "Ukraine, Kyiv, 01001"
        );

        // file
        URL transUrl = getClass().getClassLoader().getResource("file/backup.txt");
        assert transUrl != null : "Backup file has not been found!";
        File file = new File(transUrl.getFile());
        log.info("  Backup file: " + file.getAbsolutePath());

        // serialization
        {
            try (
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
            ) {
                log.info("Transient [serialization]: {}", example);
                oos.writeObject(example);
            } catch (IOException e) {
                log.error("Transient serialization errors.");
            }
        }

        // de-serialization
        {
            try (
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
            ) {
                TransientExample output = (TransientExample) ois.readObject();
                log.info("Transient [deserialization]: {}", output);
            } catch (IOException | ClassNotFoundException e) {
                log.error("Transient de-serialization errors.");
            }
        }
    }

    /**
     * Strictfp modifier.
     */
    private void invokeStrictfp() {
        // strictfp - prevents overflow or underflow
        // floating-point calculations take place precisely as they did in earlier versions of Java

        log.info("---- Strictfp");

        // 1. A strictfp modifier can be applied with outer class, inner class, method, and outer interface.
        // 2. It cannot be applied with variable, block, inner interface, constructor, and enum.
        // 3. A strictfp class uses the IEEE 754-1985 floating-point specification for all of its floating-point operations.
        // 4. The method defined within the interface cannot be declared with strictfp modifier.
        // 5. Strictfp modifiers cannot be used with native modifiers simultaneously.

        StrictfpExample strictfpExample = new StrictfpExample(1);
        strictfpExample.count(99);
    }

    /**
     * Native modifier.
     */
    private void invokeNative() {
        // 1. A native modifier can be used only with a method. It contains only a signature but not a body.
        // 2. A native method is generally used to merge other programming languages like C and C++  code into Java programming.
        // 3. A native keyword cannot be used with strictfp simultaneously.

        // JNI - difficult to use and requires you to generate its stubs in different language and add wrapped libraries.
        log.info("JNI [Java Native Interface]");

        // JNA - provides Java programs easy access to native shared libraries without writing anything but Java code.
        log.info("JNA [Java Native Access]");

        // native method - to invoke external code
        // risks - no portability + security breaches
        NativeExample nativeExample = new NativeExample();
        log.info("Native [method]: {}", "nativeMembers.invoke()");
        try {
            nativeExample.invoke();
        } catch (UnsatisfiedLinkError ule) {
            log.error("UnsatisfiedLinkError: " + ule.getLocalizedMessage());
        }
    }
}
