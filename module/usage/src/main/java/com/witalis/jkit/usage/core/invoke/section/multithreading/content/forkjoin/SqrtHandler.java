package com.witalis.jkit.usage.core.invoke.section.multithreading.content.forkjoin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.concurrent.RecursiveAction;

/**
 * Desc: SQRT Handler
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SqrtHandler extends RecursiveAction {
    public static final int THRESHOLD = 1_000;

    private double[] data;
    private int start;
    private int end;

    @Override
    protected void compute() {
        // processing
        var mode = (end - start) < THRESHOLD;
        if (mode) {
            // consistent
            for (int i = start; i < end; i++) {
                data[i] = Math.sqrt(data[i]);
            }
        } else {
            // parallel
            int middle = (start + end) / 2;
            invokeAll(
                new SqrtHandler(data, start, middle),
                new SqrtHandler(data, middle, end)
            );
        }
    }
}