package com.witalis.jkit.usage.core.invoke.section.lambda.content.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Desc: Reference method - particular type mode
 * User: Wellaxis
 * Date: 5/21/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceMethodType {
    private double initial = 0;

    // lambda reference
    public interface ReferenceOperation<R, T> {
        int operate(R reference, T[] values, T value);
    }

    // calculate weight
    public double calculateWeight(int coefficient) {
        double weight = getInitial();
        return weight * coefficient;
    }

    // calculate weights
    public static List<Double> calculateWeights(List<ReferenceMethodType> methods, BiFunction<ReferenceMethodType, Integer, Double> function) {
        final int coefficient = 25;

        List<Double> weights = new ArrayList<>();
        methods.forEach(s -> weights.add(function.apply(s, coefficient)));
        return weights;
    }

    // static generic counter
    public <T> int countMatching(T[] values, T v) {
        int count = 0;
        for (T value : values) {
            if (value == v) {
                ++count;
            }
        }
        return count;
    }

    // generic for references
    public <T> int numberOp(ReferenceOperation<ReferenceMethodType, T> operation, T[] values, T value) {
//        ReferenceOperation<ReferenceMethodType, Integer> ro = (type, vals, v) -> type.countMatching(vals, v);

        //return type.countMatching(values, value);
        return operation.operate(this, values, value);
    }
}
