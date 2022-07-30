package com.witalis.jkit.usage.core.invoke.section.tokens;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.constant.ConstantInvoker;
import com.witalis.jkit.usage.core.invoke.section.variable.VariableInvoker;

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
public class TokenInvoker extends Invoker {

    public TokenInvoker() {
        setTitle("Java Beans chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // keywords
        log.info("## Keywords");
        invokeKeywords();
        // tab
        log.info("");
        // identifiers
        log.info("## Identifiers");
        invokeIdentifiers();
        // tab
        log.info("");
        // constants
        log.info("## Constants");
        invokeConstants();
        // tab
        log.info("");
        // variables
        log.info("## Variables");
        invokeVariables();
        // tab
        log.info("");
        // comments
        log.info("## Comments");
        invokeComments();
    }

    /**
     * Java Tokens.
     */
    private void invokeBasis() {
        // Tokens are the various elements in the java program that are identified by Java compiler.

        log.info("Tokens: reserved keywords, identifiers, literals, operators, separators, comments.");
    }

    /**
     * Reserved keywords.
     */
    private void invokeKeywords() {
        // Keywords in Java are predefined words that have specific meanings to the compiler and that meanings cannot be changed.

        log.info("Data Types: byte, short, int, long, float, double, char, boolean");
        log.info("Access Modifiers: private, protected, public");
        log.info("Control Statement: break, case, continue, default, do, else, for, goto, if, switch, while");
        log.info("Object & Classes: class, interface, enum, extends, implements, this, super, new");
        log.info("Modifiers: static, abstract, synchronized, volatile, native, transient, strictfp, final");
        log.info("Exceptions: try, catch, finally, throw, throws");
        log.info("Package: package, import");
        log.info("Miscellaneous Keywords: return, const, instanceof, void, assert, default, null, true, false");
    }

    /**
     * Java identifiers.
     */
    private void invokeIdentifiers() {
        // Identifiers in Java are names that identify elements such as classes, variables, and methods in a program.

        // Rules for naming identifiers:
        // 1. Java identifier is a sequence of characters that consists of letters, digits, underscores (_), and dollar signs ($).
        // 2. It must start with a letter, an underscore (_), or a dollar sign ($). It cannot begin with a digit.
        // 3. It cannot be a reserved word.
        // 4. It cannot be true, false, or null.
        // 5. It can be of any length.

        int numberOf20Letters = 100;
        String $message = "Information";
        boolean _status = true;
    }

    /**
     * Java constants.
     */
    private void invokeConstants() {
        new ConstantInvoker().run();
    }

    /**
     * Java variables.
     */
    private void invokeVariables() {
        new VariableInvoker().run();
    }

    /**
     * Java comments.
     */
    private void invokeComments() {
        log.info("Comments in Java are statements that describe the features of a program.");

        // one line comments.

        /*
            It is a sample of
            multiline comments.
        */

        /**
         * Java documentation comments.
         */
    }
}
