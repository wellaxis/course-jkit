package com.witalis.jkit.usage.core.invoke.section.recursion.content;

/**
 * Desc: Sum calculation
 * User: Wellaxis
 * Date: 4/26/2022
 */
public final class Sum {

    private Sum() {
        super();
    }

    public static int sum(int k) {
        if (k > 0) {
            return k + sum(k - 1);
        } else {
            return 0;
        }
    }
}
