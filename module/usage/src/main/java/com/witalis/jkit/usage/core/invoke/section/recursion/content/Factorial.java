package com.witalis.jkit.usage.core.invoke.section.recursion.content;

/**
 * Desc: Factorial calculation
 * User: Wellaxis
 * Date: 08.02.2022
 */
public class Factorial {

    public Factorial() {
        super();
    }

    public long calculate(final int n) {
        return (n == 1) ? 1L : (calculate(n - 1) * n);
    }
}
