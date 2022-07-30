package com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.recursion;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Factorial {

    private Factorial() {
        super();
    }

    public static int factorialRec(final int number) {
        if (number == 1)
            return number;
        else
            return number * factorialRec(number - 1);
    }

    public static TailCall<Integer> factorialTailRec(final int factorial, final int number) {
        if (number == 1)
            return TailCalls.done(factorial);
        else
            return TailCalls.call(() -> factorialTailRec(factorial * number, number - 1));
    }

    public static int factorial(final int number) {
        return factorialTailRec(1, number).invoke();
    }
}
