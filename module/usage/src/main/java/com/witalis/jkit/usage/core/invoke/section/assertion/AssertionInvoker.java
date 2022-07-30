package com.witalis.jkit.usage.core.invoke.section.assertion;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.assertion.content.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Assertion
 * User: Wellaxis
 * Date: 2019/11/18
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class AssertionInvoker extends Invoker {
    private static boolean throwing = false;

    public AssertionInvoker() {
        setTitle("Assertion chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
    }

    /**
     * Assertion functionality.
     */
    private void invokeBasis() {
        // The Java assert keyword allows developers to quickly verify certain assumptions or state of a program.
        // For backward compatibility, the JVM disables assertion validation by default:
        // -ea = on
        // -da = off
        // samples -ea [system], -ea:com.app.api.core [package], -ea:Intro [class]

        AssertDemo assertDemo = new AssertDemo();
        // simple form
        assert assertDemo.getValue() > 0;
        // message form
        assert assertDemo.getValue() > 0 : "Value should be positive";
        assertDemo.handle();

        // exception
        if (throwing) {
            throw new AssertionError("Assertion error instance");
        }
    }
}
