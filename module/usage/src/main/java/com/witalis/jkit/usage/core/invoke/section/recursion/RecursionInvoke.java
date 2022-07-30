package com.witalis.jkit.usage.core.invoke.section.recursion;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.recursion.content.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: recursion
 * User: Wellaxis
 * Date: 2019/11/17
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class RecursionInvoke extends Invoker {

    public RecursionInvoke() {
        setTitle("Recursion chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // sample
        log.info("## Sample");
        invokeSample();
    }

    /**
     * Basic postulates of recursion.
     */
    private void invokeBasis() {
        // Recursion provides a way to break complicated problems down
        // into simple problems which are easier to solve.

        log.info("Recursion is the technique of making a function call itself.");
    }

    /**
     * Recursion sample.
     */
    private void invokeSample() {
        // sum calculation
        {
            int number = 10;
            log.info("* Recursion [sum]: input is {}", number);
            int result = Sum.sum(number);
            log.info("* Recursion [sum]: output is {}", result);
        }

        // factorial calculation
        {
            Factorial factorial = new Factorial();

            int number = 10;
            log.info("* Recursion [factorial]: input is {}", number);
            for (int i = 1; i < number; ++i) {
                log.info("* Recursion [factorial] of [{}] is {}", i, factorial.calculate(i));
            }
        }
    }
}
