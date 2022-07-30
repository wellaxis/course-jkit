package com.witalis.jkit.usage.core.invoke.section.lambda;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.lambda.content.custom.*;
import com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.around.*;
import com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.builder.*;
import com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.decorator.*;
import com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.error.*;
import com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.evaluator.*;
import com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.lazy.*;
import com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.lock.*;
import com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.memento.*;
import com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.recursion.*;
import com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.strategy.*;
import com.witalis.jkit.usage.core.invoke.section.lambda.content.reference.*;
import com.witalis.jkit.usage.core.invoke.section.lambda.content.scenario.comparison.*;
import com.witalis.jkit.usage.core.invoke.section.lambda.content.scenario.emulation.*;
import com.witalis.jkit.usage.core.invoke.section.lambda.content.scenario.stream.*;
import com.witalis.jkit.usage.core.invoke.section.lambda.content.utils.*;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;

import static com.witalis.jkit.usage.core.invoke.section.lambda.content.reference.ReferenceMethodConstructor.*;
import static com.witalis.jkit.usage.core.invoke.section.lambda.content.reference.ReferenceMethodObject.*;
import static com.witalis.jkit.usage.core.invoke.section.lambda.content.reference.ReferenceMethodStatic.*;
import static com.witalis.jkit.usage.core.invoke.section.lambda.content.reference.ReferenceMethodType.*;

