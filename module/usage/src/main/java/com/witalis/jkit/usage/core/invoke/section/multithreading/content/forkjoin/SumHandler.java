package com.witalis.jkit.usage.core.invoke.section.multithreading.content.forkjoin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.concurrent.RecursiveTask;

/**
 * Desc: Sum Handler
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SumHandler extends RecursiveTask<Double> {
    public static final int THRESHOLD = 500;

    private double[] data;
    private int start;
    private int end;

    @Override
    protected Double compute() {
        double sum = 0;
        // processing
        var mode = (end - start) < THRESHOLD;
        if (mode) {
            // consistent
            for (int i = start; i < end; i++) {
                sum += data[i];
            }
        } else {
            // parallel
            int middle = (start + end) / 2;
            SumHandler subLeft = new SumHandler(data, start, middle);
            SumHandler subRight = new SumHandler(data, middle, end);
            subLeft.fork();
            subRight.fork();
            sum = subLeft.join() + subRight.join();
        }
        return sum;
    }
}
