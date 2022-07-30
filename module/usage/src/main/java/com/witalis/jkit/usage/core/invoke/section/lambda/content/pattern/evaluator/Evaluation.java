package com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.evaluator;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

@Slf4j
public final class Evaluation {

    private Evaluation() {
        super();
    }

    public static boolean evaluate(final int value) {
        log.info("  Evaluation of {}", value);
        simulateTimeConsumingOp(1000);
        return value > 100;
    }

    public static void simulateTimeConsumingOp(final int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch(Exception ex) {
            log.error("  Evaluation error: {}", ex.getMessage());
        }
    }

    public static void eagerEvaluator(
        final boolean input1,
        final boolean input2
    ) {
        log.info("  Evaluator [eager]: invoke");
        log.info("  Evaluator [eager]: accept? {}", (input1 && input2));
    }

    public static void lazyEvaluator(
        final Supplier<Boolean> input1,
        final Supplier<Boolean> input2
    ) {
        log.info("  Evaluator [lazy]: invoke");
        log.info("  Evaluator [lazy]: accept? {}", (input1.get() && input2.get()));
    }
}
