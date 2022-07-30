package com.witalis.jkit.usage.core.invoke.section.multithreading.content.forkjoin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Desc: Factor Handler
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FactorHandler extends CountedCompleter<BigInteger> {
    public static final int SEQUENTIAL_THRESHOLD = 5;
    private List<BigInteger> integerList;
    private AtomicReference<BigInteger> result;

    public FactorHandler(
        CountedCompleter<BigInteger> parent,
        AtomicReference<BigInteger> result,
        List<BigInteger> integerList
    ) {
        super(parent);
        this.integerList = integerList;
        this.result = result;
    }

    @Override
    public BigInteger getRawResult () {
        return result.get();
    }

    @Override
    public void compute () {
        // processing
        while (integerList.size() > SEQUENTIAL_THRESHOLD) {
            // sub-list
            List<BigInteger> newTaskList = integerList.subList(
                integerList.size() - SEQUENTIAL_THRESHOLD,
                integerList.size()
            );
            // remaining list
            integerList = integerList.subList(
                0,
                integerList.size() - SEQUENTIAL_THRESHOLD
            );
            addToPendingCount(1);
            FactorHandler task = new FactorHandler(this, result, newTaskList);
            task.fork();
        }
        // calculate
        for (BigInteger i : integerList) {
            BigInteger factorial = calculateFactorial(i);
            result.getAndAccumulate(
                factorial, BigInteger::add
            );
        }
        // completion
        propagateCompletion();
    }

    private BigInteger calculateFactorial (BigInteger input) {
        BigInteger factorial = BigInteger.ONE;
        for (BigInteger i = BigInteger.ONE;
             i.compareTo(input) <= 0;
             i = i.add(BigInteger.ONE)) {
            factorial = factorial.multiply(i);
        }
        return factorial;
    }
}
