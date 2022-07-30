package com.witalis.jkit.usage.core.invoke.section.assertion.content;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Assertion demo
 * User: Wellaxis
 * Date: 4/10/2022
 */
@Slf4j
public class AssertDemo {
    private int value = 3;

    int getNum() {
        return value--;
    }

    public int getValue() {
        return value;
    }

    public void handle() {
        boolean check = true;
        int n;
        for (int i = 0; i < 5; i++) {
            n = getNum();
            try {
                if (check) {
                    assert n >= 0 : n;
                    assert n > 0 : "It should be positive, but n=" + n;
                }
                // Exception is java.lang.AssertionError
                log.info("n equals to " + n);
            } catch (Throwable t) {
                log.error("n is incorrect: " + t.getMessage());
            }
        }
    }
}
