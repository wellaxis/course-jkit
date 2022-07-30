package com.witalis.jkit.usage.core.invoke.section.exception;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.exception.content.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * Desc: exceptions
 * User: Wellaxis
 * Date: 2019/11/17
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class ExceptionInvoker extends Invoker {

    public ExceptionInvoker() {
        setTitle("Exception chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // handling
        log.info("## Handling");
        invokeHandling();
        // tab
        log.info("");
        // rethrow
        log.info("## Rethrow");
        invokeRethrow();
        // tab
        log.info("");
        // cause
        log.info("## Cause");
        invokeCause();
        // tab
        log.info("");
        // try-with-resources
        log.info("## ARM");
        invokeARM();
        // tab
        log.info("");
        // multi
        log.info("## Multi");
        invokeMulti();
    }

    /**
     * Basic postulates of exception handling.
     */
    private void invokeBasis() {
        // All exception and errors types are subclasses of class Throwable.
        // Error: indicates a serious problem that a reasonable application should not try to catch.
        // Exception: indicates conditions that a reasonable application might try to catch.
        // Unchecked exceptions: error + runtime -- no throws, others - checked.

        log.info("Java Exception Handling is a mechanism to handle runtime errors.");
    }

    /**
     * Handle of exceptions.
     */
    private void invokeHandling() throws ArrayIndexOutOfBoundsException, NullPointerException {
        int i = new Random().nextInt(4);
        int j = 1;
        int[] l = new int[1];
        try {
            switch (i) {
                case 0: {
                    int k = j / i;
                    break;
                }
                case 1: {
                    throw new RuntimeException("Unchecked exceptions");
                }
                case 2: {
                    l[2] = i;
                    break;
                }
                case 3: {
                    AccessException accessEx = new AccessException("Checked exceptions");
                    // chained exceptions - since JDK 4
                    accessEx.initCause((Throwable) new RuntimeException("Just Sample."));
                    throw accessEx;
                }
                default: {
                    break;
                }
            }
        } catch (ArithmeticException ae) {
            log.error("Arithmetic: " + ae.getMessage());
        } catch (NullPointerException ne) {
            log.error("NullPointer: " + ne.getMessage());
        } catch (ArrayIndexOutOfBoundsException be) {
            log.error("ArrayIndex: " + be.getMessage());
        } catch (Exception e) {
            log.error("ParentException: " + e.getMessage());
            if (e.getCause() != null) {
                log.error("CauseException: " + e.getCause());
            }
        } finally {
            log.info("The End.");
        }
    }

    /**
     * Re-throw exceptions.
     */
    private void invokeRethrow() {
        PreciseExample precise = new PreciseExample();
        try {
            precise.rethrow();
        } catch (Exception e) {
            log.error("  Error [" + e.getClass() + "]: " + e.getMessage());
        }
    }

    /**
     * Cause of exceptions.
     */
    private void invokeCause() {
        // cause-catch -- since JDK 4
        CauseExample cause = new CauseExample();
        try {
            cause.causing();
        } catch (Exception e) {
            log.error("  Error [" + e.getClass() + "]: " + e.getMessage());
        }
    }

    /**
     * Try-with-resources.
     */
    private void invokeARM() {
        // try-with-resources since JDK 7 (for files)
        try (Scanner contents = new Scanner(new File("/fake"))) {
            var content = Integer.parseInt(contents.nextLine());
            log.info("* Unexpected content: {}", content);
        } catch (FileNotFoundException e ) {
            log.error("  Error [ARM]: File not found.");
        }
    }

    /**
     * Multi-catch of exceptions.
     */
    private void invokeMulti() {
        // multi-catch -- since JDK 7
        int i = 0, j = 0;
        try {
            j /= i;
        } catch (ArithmeticException | ArrayIndexOutOfBoundsException | NullPointerException e) {
            log.error("MultiException: " + e.getMessage());
        }
    }
}
