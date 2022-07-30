package com.witalis.jkit.usage.core.invoke.section.lambda.content.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * Desc: Reference method - static mode
 * User: Wellaxis
 * Date: 5/21/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceMethodStatic {
    private int value;

    // lambda reference
    public interface ReferenceOperation<T> {
        int operate(T[] values, T value);
    }

    // check numbers
    public static boolean isMoreThanFifty(int n1, int n2) {
        return (n1 + n2) > 50;
    }

    // filter numbers
    public static List<Integer> filterNumbers(List<Integer> numbers, BiPredicate<Integer, Integer> predicate) {
        List<Integer> newList = new ArrayList<>();
        for (Integer i : numbers) {
            if (predicate.test(i, i + 10)) newList.add(i);
        }
        return newList;
    }

    // static generic counter
    public static <T> int countMatching(T[] values, T v) {
        int count = 0;
        for (T value : values) {
            if (value == v) {
                ++count;
            }
        }
        return count;
    }

    // generic for references
    public static<T> int funcOperation(ReferenceOperation<T> operation, T[] values, T value) {
        return operation.operate(values, value);
    }
}
