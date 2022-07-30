package com.witalis.jkit.usage.core.invoke.section.lambda.content.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

/**
 * Desc: Reference method - object instance mode
 * User: Wellaxis
 * Date: 5/21/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceMethodObject {
    private Laptop laptop;
    private Mechanic mechanic;

    // lambda reference
    public interface ReferenceOperation<T> {
        int operate(T[] values, T value);
    }

    @Data
    @AllArgsConstructor
    public static class Laptop {
        private UUID id;
        private String color;
    }

    @Data
    @AllArgsConstructor
    public static class Mechanic {
        private String name;

        public double fix(Laptop laptop) {
            return 1_000 * Math.floor(ThreadLocalRandom.current().nextDouble() * 100) / 100;
        }
    }

    // fix device
    public double fixLaptop(Laptop laptop, Function<Laptop, Double> function) {
        return function.apply(laptop);
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
    public <T> int numberOp(ReferenceOperation<T> operation, T[] values, T value) {
        return operation.operate(values, value);
    }
}
