package com.witalis.jkit.usage.core.invoke.section.encapsulation;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.encapsulation.content.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Encapsulation
 * User: Wellaxis
 * Date: 2019/11/17
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class EncapsulationInvoker extends Invoker {

    public EncapsulationInvoker() {
        setTitle("Encapsulation chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // modifiers
        log.info("## Modifiers");
        invokeModifiers();
        // tab
        log.info("");
        // nested
        log.info("## Nested");
        invokeNested();
    }

    /**
     * Basic postulates of encapsulation.
     */
    private void invokeBasis() {
        // Encapsulation in Java is a mechanism to wrap up
        // variables(data) and methods(code) together as a single unit.

        // advantages of encapsulation:
        // * data hiding
        // * access control
        // * increased flexibility
        // * re-usability
        // * easy testing

        log.info("---- Basic postulates");

        class Account {
            private String number;
            private int balance;

            public void setNumber(String number) {
                this.number = number;
            }

            public int getBalance() {
                return balance;
            }
        }

        Account account = new Account();
        String number = "4203-4593-8459-3856";
        account.setNumber(number);
        int balance = account.getBalance();
        log.info("Account[balance]: {} -> {}", number, balance);
    }

    /**
     * Encapsulation via class modifiers.
     */
    private void invokeModifiers() {
        // technically in encapsulation, the variables or data of a class
        // is hidden from any other class and can be accessed only through
        // any member function of its own class in which it is declared.

        log.info("---- Getters & Setters");

        Product computer = new Product("Apple");
        computer.setCost(12_345.67);
        log.info("Product[computer]: {}", computer);

        Product tablet = new Product("IPad", 999.9);
        log.info("Product[tablet]: {}", tablet);
        double totalCost = tablet.getTotalCost(0.35F);
        log.info("Total cost is {}", String.format("%.2f", totalCost));
    }

    /**
     * Nested data visibility.
     */
    private void invokeNested() {
        // inner class has access to outer class
        // anonymous classes capture local variables

        log.info("---- Nested access");

        // inner class
        {
            log.info("** Inner class");
            // inner class = nested non-static
            int outer = 10;

            class Inner {
                private int inner = 20;

                // access to outer variables
                void display() {
                    log.info("Nested[display]: inner = {}", inner);
                    log.info("Nested[display]: outer = {}", outer);
                }
            }
            Inner inner = new Inner();
            inner.display();
        }

        // anonymous class
        {
            log.info("** Anonymous class");
            // anonymous class = without name
            int outer = 10;

            Runnable action = new Runnable() {
                private int inner = 20;

                @Override
                public void run() {
                    log.info("Anonymous[run]: inner = {}", inner);
                    log.info("Anonymous[run]: outer = {}", outer);
                }
            };
            action.run();
        }
    }
}
