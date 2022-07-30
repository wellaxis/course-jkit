package com.witalis.jkit.usage.core.invoke.section.generics;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.generics.content.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Desc: generics
 * User: Wellaxis
 * Date: 2019/11/19
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
@SuppressWarnings("unchecked")
public class GenericInvoker extends Invoker {

    public GenericInvoker() {
        setTitle("Generics chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // erasure
        log.info("## Erasure");
        invokeErasure();
        // tab
        log.info("");
        // types
        log.info("## Types");
        invokeTypes();
        // tab
        log.info("");
        // wildcards
        log.info("## Wildcards");
        invokeWildcards();
        // tab
        log.info("");
        // methods
        log.info("## Methods");
        invokeMethods();
        // tab
        log.info("");
        // constructors
        log.info("## Constructors");
        invokeConstructors();
        // tab
        log.info("");
        // interfaces
        log.info("## Interfaces");
        invokeInterfaces();
        // tab
        log.info("");
        // hierarchies
        log.info("## Hierarchies");
        invokeHierarchies();
        // tab
        log.info("");
        // exceptions
        log.info("## Exceptions");
        invokeExceptions();
        // tab
        log.info("");
        // restrictions
        log.info("## Restrictions");
        invokeRestrictions();
        // tab
        log.info("");
        // variances
        log.info("-- Variances");
        invokeVariances();
    }

    /**
     * Basic postulates of generics functionalities.
     */
    private void invokeBasis() {
        // generics - since JDK 5
        // generics means parameterized types
        // erasure - process of removing generic type information during compilation

        // only objects - not primitive types
        Gen<Integer> iGen = new Gen<Integer>(99);
        log.info(iGen.show());
        Gen<String> sGen = new Gen<String>("Generics");
        log.info(sGen.show());

        // diamond operator - since JDK 7
        Gen<Boolean> bGen = new Gen<>(true);
        log.info(bGen.show());
        Gen<Float> fGen = new Gen<>(-5.7F);
        log.info(fGen.show());

        // simplify - since JDK 7 (raw type)
        Gen<Long> lGen = new Gen(100L);
        log.info(lGen.show());

        // var operator - since JDK 10
        var vGen = new Gen<String>("var");
        log.info(vGen.show());

        // raw type legacy - for old, pre-generics code
        Gen nGen = new Gen('\u1033');
        log.info(nGen.show());

        // unchecked warnings
        var gen = new Gen(null);
        log.info(gen.show());
    }

    /**
     * Generic erasure.
     */
    private void invokeErasure() {
        // erasure process

        // bridge method - overriding method - only for compiler
        log.info("");

        // erasure process check - javap compilation:
        // java.lang.String getValue();   found method
        // java.lang.Object getValue();   bridge method

        GenStr genStr = new GenStr("Generic String");
        log.info("  Value is: " + genStr.getValue());

        // ambiguity - generic exception - method overloading
    }

    /**
     * Generic types.
     */
    private void invokeTypes() {
        // multiple types
        {
            SuperGen<String, Boolean, Integer> superGen = new SuperGen<>("Hi", true, 100);
            log.info("Super Gen Defined: " + superGen.toString());

            SuperGen supGen = new SuperGen<>("Hi", true, 100);
            log.info("Super Gen Undefined: " + supGen.toString());
        }

        // restrictions on base types - extends, & for each interface
        {
            Stats<Integer> iStats = new Stats<>(new Integer[]{1, 8, -99, 3, -7, 123});
            log.info("Stats[I] average: " + iStats.average());
            log.info("Stats[I] sameAvg: " + iStats.sameAvg(new Stats<>(new Integer[]{0, 1, 2})));

            Stats<Double> dStats = new Stats<>(new Double[]{1.34, 1.8d, 0.99, 3.0, -7d});
            log.info("Stats[D] average: " + dStats.average());
            log.info("Stats[D] sameAvg: " + dStats.wildAvg(new Stats<>(new Integer[]{0, 1, 2})));
        }
    }

    /**
     * Generic wildcards.
     */
    private void invokeWildcards() {
        // bounded wildcards

        Coords coords = new Coords(null);
        coords.showX(coords);
        DimensionalTwo dimensionalTwo = new DimensionalTwo(10, 20);
        DimensionalThree threeD = new DimensionalThree(dimensionalTwo.x, dimensionalTwo.y, 30);
        DimensionalFour fourD = new DimensionalFour(threeD.x, threeD.y, threeD.z, 40);

        log.info("* 2 Dimensions:");
        Coords coords2D = new Coords(new DimensionalTwo[]{dimensionalTwo, threeD, fourD});
        coords.showXY(coords2D);

        log.info("* 3 Dimensions:");
        Coords coords3D = new Coords(new DimensionalThree[]{threeD, fourD, new DimensionalThree(7, 8, 9)});
        coords.showXYZ(coords3D);

        log.info("* 4 Dimensions:");
        Coords coords4D = new Coords(new DimensionalFour[]{fourD, new DimensionalFour(1, 2, 3, 4)});
        coords.showXYZT(coords4D);
    }

    /**
     * Generic methods.
     */
    private void invokeMethods() {
        // usual class, but generic method

        log.info("Comparator: ");
        Comparator comparator = new Comparator();

        Integer[] _iArr = {3, 76, -23, -1, 0, 245};
        log.info("Is In '-1': " + comparator.isIn(-1, _iArr));

        String[] _sArr = {"Hello", "world", "I'm", "java", "here"};
        log.info("Is In 'java': " + comparator.isIn("java", _sArr));

        Float[] _fArr = {Float.valueOf(3.6f), Float.valueOf(29.7f), Float.valueOf(-2.3f), Float.valueOf(-10.0f), Float.valueOf(0.0f), Float.valueOf(245.0f)};
        log.info("Is In '1.23': " + comparator.<Float, Float>isIn(1.23f, _fArr));

        Character[] _cArr = {Character.valueOf('1'), Character.valueOf('a'), Character.valueOf('c'), Character.valueOf('F'), Character.valueOf('C'), Character.valueOf('.')};
        log.info("Is In 'A': " + comparator.<Character, Character>isIn('A', _cArr));

    }

    /**
     * Generic constructors.
     */
    private void invokeConstructors() {
        // generic constructor

        GenCons cons1 = new GenCons(100);
        GenCons cons2 = new GenCons(100L);
        GenCons cons3 = new GenCons(100.1F);
        GenCons cons4 = new GenCons(100.2D);
        cons1.show();
        cons2.show();
        cons3.show();
        cons4.show();
    }

    /**
     * Generic interfaces.
     */
    private void invokeInterfaces() {
        // generic interfaces

        Integer[] _iArr = {3, 76, -23, -1, 0, 245};
        String[] _sArr = {"Hello", "world", "I'm", "java", "here"};
        Float[] _fArr = {Float.valueOf(3.6f), Float.valueOf(29.7f), Float.valueOf(-2.3f), Float.valueOf(-10.0f), Float.valueOf(0.0f), Float.valueOf(245.0f)};
        Character[] _cArr = {Character.valueOf('1'), Character.valueOf('a'), Character.valueOf('c'), Character.valueOf('F'), Character.valueOf('C'), Character.valueOf('.')};

        IntroMinMax introMinMax = null;
        introMinMax = new IntroMinMax(_iArr);
        log.info("Integer: min=" + introMinMax.min() + ", max=" + introMinMax.max());
        introMinMax = new IntroMinMax(_fArr);
        log.info("Float: min=" + introMinMax.min() + ", max=" + introMinMax.max());
        introMinMax = new IntroMinMax(_cArr);
        log.info("Char: min=" + introMinMax.min() + ", max=" + introMinMax.max());
        introMinMax = new IntroMinMax(_sArr);
        log.info("String: min=" + introMinMax.min() + ", max=" + introMinMax.max());
    }

    /**
     * Generic hierarchies.
     */
    private void invokeHierarchies() {
        // generic hierarchies

        Parent<String> parent = new Parent<>("Parent");
        parent.over();
        log.info("Parent: " + parent.show());
        Child<String> child = new Child<>(new String[]{"Child", "Hi", "I'm", "a child"});
        child.over();
        log.info("Child: " + child.show() + ", " + Arrays.toString(child.showAll()));
        Parent<?> backward = new Parent<>(null);
        backward.over();
    }

    /**
     * Generic exceptions.
     */
    private void invokeExceptions() {
        // throw generic-type exception

        var exceptional = new Exceptional<>();
        try {
            exceptional.apply();
        } catch (Exception e) {
            log.error("Throwable generic exception[{}]: {}", e.getClass().getSimpleName(), e.getMessage());
        }
    }

    /**
     * Generic restrictions.
     */
    private void invokeRestrictions() {
        // generic restrictions

        // 1. ob = new T(); - instance of a type parameter
        {
            class Restriction1<R> {
                R object;

                Restriction1() {
                    // object = new R();
                }
            }
        }

        // 2. static T ob; - non static member declaration
        {
            class Restriction2<R> {
                // static R value;
                // static R getValue() { return value; }
            }
        }

        // 3. values = new T[10]; - instance of array
        {
            class Restriction3<R> {
                R value;
                R[] values;

                public Restriction3(R value, R[] values) {
                    this.value = value;
                    this.values = values;
                    // values = new R[100];
                }
            }

            Restriction3<Long> r3 = new Restriction3<>(1L, new Long[]{2L, 3L, 4L});
            // Restriction3<Long>[] r3s = new Restriction3<Long>[100];
            Restriction3<?>[] r3s = new Restriction3<?>[100];
        }

        // 4. throwable - cannot create generic exception class
        {
            // class Restriction4<R> extends Throwable {}
        }
    }

    /**
     * Generic variances.
     */
    private void invokeVariances() {

        class Parent {}

        class Child extends Parent {}

        // covariance
        {
            log.info("Covariance: P->S => F(P) -> F(S), read ops");

            // optional
            {
                Optional<Parent> parent = Optional.of(new Parent());
                Optional<Child> child = Optional.of(new Child());
                // correct
                Optional<Parent> covariance = Optional.of(new Child());

                // incorrect
                // Optional<Child> erroneous = Optional.of(new Parent());
            }

            // array
            {
                Parent[] parents = new Parent[]{new Parent()};
                Child[] children = new Child[]{new Child()};
                // correct
                Parent[] covariance = new Child[]{new Child()};

                // incorrect
                // Child[] erroneous = new Child[] {new Parent()};
            }

            // iterator
            {
                // boolean find(Iterator<? extends T> where, Object what)
            }

            // list (wildcard - extends)
            {
                List<? extends Parent> parents;
                // correct
                parents = new ArrayList<Child>();

                // incorrect
                // List<? extends Child> erroneous = new ArrayList<Parent>();
            }

            // scenario
            {
                // generics with wildcard extends
                List<Integer> integers = new ArrayList<>(List.of(1, 2, 3));
                List<? extends Number> numbers = integers;
                // allowed: read operation
                Number number = numbers.get(0);
                // not allowed: write operation
                // numbers.add(Integer.valueOf(1));
            }
        }

        // contravariance
        {
            log.info("Contravariance: P->S => F(P) <- F(S), write ops");

            // comparator
            {
                // <T> void sort(List<T> what, Comparator<? super T> how)
            }

            // list (wildcard - super)
            {
                List<? super Child> children;
                // correct
                children = new ArrayList<Parent>();

                // incorrect
                // List<? super Parent> erroneous = new ArrayList<Child>();
            }

            // scenario
            {
                // generics with wildcard super
                List<Number> numbers = new ArrayList<>(List.of(1, 2, 3));
                List<? super Integer> integers = numbers;
                // allowed: write operation
                integers.add(Integer.valueOf(1));
                // not allowed: read operation
                // Number number = integers.get(0);
            }
        }

        // invariance
        {
            log.info("Invariance: P->S => F(S) <> F(P), both ops");

            // list
            {
                List<Parent> parents;
                List<Child> children;
                // correct
                parents = new ArrayList<Parent>();
                children = new ArrayList<Child>();

                // incorrect
                // List<Parent> erroneous = new ArrayList<Child>();
                // List<Child> erroneous = new ArrayList<Parent>();
            }
        }
    }
}
