package com.witalis.jkit.shell.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class NumericService {

    /**
     * It uses to calculate sum of the numbers.
     * <p/>
     * @param numbers the array of numbers
     * @return the sum of the numbers
     */
    public double sum(double[] numbers) {
        return Arrays.stream(numbers).sum();
    }

    /**
     * It uses to calculate max of the numbers.
     * <p/>
     * @param numbers the array of numbers
     * @return the max of the numbers
     */
    public double max(double[] numbers) {
        var optional = Arrays.stream(numbers).max();
        return optional.orElse(-1);
    }
}
