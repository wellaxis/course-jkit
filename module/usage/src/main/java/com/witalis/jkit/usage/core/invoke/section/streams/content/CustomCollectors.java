package com.witalis.jkit.usage.core.invoke.section.streams.content;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Desc: Custom collectors.
 * User: Wellaxis
 * Date: 4/24/2022
 */
public final class CustomCollectors {

    /**
     * Custom collector for stream API.
     * 1) the type of objects that will be available for collection
     * 2) the type of mutable accumulator object
     * 3) the type of final result
     * <p/>
     * @param <T> the generic type for collector
     */
    public static class CustomCollector<T extends Number> implements Collector<T, LinkedList<T>, LinkedHashSet<T>> {

        public static <T extends Number> CustomCollector<T> toCustomize() {
            return new CustomCollector<T>();
        }

        @Override
        // returns a supplier that generates an empty accumulator instance
        public Supplier<LinkedList<T>> supplier() {
            return LinkedList::new;
        }

        @Override
        // returns a function that is used for adding a new element to an existing accumulator object
        public BiConsumer<LinkedList<T>, T> accumulator() {
            return (accumulator, element) -> {
                Number pow = Math.pow(element.doubleValue(), 2);
                accumulator.add((T) pow);
            };
        }

        @Override
        // returns a function that is used for merging two accumulators together
        public BinaryOperator<LinkedList<T>> combiner() {
            return (l, r) -> {
                l.addAll(r);
                return l;
            };
        }

        @Override
        // used for converting an accumulator to final result type
        public Function<LinkedList<T>, LinkedHashSet<T>> finisher() {
            return LinkedHashSet::new;
        }

        @Override
        // to provide stream with some additional information that will be used for internal optimizations
        public Set<Characteristics> characteristics() {
            return Set.of(Characteristics.CONCURRENT);
        }
    }
}
