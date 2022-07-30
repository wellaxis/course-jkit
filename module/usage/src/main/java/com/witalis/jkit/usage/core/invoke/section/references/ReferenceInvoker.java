package com.witalis.jkit.usage.core.invoke.section.references;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.references.content.ReferenceHandler;

import com.witalis.jkit.usage.core.invoke.section.references.content.ReferenceSuper;
import com.witalis.jkit.usage.core.invoke.section.references.content.ReferenceThis;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: references
 * User: Wellaxis
 * Date: 2019/11/19
 */
@Slf4j
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class ReferenceInvoker extends Invoker {

    public ReferenceInvoker() {
        setTitle("Reference chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // this
        log.info("## This");
        invokeThis();
        // tab
        log.info("");
        // super
        log.info("## Super");
        invokeSuper();
        // tab
        log.info("");
        // reference
        log.info("## Reference");
        invokeReference();
    }

    /**
     * Basic postulates of references.
     */
    private void invokeBasis() {
        // In java there are two reference variables to class objects: this & super.

        log.info("There are two reference variables to class objects: this & super.");

        // In Java there are four types of references differentiated on the way by which they are garbage collected.
        // 1) Strong References
        // 2) Weak References
        // 3) Soft References
        // 4) Phantom References

        log.info("There are four types of references: strong, weak, soft, phantom.");
    }

    private void invokeThis() {
        log.info("Keyword 'this' is a reference variable that refers to the current class object.");

        // It holds the reference to the current class object or the same class object.

        log.info("1. The keyword 'this' in Java can be applied to instance variables, constructors, and methods.");
        log.info("2. It can only be used within the non-static method of a class.");
        log.info("3. The compiler automatically adds 'this' keyword while invoking the method of the current class.");

        log.info("Use of 'this':");
        log.info("1. this reference can be used to refer to the current class instance variable.");
        log.info("2. this keyword is used to call the non-static method of the current class.");
        log.info("3. this() can be used to invoke the current class constructor.");
        log.info("4. this keyword can be used as a parameter in the method call.");
        log.info("5. this can be used as a parameter in the constructor call.");
        log.info("6. this can be used to return the object of the current class from the method.");

        // sample
        log.info("Sample -> reference 'this':");
        {
            ReferenceThis referenceThis = new ReferenceThis("This");
            referenceThis.increment().increment().increment().print();
        }
    }

    private void invokeSuper() {
        log.info("Keyword 'super' is a reference variable that refers to an immediate superclass object.");

        // The keyword 'super' comes into the picture with the concept of inheritance in Java.

        log.info("1. The keyword 'super' always represents a superclass object.");
        log.info("2. The keyword 'super' allows users to access members of a superclass in a subclass.");
        log.info("3. We can apply super keyword with variables, methods, constructors of parent class.");
        log.info("4. There is not any super.super in Java. It is illegal.");

        // Difference between 'this' vs 'super':

        // 1.1 'this' is a reference variable that contains current class objects.
        // 1.2 'super' is a reference variable that contains immediate super class objects.

        // 2.1 Any member of the current class object from within an instance method or a constructor can be referred by using this keyword.
        // 2.2 If the method overrides one of its super class’s method, the overridden method can be called through the use of super keyword.

        // 3.1 'this' keyword is used to call another constructor from within a constructor in the same class.
        // 3.2 'super' keyword is used to call the super class’s constructor from within a constructor of the subclass.

        // 4.1 JVM automatically adds 'this' keyword while invoking the method of the current class.
        // 4.2 By default JVM automatically put the super() keyword at first line inside the constructor.

        // sample
        log.info("Sample -> reference 'super':");
        {
            ReferenceSuper referenceSuper = new ReferenceSuper("Super");
            referenceSuper.increment().increment().increment().print();
        }
    }

    /**
     * Java References
     */
    private void invokeReference() {
        // without loading
        {
            ReferenceHandler handler = new ReferenceHandler();
            handler.initialization();

            log.info("Reference [handler] - without loading");
            handler.handle();
        }

        log.info("");

        // with loading
        {
            ReferenceHandler handler = new ReferenceHandler();
            handler.initialization();

            log.info("Reference: [handler] - before loading");
            handler.handle();

            // simulate big loading to clean soft references
            handler.memoryLoading();

            log.info("Reference: [handler] - after loading");
            handler.handle();
        }
    }
}
