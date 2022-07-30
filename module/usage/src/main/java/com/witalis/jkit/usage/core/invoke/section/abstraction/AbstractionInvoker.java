package com.witalis.jkit.usage.core.invoke.section.abstraction;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.abstraction.content.AbstractShow;
import com.witalis.jkit.usage.core.invoke.section.abstraction.content.InterfaceShow;
import com.witalis.jkit.usage.core.invoke.section.abstraction.content.UsualShow;
import com.witalis.jkit.usage.core.invoke.section.abstraction.content.FinalShow;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: abstracts
 * User: Wellaxis
 * Date: 2019/11/17
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class AbstractionInvoker extends Invoker {

    public AbstractionInvoker() {
        setTitle("Abstraction chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // utilities
        log.info("## Utils");
        invokeUtils();
    }

    /**
     * Basic postulates of abstraction.
     */
    private void invokeBasis() {
        // Data abstraction is the process of hiding certain details and showing only essential information to the user.
        // Abstraction can be achieved with either abstract classes or interfaces.

        // Advantages of abstraction
        // * reduces the complexity of viewing the things
        // * avoids code duplication and increases re-usability
        // * helps to increase the security of an application

        // interface level
        InterfaceShow interfaceShow = () -> log.info("It's an interface show...");
        log.info("Interface -> {}", interfaceShow.getClass().getSimpleName());
        interfaceShow.show();
        log.info("");

        // abstract level
        AbstractShow abstractShow = new AbstractShow() {
            @Override
            public void show() {
                log.info("It's an abstract show...");
            }
        };
        log.info("Abstract class -> {}", abstractShow.getClass().getSimpleName());
        abstractShow.show();
        abstractShow.note();
        abstractShow.take();
        abstractShow.hide();
        log.info("");

        // class level
        UsualShow usualShow = new UsualShow();
        log.info("Class -> {}", usualShow.getClass().getSimpleName());
        usualShow.show();
        usualShow.note();
        usualShow.hide();
        usualShow.take();
        log.info("");

        // final class level
        FinalShow finalShow = new FinalShow();
        log.info("Final Class -> {}", finalShow.getClass().getSimpleName());
        finalShow.show();
        finalShow.note();
        finalShow.hide();
        finalShow.take();
        log.info("");
    }

    /**
     * Hiding of utils implementations.
     */
    private void invokeUtils() {
        // Utils class contains a plethora of built-in methods to calculate the result.
        // However, the user is not required to be aware of the implementation of these two methods.

        int x = 10;
        int y = 20;

        log.info("Multiplication of {} & {} is {}", x, y, Math.multiplyExact(x, y));
    }
}
