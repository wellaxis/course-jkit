package com.witalis.jkit.usage.core.invoke.section.collection.context;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc:
 * User: Wellaxis
 * Date: 4/10/2022
 */
@Slf4j
@Data
public class VarArgs {
    private Object values[];

    VarArgs(int i) {
        values = new Object[i];
    }

    public VarArgs(Object[] arr) {
        values = arr;
    }

    public void printArray(int i) {
        if (i == 0) {
            return;
        } else {
            printArray(i - 1);
        }
        log.info("[" + (i - 1) + "] " + values[i - 1]);
    }

    // old
    public void test(int v[], char mode) {
        for (int x : v) {
            log.info(x + "");
        }
    }

    // new - last arr
    public void test(byte mode, int... v) {
        for (int x : v) {
            log.info(x + "");
        }
    }
}