/**
 * Desc: lambdas
 * User: Wellaxis
 * Date: 2019/11/19
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class LambdaInvoker extends Invoker {

    public LambdaInvoker() {
        setTitle("Stream chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // utils
        log.info("## Utils");
        invokeUtils();
        // tab
        log.info("");
        // customs
        log.info("## Custom");
        invokeCustom();
        // tab
        log.info("");
        // references
        log.info("## Reference");
        invokeReference();
        // tab
        log.info("");
        // patterns
        log.info("## Pattern");
        invokePattern();
        // tab
        log.info("");
        // scenario
        log.info("## Scenario");
        invokeScenario();
    }

    /**
     * Basic postulates of lambdas.
     */
    private void invokeBasis() {
        // lambda expressions - closures (since Java 8)
        // lambda as functional interface - SAM (Single Abstract Method)

        log.info("Lambdas are the functional interfaces.");
    }

    /**
     * Core lambdas.
     */
    private void invokeUtils() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<String> alphas = Arrays.asList("A", "AA", "AAA", "B", "BB", "BBB");
        List<Car> cars = Arrays.asList(
            new Car("Honda", 44_000D),
            new Car("Toyota", 38_000D),
            new Car("mazda", 40_000D),
            new Car("Lexus", 75_000D)
        );

        // predicate
        {
            log.info("");
            log.info("* Predicate lambda:");

            // standard
            List<Integer> collect = nums.stream().filter(x -> x > 5).toList();
            log.info("  - predicate[expression]=" + collect);

            // variable
            Predicate<Integer> predicateMoreThan5 = x -> x > 5;
            collect = nums.stream().filter(predicateMoreThan5).toList();
            log.info("  - predicate[function]=" + collect);

            // and
            Predicate<Integer> predicateLessThan8 = x -> x < 8;
            collect = nums.stream().filter(predicateMoreThan5.and(predicateLessThan8)).toList();
            log.info("  - predicate[and]=" + collect);

            // or
            Predicate<String> predicateLength3 = x -> x.length() == 3;
            Predicate<String> predicateStartWithA = x -> x.startsWith("A");
            List<String> words = alphas.stream().filter(predicateLength3.or(predicateStartWithA)).toList();
            log.info("  - predicate[or]=" + words);

            // negate
            words = alphas.stream().filter(predicateStartWithA.negate()).toList();
            log.info("  - predicate[negate]=" + words);

            // int
            IntPredicate predicateMod7 = x -> x % 7 == 0;
            log.info("  - predicate[int]=" + predicateMod7.test(15));

            // argument
            List<String> allB = CoreLambdaUtils.verify(alphas, s -> !s.isEmpty() && s.startsWith("A"));
            log.info("  - predicate[argument]={}", allB);

            // bi-predicate
            {
                log.info("");
                log.info("* BiPredicate lambda:");

                // standard
                BiPredicate<String, Integer> filter = (x, y) -> x.length() == y;
                boolean res1 = filter.test("school", 6);
                log.info("  - bi-predicate[ok]={}", res1);
                boolean res2 = filter.test("java", 10);
                log.info("  - bi-predicate[bad]={}", res2);

                // argument
                BiPredicate<String, Double> bi = (model, cost) -> {
                    Character top = model.charAt(0);
                    return (top.equals(Character.toUpperCase(top)) && cost <= 40_000D);
                };
                List<Car> cheapCars = CoreLambdaUtils.cheapCars(cars, bi);
                log.info("  - bi-predicate[argument]={}", cheapCars);
            }
        }

        // function
        {
            log.info("");
            log.info("* Function lambda:");

            // standard
            Function<String, Integer> func = x -> x.length();
            Integer apply = func.apply("programming");
            log.info("  - function[length]={}", apply);

            // and then
            Function<Integer, Double> func2 = x -> Math.pow(x, 3);
            Double result = func.andThen(func2).apply("java");
            log.info("  - function[andThen]={}", result);

            // int
            IntFunction<Double> div = x -> x / 2.0;
            log.info("  - function[int]={}", div.apply(5));

            // argument
            List<String> encryption = CoreLambdaUtils.encrypt(alphas, s -> DigestUtils.sha512Hex(s).substring(0, 8));
            log.info("  - function[argument]={}", encryption);

            // bi-function
            {
                log.info("");
                log.info("* BiFunction lambda:");

                // standard
                BiFunction<Integer, Double, String> biFunc = (x1, x2) -> "Res=" + Math.pow(x1, x2);
                String biResult = biFunc.apply(2, 3.0);
                log.info("  - bi-function[multi]={}", biResult);

                // and then
                BiFunction<Integer, Integer, Double> biFunc1 = (a1, a2) -> Math.pow(a1, a2);
                Function<Double, String> biFunc2 = (input) -> "Result : " + String.valueOf(input);
                biResult = biFunc1.andThen(biFunc2).apply(2, 4);
                log.info("  - bi-function[andThen]={}", biResult);

                // argument
                String hypotenuse = CoreLambdaUtils.processHypotenuse(
                    3, 4,
                    (a1, a2) -> Math.hypot(a1, a2),
                    (h) -> "Hypot is " + h
                );
                log.info("  - bi-function[argument]={}", hypotenuse);
            }
        }

        // consumer
        {
            log.info("");
            log.info("* Consumer lambda:");

            // standard
            var info = "Lambdas";
            Consumer<String> logging = x -> log.info("  - consumer[print]={}", x);
            logging.accept(info);

            // and then
            Consumer<String> logging2 = x -> log.info("  - consumer[print2]={}", x);
            logging.andThen(logging2).accept("Hello");

            // int
            IntConsumer handle = x -> log.info("  - consumer[int]={}", ++x);
            handle.accept(99);

            // argument
            var list = List.of(4, 7, 9);
            Consumer<Integer> doubles = x -> log.info("  - consumer[item][{}] = {}", x, Math.pow(x, 2));
            CoreLambdaUtils.bulkProcessing(list, doubles);

            // bi-consumer
            {
                log.info("");
                log.info("* BiConsumer lambda:");

                // standard
                BiConsumer<Integer, Integer> addTwo = (x, y) -> log.info("  - bi-consumer[plus] = {} + {} = {}", x, y, x + y);
                addTwo.accept(1, 2);

                // argument
                CoreLambdaUtils.doubleAdder(
                    23, 34L,
                    (x, y) -> log.info("  - bi-consumer[argument] = {}^2 + {}^2 = {}", x, y, (long) x * x + y * y)
                );
            }
        }

        // supplier
        {
            log.info("");
            log.info("* Supplier lambda:");

            // standard
            Supplier<LocalDateTime> dateTime = () -> LocalDateTime.now();
            log.info("  - supplier[time]={}", dateTime.get());

            // int
            IntSupplier random = () -> ThreadLocalRandom.current().nextInt();
            log.info("  - supplier[int]={}", random.getAsInt());

            // argument
            List<String> list = CoreLambdaUtils.listGenerator(ArrayList::new);
            list.addAll(List.of("X", "Y", "Z"));
            log.info("  - supplier[argument]={}", list);

            // factory
            {
                Car car = CoreLambdaUtils.factory(Car::new);
                log.info("  - supplier[factory]={}", car);

                car = CoreLambdaUtils.factory(() -> new Car("Jeep", 55_000D));
                log.info("  - supplier[factory]={}", car);
            }
        }

        // unary operator (extends function)
        {
            log.info("");
            log.info("* Unary Operator lambda:");

            // standard
            Function<Integer, Integer> func1 = x -> x * 2;
            Integer result1 = func1.apply(5);
            log.info("  - function[simple]={}", result1);

            // apply
            UnaryOperator<Integer> func2 = x -> x * 2;
            Integer result2 = func2.apply(5);
            log.info("  - unary-operator[simple]={}", result2);

            // double
            DoubleUnaryOperator ceil = x -> Math.ceil(x);
            // ceil - the smallest value that is greater than parameter (as integer)
            log.info("  - unary-operator[double]={}", ceil.applyAsDouble(8.8));

            // argument
            List<Integer> sqrtList = CoreLambdaUtils.sqrtOperation(nums, x -> (int) Math.sqrt(x));
            log.info("  - unary-operator[argument]={}", sqrtList);

            // accumulator
            {
                var salaries = List.of(123D, 234D, 345D, 456D, 567D);
                double totalWithTaxes = CoreLambdaUtils.accumulator(salaries, 0, x -> x * 1.2);
                log.info("  - unary-operator[accumulator]={}", totalWithTaxes);
            }
        }

        // binary operator (extends bi-function)
        {
            log.info("");
            log.info("* Binary Operator lambda:");

            // standard
            BiFunction<Integer, Integer, Integer> func1 = (x, y) -> x * y;
            Integer result1 = func1.apply(3,5);
            log.info("  - bi-function[simple]={}", result1);

            // apply
            BinaryOperator<Integer> func2 = (x, y) -> x * y;
            Integer result2 = func2.apply(3,5);
            log.info("  - binary-operator[simple]={}", result2);

            // long
            LongBinaryOperator ceil = (x, y) -> Math.addExact(x, y);
            log.info("  - binary-operator[long]={}", ceil.applyAsLong(7L, 8L));

            // argument
            Map<Integer, String> mods = CoreLambdaUtils.gatherOperation(
                nums.subList(0, 3),
                (a1, a2) -> Math.multiplyExact(a1, a2)
            );
            log.info("  - binary-operator[argument]={}", mods);

            // finder
            {
                Comparator<Car> comparator = Comparator.comparing(Car::getCost);

                Car expensive = CoreLambdaUtils.finder(cars, BinaryOperator.maxBy(comparator));
                log.info("  - binary-operator[expensive]={}", expensive);

                Car cheap = CoreLambdaUtils.finder(cars, BinaryOperator.minBy(comparator));
                log.info("  - binary-operator[cheap]={}", cheap);
            }
        }
    }

    /**
     * Custom lambdas.
     */
    private void invokeCustom() {
        // constant
        {
            log.info("");
            log.info("* Constant lambdas:");

            LambdaValue lValue = null;
            lValue = () -> 10.0D;
            log.info("  - Value[p]=" + lValue.getValue());

            for (int i = 0; i < 5; ++i) {
                lValue = () -> Math.random() * 100.0;
                log.info("  - Value[" + i + "]=" + lValue.getValue());
            }

            lValue = () -> -10.0;
            log.info("  - Value[n]=" + lValue.getValue());

            ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "Hi");
            log.info("  - ThreadLocal[s]=" + threadLocal.get());
        }

        // expression
        {
            log.info("");
            log.info("* Expression lambdas:");

            int n = 10;
            LambdaCheck lEven = (x) -> x % 2 == 0;
            log.info("  - Even[" + n + "]=" + lEven.check(n));
            log.info("  - Even[" + ++n + "]=" + lEven.check(n));

            // single parameter - without brackets
            log.info("");
            LambdaCheck lSign = x -> x > 0;
            int l = 10;
            log.info("  - Sign[" + l + "]=" + lSign.check(l));
            log.info("  - Sign[" + (l *= -1) + "]=" + lSign.check(l));

            log.info("");
            LambdaCheck lAbs = x -> Math.abs(x) == x;
            int m = 10;
            log.info("  - Abs[" + m + "]=" + lAbs.check(m));
            log.info("  - Abs[" + (m *= -1) + "]=" + lAbs.check(m));

            // several parameters
            log.info("");
            LambdaFactor lFactor = (x, y) -> x % y == 0;
            log.info("  - Factor[" + n + "," + m + "]=" + lFactor.invoke(n, m));
            n = 12;
            m = 4;
            log.info("  - Factor[" + n + "," + m + "]=" + lFactor.invoke(n, m));
        }

        // block lambdas with return
        {
            log.info("");
            log.info("* Block lambdas:");

            // lambda with body - into brackets
            int n = 10;
            int m = 10;
            LambdaFunc lFunc = (x) -> {
                int result = 1;
                for (int i = 1; i <= x; ++i) {
                    result *= i;
                }
                return result;
            };
            log.info("  - Factorial[" + n + "]=" + lFunc.func(n));
            log.info("  - Factorial[" + m + "]=" + lFunc.func(m));

            // lambda with body - use return statement
            log.info("");
            String s1 = "Hello,";
            String s2 = "World!";
            LambdaInvert lInvert = (in) -> {
                String result = "";
                for (int i = in.length() - 1; i >= 0; --i) {
                    result += in.charAt(i);
                }
                return result;
            };
            log.info("  - Reverse[" + s1 + "]=" + lInvert.invert(s1));
            log.info("  - Reverse[" + s2 + "]=" + lInvert.invert(s2));
        }

        // parameters as arrays
        {
            log.info("");
            log.info("* Array lambdas:");

            String[] arr0 = null;
            String[] arr1 = new String[]{"One", "Two", "Three", "Four"};
            String[] arr2 = {"1", "2", null, "3", "4", "", "5"};
            LambdaConcat lConcat = (arr) -> {
                if (arr == null) return "None";
                StringBuilder sb = new StringBuilder();
                for (String item : arr) {
                    sb.append(item).append("|");
                }
                return sb.toString();
            };
            log.info("  - Concat[" + Arrays.toString(arr0) + "]=" + lConcat.concat(arr0));
            log.info("  - Concat[" + Arrays.toString(arr1) + "]=" + lConcat.concat(arr1));
            log.info("  - Concat[" + Arrays.toString(arr2) + "]=" + lConcat.concat(arr2));
        }

        // generic lambda
        {
            log.info("");
            log.info("* Generic lambdas:");

            LambdaGeneric<Integer> lGenInt = (num) -> num * num;
            log.info("  - Generic[int]=" + lGenInt.gen(500));

            LambdaGeneric<String> lGenStr = (str) -> str.toUpperCase();
            log.info("  - Generic[str]=" + lGenStr.gen("Bank"));
        }

        // arguments
        {
            log.info("");
            log.info("* Lambdas as parameters:");

            class Lambdas {

                // lambda as parameter
                String trim(LambdaGeneric<String> lambda, String line) {
                    return lambda.gen(line);
                }
            }

            Lambdas lambdas = new Lambdas();
            String in = "Hello, this is the World!";
            String out = lambdas.trim(
                (str) -> {
                    String result = "";
                    for (int i = 0; i < str.length(); ++i) {
                        if (str.charAt(i) == ' ') continue;
                        result += str.charAt(i);
                    }
                    return result;
                },
                in
            );
            log.info("  - In = {}", in);
            log.info("  - Out = {}", out);
        }

        // overloading
        {
            log.info("");
            log.info("* Lambdas overloading:");

            // ambiguous method call, Predicate<Integer> vs LambdaOverloading
            Integer value = 10;

            // compilation error - rename second check method with the name of first
            var issue = CustomLambdaUtils.checkNumber(x -> x > 5, value);
            var issue2 = CustomLambdaUtils.checkNumberOverloading(x -> x > 5, value);
            log.info("* Lambdas with overloading: {} vs {}", issue, issue2);
        }

        // exceptions
        {
            log.info("");
            log.info("* Lambdas with exceptions:");

            // lambda can throw exception
            LambdaException indexFunc = (x) -> {
                double sum = 0.0;
                if (x.length == 0)
                    throw new ArrayIndexOutOfBoundsException("  - Len is zero.");
                for (double x1 : x) sum += x1;
                return sum / (double) x.length;
            };
            try {
                log.info("  - Avg[F]=" + indexFunc.func(new double[]{1.0, 2.0, 3.0, 4.0, 5.0}));
                log.info("  - Avg[E]=" + indexFunc.func(new double[]{}));
            } catch (EmptyArrayException | ArrayIndexOutOfBoundsException e) {
                log.error(e.getMessage());
            }

            // handle unchecked exceptions
            List<String> integers = Arrays.asList("44", "373", "xyz", "145");
            integers.forEach(
                HandlingUncheckedConsumer.uncheckedHandling(
                    i -> log.info(String.valueOf(Integer.parseInt(i))),
                    NumberFormatException.class
                )
            );

            List<Integer> ints = Arrays.asList(5, 10, 0, 15);
            ints.forEach(
                HandlingUncheckedConsumer.uncheckedHandling(
                    i -> log.info(String.valueOf(1000 / i)),
                    ArithmeticException.class
                )
            );

            // handle checked exceptions
            List<Integer> list = Arrays.asList(5, 4, 3, 2, 1);
            list.forEach(
                HandlingCheckedConsumer.checkedHandling(
                    i -> Thread.sleep(i)
                )
            );
        }

        // variable capture
        {
            log.info("");
            log.info("* Lambdas variable capture:");

            // closures - lambdas with captured variables
            class ClosureCapture {
                int value;

                public ClosureCapture(int value) {
                    this.value = value;
                }

                // method with value in enclosing scope
                private int test() {
                    LambdaGeneric<Integer> lGenInt = (num) -> {
                        var result = 0;
                        result = num * num;
                        value = result;
                        return result;
                    };
                    return lGenInt.gen(value);
                }

                // variables used in lambda expression should be final or effectively final
                private int test(Integer value1) {
                    int value2 = 300;
                    LambdaGeneric<Integer> lGenInt = (num) -> {
                        var result = 0;
                        // mutable local variable
                        result = num * num;
                        // value1 = result; -- unable
                        // value2 = result; -- unable
                        return result;
                    };
                    return lGenInt.gen(value1);
                }
            }

            ClosureCapture capture = new ClosureCapture(100);
            log.info("  - Capture[v1]=" + capture.test() + ", value=" + capture.value);
            log.info("  - Capture[v2]=" + capture.test(200) + ", value=" + capture.value);
        }
    }

    /**
     * Reference methods.
     */
    private void invokeReference() {
        // method references - since Java 8

        // this vs super
        {
            Runnable thisInfo = this::toString;
            log.info("Reference [this]: " + thisInfo);
            thisInfo.run();

            Runnable superInfo = super::toString;
            log.info("Reference [super]: " + superInfo);
            superInfo.run();
        }

        log.info("");

        // method reference - static method
        {
            log.info("1) Method reference: static method");

            // direct: (args) -> ClassName.staticMethod(args) == ClassName::staticMethod
            {
                List<Integer> list = Arrays.asList(12, 5, 45, 18, 33, 24, 40);
                List<Integer> result;

                // using an anonymous class
                result = filterNumbers(
                    list,
                    new BiPredicate<Integer, Integer>() {
                        public boolean test(Integer i1, Integer i2) {
                            return isMoreThanFifty(i1, i2);
                        }
                    }
                );
                log.info(" - anonymous class: {}", result);

                // using a lambda expression
                result = filterNumbers(list, (i1, i2) -> isMoreThanFifty(i1, i2));
                log.info(" - lambda expression: {}", result);

                // using a method reference
                result = filterNumbers(list, ReferenceMethodStatic::isMoreThanFifty);
                log.info(" - method reference: {}", result);
            }

            // generics: (args) -> ClassName.staticMethod(args) == ClassName::<Type>staticMethod
            {
                int count;

                Integer[] intArr = new Integer[] {1, 2, 3, 4, 2, 3, 4, 4, 5};
                count = funcOperation(ReferenceMethodStatic::<Integer>countMatching, intArr, 4);
                log.info(" - Array /integer/ has " + count + " elements of number 4.");

                String[] strArr = new String[] {"One", "Two", "Three", "Four", "Two"};
                count = funcOperation(ReferenceMethodStatic::<String>countMatching, strArr, "Two");
                log.info(" - Array /string/ has " + count + " elements of literal 2");

                Date[] dateArr = new Date[] {new Date(), Date.from(Instant.now())};
                count = funcOperation(ReferenceMethodStatic::countMatching, dateArr, new Date());
                log.info(" - Array /date/ has " + count + " elements of literal current date");
            }
        }

        log.info("");

        // method reference - instance method of an object of a particular type
        {
            log.info("2) method reference: instance method of an object of a particular type");

            // direct: (obj, args) -> obj.instanceMethod(args) == ObjectType::instanceMethod
            {
                List<ReferenceMethodType> list = List.of(
                    new ReferenceMethodType(Math.PI),
                    new ReferenceMethodType(Math.E),
                    new ReferenceMethodType(0L)
                );
                List<Double> result;

                // using an anonymous class
                result = calculateWeights(
                    list,
                    new BiFunction<ReferenceMethodType, Integer, Double>() {
                        public Double apply(ReferenceMethodType type, Integer coefficient) {
                            return type.calculateWeight(coefficient);
                        }
                    }
                );
                log.info(" - anonymous class: {}", result);

                // using a lambda expression
                result = calculateWeights(list, (type, coefficient) -> type.calculateWeight(coefficient));
                log.info(" - lambda expression: {}", result);

                // using a method reference
                result = calculateWeights(list, ReferenceMethodType::calculateWeight);
                log.info(" - method reference: {}", result);
            }

            // generic: (obj, args) -> obj.instanceMethod(args) == ObjectType::<Type>instanceMethod
            {
                int count;
                ReferenceMethodType type = new ReferenceMethodType();

                Integer[] intArr = new Integer[] {1, 2, 3, 4, 2, 3, 4, 4, 5};
                count = type.numberOp((ref, arr, v) -> ref.countMatching(arr, v), intArr, 4);
                log.info(" - Array /integer/ has " + count + " elements of number 4.");

                String[] strArr = new String[] {"One", "Two", "Three", "Four", "Two"};
                count = type.numberOp(ReferenceMethodType::<String>countMatching, strArr, "Two");
                log.info(" - Array /string/ has " + count + " elements of literal 2");

                Date[] dateArr = new Date[] {new Date(), Date.from(Instant.now())};
                count = type.numberOp(ReferenceMethodType::countMatching, dateArr, new Date());
                log.info(" - Array /date/ has " + count + " elements of literal current date");
            }
        }

        log.info("");

        // method reference - instance method of an existing object
        {
            log.info("3) method reference: instance method of an existing object");

            // (args) -> obj.instanceMethod(args) == obj::instanceMethod
            {
                final ReferenceMethodObject.Mechanic mechanic = new ReferenceMethodObject.Mechanic("Roman");
                final Laptop laptop = new Laptop(UUID.randomUUID(), "Black");
                final ReferenceMethodObject objectInstance = new ReferenceMethodObject(laptop, mechanic);

                double result;

                // using an anonymous class
                result = objectInstance.fixLaptop(laptop, new Function<Laptop, Double>() {
                    public Double apply(Laptop c) {
                        return mechanic.fix(c);
                    }
                });
                log.info(" - anonymous class: {}", result);

                // using a lambda expression
                result = objectInstance.fixLaptop(laptop, c -> mechanic.fix(c));
                log.info(" - lambda expression: {}", result);

                // using a method reference
                result = objectInstance.fixLaptop(laptop, mechanic::fix);
                log.info(" - method reference: {}", result);
            }

            // generic: (args) -> obj.instanceMethod(args) == obj::<Type>instanceMethod
            {
                int count;
                ReferenceMethodObject object = new ReferenceMethodObject();

                Integer[] intArr = new Integer[] {1, 2, 3, 4, 2, 3, 4, 4, 5};
                count = object.numberOp(object::<Integer>countMatching, intArr, 4);
                log.info(" - Array /integer/ has " + count + " elements of number 4.");

                String[] strArr = new String[] {"One", "Two", "Three", "Four", "Two"};
                count = object.numberOp(object::<String>countMatching, strArr, "Two");
                log.info(" - Array /string/ has " + count + " elements of literal 2");

                Date[] dateArr = new Date[] {new Date(), Date.from(Instant.now())};
                count = object.numberOp(object::countMatching, dateArr, new Date());
                log.info(" - Array /date/ has " + count + " elements of literal current date");
            }
        }

        log.info("");

        // method reference - constructor
        {
            log.info("4) method reference: constructor");

            // direct: (args) -> new ClassName(args) == ClassName::new
            {

                // 4.0 - custom (any arguments) -> interface
                {
                    log.info(" * Any args constructor");

                    ReferenceMethodConstructor.ConstructorNoParameters constructorEmpty = ReferenceMethodConstructor::new;
                    log.info(" - method reference [no parameters]: {}", constructorEmpty.get());

                    ReferenceMethodConstructor.ConstructorWithParameters constructorFull = ReferenceMethodConstructor::new;
                    log.info(" - method reference [with parameters]: {}", constructorFull.get(UUID.randomUUID(), DEF_NAME, DEF_STATE, new Object()));
                }

                // 4.1 - no arguments -> supplier lambda
                {
                    log.info(" * No args constructor");
                    Supplier<List<String>> supplier;
                    List<String> result;

                    // using an anonymous class
                    supplier = new Supplier() {
                        public List<String> get() {
                            return new ArrayList<String>();
                        }
                    };
                    result = supplier.get();
                    log.info(" - anonymous class: {}", result);

                    // using a lambda expression
                    supplier = () -> new ArrayList<String>();
                    result = supplier.get();
                    log.info(" - lambda expression: {}", result);

                    // using a method reference
                    supplier = ArrayList::new;
                    result = supplier.get();
                    log.info(" - method reference: {}", result);
                }

                // 4.2 - 1 argument -> function
                {
                    log.info(" * Single arg constructor");
                    Function<Long, Date> function;
                    Date result;

                    // using an anonymous class
                    function = new Function<Long, Date>() {
                        public Date apply(Long l) {
                            return new Date(l);
                        }
                    };
                    result = function.apply(System.currentTimeMillis());
                    log.info(" - anonymous class: {}", result);

                    // using a lambda expression
                    function = l -> Date.from(Instant.ofEpochMilli(l));
                    result = function.apply(System.currentTimeMillis());
                    log.info(" - lambda expression: {}", result);

                    // using a method reference
                    function = Date::new;
                    result = function.apply(System.currentTimeMillis());
                    log.info(" - method reference: {}", result);
                }

                // 4.3 - 2 arguments -> bi-function
                {
                    log.info(" * Multiple args constructor");
                    BiFunction<String, String, Locale> biFunction;
                    Locale result;

                    // using an anonymous class
                    biFunction = new BiFunction<String, String, Locale>() {
                        public Locale apply(String lang, String country) {
                            return new Locale(lang, country);
                        }
                    };
                    result = biFunction.apply("en", "UK");
                    log.info(" - anonymous class: {}", result);

                    // using a lambda expression
                    biFunction = (lang, country) -> new Locale(lang, country);
                    result = biFunction.apply("fr", "FR");
                    log.info(" - lambda expression: {}", result);

                    // using a method reference
                    biFunction = Locale::new;
                    result = biFunction.apply("uk", "UA");
                    log.info(" - method reference: {}", result);
                }
            }

            // generics: (args) -> new ClassName(args) == ClassName<Type>::new
            {
                log.info(" * Generics args constructor");

                ReferenceMethodConstructor<?> referenceConstructor;

                ReferenceMethodConstructor.ConstructorNoParameters constructorEmpty = ReferenceMethodConstructor::new;
                referenceConstructor = constructorEmpty.get();
                log.info(" - method reference [no parameters]: {}", referenceConstructor);

                ReferenceMethodConstructor.ConstructorOneParameter<Long> constructorOne = ReferenceMethodConstructor::new;
                referenceConstructor = constructorOne.get(Long.MAX_VALUE);
                log.info(" - method reference [one parameter]: {}", referenceConstructor);

                ReferenceMethodConstructor.ConstructorWithParameters<String> constructorFull = ReferenceMethodConstructor::new;
                referenceConstructor = constructorFull.get(UUID.randomUUID(), DEF_NAME, DEF_STATE, "Done.");
                log.info(" - method reference [with parameters]: {}", referenceConstructor);
            }
        }
    }

    /**
     * Pattern lambdas.
     */
    private void invokePattern() {
        // builder pattern
        {
            log.info("");
            log.info("* Lambdas builder pattern:");

            FluentMailer.send(
                mailer -> mailer
                    .from("source@agiledeveloper.com")
                    .to("target@agiledeveloper.com")
                    .subject("build notification")
                    .body("...much better...")
            );
        }

        // strategy pattern
        {
            log.info("");
            log.info("* Lambdas strategy pattern:");

            final List<Asset> assets = Arrays.asList(
                new Asset(Asset.AssetType.BOND, 1000),
                new Asset(Asset.AssetType.BOND, 2000),
                new Asset(Asset.AssetType.STOCK, 3000),
                new Asset(Asset.AssetType.STOCK, 4000)
            );

            // not correct development
            log.info(" - Not correct programming:");
            log.info(
                "   Total of all assets: {}",
                AssetUtils.totalAssetValues(assets)
            );
            log.info(
                "   Total of bond assets: {}",
                AssetUtils.totalBondValues(assets)
            );
            log.info(
                "   Total of stock assets: {}",
                AssetUtils.totalStockValues(assets)
            );

            // correct strategy use-case
            log.info(" - Is correct strategy pattern:");
            log.info(
                "   Total of all assets: {}",
                AssetUtils.totalAssetValues(assets, asset -> true)
            );
            log.info(
                "   Total of bond assets: {}",
                AssetUtils.totalAssetValues(assets, asset -> asset.getType() == Asset.AssetType.BOND)
            );
            log.info(
                "   Total of stock assets: {}",
                AssetUtils.totalAssetValues(assets, asset -> asset.getType() == Asset.AssetType.STOCK)
            );
        }

        // decorator pattern
        {
            log.info("");
            log.info("* Lambdas decorator pattern:");

            final Account account = new Account();

            final Consumer<String> printBalance = (operationInfo) ->
                log.info("Account: balance with {} is {}", operationInfo,
                    account.capture(new Balance(1000)));

            log.info("1) no operations");
            printBalance.accept("no operations");

            log.info("2) income 200");
            account.setOperations(balance -> balance.income(200));
            printBalance.accept("income (200) operation");

            log.info("3) outcome 300");
            account.setOperations(balance -> balance.outcome(300));
            printBalance.accept("outcome (300) operation");

            log.info("4) bonus =)");
            account.setOperations(Balance::bonus);
            printBalance.accept("bonus operation");

            log.info("5) taxes =(");
            account.setOperations(Balance::taxes);
            printBalance.accept("taxes operation");

            log.info("6) chain of operations");
            account.setOperations(
                balance -> balance.income(200),
                Balance::taxes,
                balance -> balance.outcome(500),
                Balance::bonus
            );
            printBalance.accept("income & outcome, bonus and taxes");
        }

        // execute around method (EAM) pattern
        {
            log.info("");
            log.info("* Lambdas EAM pattern:");

            log.info("EAM: start");
            try {
                URL sourceUrl = getClass().getClassLoader().getResource("file");
                File eamSingle = new File(sourceUrl.getFile() + File.separator + "eam-single.txt");
                eamSingle.createNewFile();
                File eamMulti = new File(sourceUrl.getFile() + File.separator + "eam-multi.txt");
                eamMulti.createNewFile();
                File eamSlogan = new File(sourceUrl.getFile() + File.separator + "eam-slogan.txt");
                eamSlogan.createNewFile();

                // file as line
                FileWriterEam.use(
                    eamSingle.getAbsolutePath(),
                    writerEAM -> writerEAM.writeStuff("lambda")
                );

                // file per multiple lines
                FileWriterEam.use(
                    eamMulti.getAbsolutePath(),
                    writerEAM -> {
                        writerEAM.writeStuff("how");
                        writerEAM.writeStuff("sweet");
                        writerEAM.writeStuff("lambdas");
                    }
                );

                // file as text
                FileWriterEam.use(
                    eamSlogan.getAbsolutePath(),
                    FileWriterEam::writeSlogan
                );
            } catch (IOException e) {
                log.error("EAM: error - {}", e.getMessage());
            }
            log.info("EAM: stop");
        }

        // handling exceptions
        {
            log.info("");
            log.info("* Lambdas handling exceptions:");

            HandleError<String, IOException> objectify = (s) -> {
                var size = s.chars().count();
                if (size >= 10) {
                    log.info("Object '{}', Length {}", s, size);
                } else {
                    throw new IOException("IO Exception in action.");
                }
            };
            try {
                objectify.accept("Handling Exceptions");
                objectify.accept("Handling");
            } catch (Exception e) {
                log.error("Error is {}", e.getMessage());
            }
        }

        // handling locking
        {
            log.info("");
            log.info("* Lambdas handling locking:");

            Locking locking = new Locking();
            locking.doOp1();
            locking.doOp2();
            locking.doOp3();
            locking.doOp4();
            locking.doOp5(() -> log.info("Locks with Lambdas"));
        }

        // lazy initialization
        {
            log.info("");
            log.info("* Lambdas lazy initialization:");

            log.info("");
            // native - old fashion
            log.info("Simulation [native]: begin");
            final var holderNative = new HolderNative();
            holderNative.getHeavy().process();
            holderNative.getHeavy().process();
            log.info("Simulation [native]: end");

            log.info("");
            // lambda - new fashion
            log.info("Simulation [lambda]: begin");
            final var holderLambda = new HolderLambda();
            holderLambda.getHeavy().process();
            holderLambda.getHeavy().process();
            log.info("Simulation [lambda]: end");
        }

        // lazy evaluation
        {
            log.info("");
            log.info("* Lambdas lazy evaluation:");

            log.info("");
            // eager
            log.info("Evaluation [eager]: begin");
            Evaluation.eagerEvaluator(
                Evaluation.evaluate(1),
                Evaluation.evaluate(2)
            );
            log.info("Evaluation [eager]: end");

            log.info("");
            // lazy
            log.info("Evaluation [lazy]: begin");
            Evaluation.lazyEvaluator(
                () -> Evaluation.evaluate(1),
                () -> Evaluation.evaluate(2)
            );
            log.info("Evaluation [lazy]: end");
        }

        // lambda recursion
        {
            log.info("");
            log.info("* Lambdas TCO recursion:");

            log.info("");
            // blunt
            log.info("Recursion [blunt]: begin");
            log.info(
                "Recursion [blunt]: {}",
                Factorial.factorialRec(5)
            );
            log.info("Recursion [blunt]: end");

            log.info("");
            // lambda
            log.info("Recursion [lambda]: begin");
            log.info(
                "Recursion [lambda]: {}",
                Factorial.factorial(5)
            );
            log.info("Recursion [lambda]: end");
        }

        // lambda memoization
        {
            log.info("");
            log.info("* Lambdas cache memoization:");

            log.info(" - The rod-cutting problem");

            log.info("");
            // basic
            log.info("No memoization [basic]: begin");
            final var rodCutter = new RodCutter(RodCutter.priceValues);
            log.info(
                "No memoization [basic]: {}",
                rodCutter.maxProfit(5)
            );
            log.info(
                "No memoization [basic]: {}",
                rodCutter.maxProfit(20)
            );
            log.info("No memoization [basic]: end");

            log.info("");
            // cache
            log.info("With memoization [cache]: begin");
            log.info(
                "With memoization [cache]: {}",
                rodCutter.maxProfitMemoized(5)
            );
            log.info(
                "With memoization [cache]: {}",
                rodCutter.maxProfitMemoized(20)
            );
            log.info("With memoization [cache]: end");
        }
    }

    /**
     * Pattern lambdas.
     */
    private void invokeScenario() {
        // emulation
        {
            log.info("");
            log.info("* Lambdas emulation:");

            // lambda-based emulation
            var value = EmulationUtils.valueEmulate(
                "Value",
                String::toUpperCase
            );
            log.info("  - Emulation: {}", value);

            // anonymous-class-based emulation
            value = EmulationUtils.valueEmulate(
                "Value",
                new ClassEmulation<>() {

                    @Override
                    public String simulate(String value) {
                        return value.toUpperCase();
                    }
                }
            );
            log.info("  - Simulation: {}", value);
        }

        // comparison
        {
            log.info("");
            log.info("* Lambdas comparison:");

            List<Product> products = new ArrayList<>(
                List.of(
                    new Product(1L, "Laptop", 20_000D, true),
                    new Product(2L, "Tablet", 20_000D, true),
                    new Product(3L, "Mobile", 10_000D, true),
                    new Product(4L, "Headphones", 5_000D, false),
                    new Product(5L, "Keyboard", 10_000D, true)
                )
            );

            // comparator as lambda expression
            products.sort(
                Comparator.comparingDouble(
                    Product::price
                ).thenComparing(
                    Product::name
                ).thenComparingLong(
                    Product::id
                )
            );

            log.info("  - Products:");
            products.forEach(product -> log.info(product.toString()));
        }

        // stream
        {
            log.info("");
            log.info("* Lambdas in streams:");

            List<Place> places = new ArrayList<>(
                List.of(
                    new Place(1L, "Ukraine, Kyiv", true),
                    new Place(2L, "Italy, Milan", true),
                    new Place(3L, "Ukraine, Kharkiv", true),
                    new Place(4L, "England, London", true),
                    new Place(5L, "Germany, Berlin", false),
                    new Place(6L, "Ukraine, Odesa", false),
                    new Place(7L, "Spain, Madrid", true),
                    new Place(8L, "Ukraine, Lviv", true),
                    new Place(9L, "France, Paris", false)
                )
            );

            // stream processing with lambda expressions
            log.info("  - Places:");
            places.stream()
                .filter(Place::active)
                .distinct()
                .limit(100)
                .filter(p -> p.line().startsWith("Ukraine"))
                .peek(p -> log.info("  + peek: {}", p.id()))
                .map(p -> p.line().toUpperCase())
                .sorted()
                .forEach(p -> log.info("  - peek: {}", p));
        }
    }
}
