package com.witalis.jkit.usage.core.invoke.section.lambda.content.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;

/**
 * Desc: Lambda utilities
 * User: Wellaxis
 * Date: 5/14/2022
 */
public final class CoreLambdaUtils {

    private CoreLambdaUtils() {
        super();
    }

    // predicate - filter list of generic elements
    public static <T> List<T> verify(List<T> list, Predicate<T> func) {
        return list.stream().filter(func).toList();
    }

    // bi-predicate - cars comparison by characteristics
    public static <T extends Car> List<T> cheapCars(List<T> cars, BiPredicate<String, Double> biPredicate) {
        return cars.stream()
            .filter(x -> biPredicate.test(x.getModel(), x.getCost()))
            .toList();
    }

    // function - map incoming type to outgoing one
    public static <T, R> List<R> encrypt(List<T> list, Function<T, R> func) {
        return list.stream().map(func).toList();
    }

    // bi-function - hypotenuse processing with generic result
    public static <R> R processHypotenuse(Integer i1, Integer i2, BiFunction<Integer, Integer, Double> func1, Function<Double, R> func2) {
        return func1.andThen(func2).apply(i1, i2);
    }

    // consumer - bulk processing of list of elements
    public static <T> void bulkProcessing(List<T> list, Consumer<T> consumer) {
        for (T t: list) consumer.accept(t);
    }

    // bi-consumer - double adder calculation
    public static <T, R> void doubleAdder(T first, R second, BiConsumer<T, R> adder) {
        adder.accept(first, second);
    }

    // supplier - generate list of elements
    public static <T> List<T> listGenerator(Supplier<List<T>> supplier) {
        return supplier.get();
    }

    // supplier - factory, to produce cars
    public static Car factory(Supplier<? extends Car> supplier) {
        Car car = supplier.get();
        if (car.getModel() == null || car.getModel().isEmpty()) {
            car.setModel("Acura");
        }
        if (car.getCost() == 0.0) {
            car.setCost(10_000D);
        }
        return car;
    }

    // unary-operator - sqrt operation for list of elements
    public static <T> List<T> sqrtOperation(List<T> list, UnaryOperator<T> operator) {
        return list.stream().map(operator).toList();
    }

    // unary-accumulator - total tax calculation
    public static double accumulator(List<Double> list, double init, UnaryOperator<Double> operator) {
        var result = init;
        for (double t: list) {
            result += operator.apply(t);
        }
        return result;
    }

    // binary-operator - multiplication gathering
    public static <T> Map<T, String> gatherOperation(List<T> list, BinaryOperator<T> operator) {
        var map = new HashMap<T, String>();
        T previous = list.get(0);
        for (T t : list) {
            var res = operator.apply(t, previous);
            map.put(res, " <1st: " + t + ", 2nd: " + previous + ", res: " + res.toString() + "> ");
            previous = t;
        }
        return map;
    }

    // binary-finder - comparator implementation
    public static Car finder(List<Car> cars, BinaryOperator<Car> operator) {
        Car result = null;
        for (Car car: cars) {
            if (result == null)
                result = car;
            else
                result = operator.apply(result, car);
        }
        return result;
    }
}
