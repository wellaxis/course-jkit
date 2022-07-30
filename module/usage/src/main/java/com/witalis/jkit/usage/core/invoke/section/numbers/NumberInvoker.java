package com.witalis.jkit.usage.core.invoke.section.numbers;

import com.witalis.jkit.usage.core.invoke.Invoker;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Desc: Number declaration
 * User: Wellaxis
 * Date: 2019/11/16
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class NumberInvoker extends Invoker {

    public NumberInvoker() {
        setTitle("Numbers chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // constants
        log.info("## Constants");
        invokeConstants();
        // tab
        log.info("");
        // computation
        log.info("## Computation");
        invokeComputation();
        // tab
        log.info("");
        // big numbers
        log.info("## Big Numbers");
        invokeBigNumbers();
        // tab
        log.info("");
        // utilities
        log.info("## Utils");
        invokeUtils();
    }

    /**
     * Basic data types definitions.
     */
    private void invokeBasis() {
        // The abstract class Number is the superclass of classes BigDecimal, BigInteger, Byte, Double, Float, Integer, Long, and Short.

        log.info("Subclasses of Number must provide methods to convert the represented numeric value to byte, double, float, int, long, and short.");
    }

    /**
     * Constants of numbers.
     */
    private void invokeConstants() {
        log.info("Type: min, max, bytes, size");

        // byte
        {
            int min = Byte.MIN_VALUE;
            int max = Byte.MAX_VALUE;
            int bytes = Byte.BYTES;
            int size = Byte.SIZE;
            log.info("Byte: {}, {}, {}, {}", min, max, bytes, size);
        }

        // short
        {
            int min = Short.MIN_VALUE;
            int max = Short.MAX_VALUE;
            int bytes = Short.BYTES;
            int size = Short.SIZE;
            log.info("Short: {}, {}, {}, {}", min, max, bytes, size);
        }

        // int
        {
            int min = Integer.MIN_VALUE;
            int max = Integer.MAX_VALUE;
            int bytes = Integer.BYTES;
            int size = Integer.SIZE;
            log.info("Integer: {}, {}, {}, {}", min, max, bytes, size);
        }

        // long
        {
            long min = Long.MIN_VALUE;
            long max = Long.MAX_VALUE;
            int bytes = Long.BYTES;
            int size = Long.SIZE;
            log.info("Long: {}, {}, {}, {}", min, max, bytes, size);
        }

        // float
        {
            float min = Float.MIN_VALUE;
            float max = Float.MAX_VALUE;
            int bytes = Float.BYTES;
            int size = Float.SIZE;
            log.info("Float: {}, {}, {}, {}", min, max, bytes, size);

            float naN = Float.NaN;
            float minNormal = Float.MIN_NORMAL;
            int minExponent = Float.MIN_EXPONENT;
            int maxExponent = Float.MAX_EXPONENT;
            float negativeInfinity = Float.NEGATIVE_INFINITY;
            float positiveInfinity = Float.POSITIVE_INFINITY;
            log.info(
                "Float [extra]: {}, {}, {}, {}, {}, {}",
                naN, minNormal, minExponent, maxExponent,
                negativeInfinity, positiveInfinity
            );
        }

        // double
        {
            double min = Double.MIN_VALUE;
            double max = Double.MAX_VALUE;
            int bytes = Double.BYTES;
            int size = Double.SIZE;
            log.info("Double: {}, {}, {}, {}", min, max, bytes, size);

            double naN = Double.NaN;
            double minNormal = Double.MIN_NORMAL;
            int minExponent = Double.MIN_EXPONENT;
            int maxExponent = Double.MAX_EXPONENT;
            double negativeInfinity = Double.NEGATIVE_INFINITY;
            double positiveInfinity = Double.POSITIVE_INFINITY;
            log.info(
                "Double [extra]: {}, {}, {}, {}, {}, {}",
                naN, minNormal, minExponent, maxExponent,
                negativeInfinity, positiveInfinity
            );
        }
    }

    /**
     * Numeric calculations.
     */
    @SuppressWarnings("checked")
    private void invokeComputation() {
        // wrappers
        {
            log.info("---- Wrappers:");

            log.info(" - Is NaN: " + Double.isNaN(Double.NaN));
            log.info(" - Is Infinite: " + Double.isInfinite(Double.NEGATIVE_INFINITY));
            log.info(" - Min: " + Double.min(10D, 20D));
            log.info(" - Max: " + Double.max(10D, 20D));
            log.info(" - Sum: " + Double.sum(10D, 20D));
        }

        // operations
        {
            log.info("---- Operations:");

            // short vs full forms:
            // x = (XType)(x + y)
            int x = 2;
            x += 0.2;
            log.info("Short form: {}", x);
            x = (int) (x + 0.5);
        }

        // comparison
        {
            log.info("---- Comparison:");

            double pi = 3.14159;
            Double pd = Double.valueOf(pi);
            Double pe = Double.valueOf("314159E-5");
            log.info(" - Compare: " + pd.equals(pe));
        }

        // values
        {
            log.info("---- Values:");

            Double d1 = Double.valueOf(1 / 0.);
            Double d2 = Double.valueOf(0 / 0.);
            log.info(d1 + " : " + d1.isInfinite() + " " + d1.isNaN());
            log.info(d2 + " : " + d2.isInfinite() + " " + d2.isNaN());
        }

        // parsing
        {
            log.info("---- Parsing:");

            try {
                int value = Integer.parseInt("11");
                log.info(" - Parse: " + value);
                value = Integer.parseInt("xe");
            } catch (NumberFormatException nfe) {
                log.error("Incorrect number format: " + nfe.getMessage());
            }
        }

        // formatting
        {
            log.info("---- Formatting:");

            int value = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
            log.info(" - Format[Decimal]: " + value);
            log.info(" - Format[Binary]: " + Integer.toBinaryString(value));
            log.info(" - Format[Hex]: " + Integer.toHexString(value));
            log.info(" - Format[Octal]: " + Integer.toOctalString(value));
        }

        // statistics
        {
            log.info("---- Summary Statistics:");

            var statistics = new DoubleSummaryStatistics();

            statistics.accept(1);
            statistics.accept(2);
            statistics.accept(3);
            log.info(" - Min: {}", statistics.getMin());
            log.info(" - Max: {}", statistics.getMax());
            log.info(" - Sum: {}", statistics.getSum());
            log.info(" - Count: {}", statistics.getCount());
            log.info(" - Average: {}", statistics.getAverage());
        }
    }

    /**
     * Operations with big numbers.
     */
    @SuppressWarnings("checked")
    private void invokeBigNumbers() {
        // integer
        {
            BigInteger i = new BigInteger("1111");
            log.info(" - BigInteger[i]: {}", i);
        }

        // decimal
        {
            BigDecimal a = new BigDecimal("1.1");
            log.info(" - BigDecimal[a]: {}", a);
            BigDecimal b = new BigDecimal("3");
            log.info(" - BigDecimal[b]: {}", b);

            // truncate value - 2 digits after comma with rounding down.
            BigDecimal c = a.divide(b, new MathContext(2, RoundingMode.DOWN));
            log.info(" - BigDecimal[c]: {}", c);

            BigDecimal d = new BigDecimal("0.36000");
            log.info(" - BigDecimal[d]: {}", d);
            // different precision
            assert !c.equals(d);
            // equality by maths
            assert c.compareTo(d) == 0;

            BigDecimal e = BigDecimal.valueOf(0.36);
            log.info(" - BigDecimal[e]: {}", e);
            // e equality by maths
            assert c.compareTo(e) == 0;

            BigDecimal f = new BigDecimal(0.36);
            log.info(" - BigDecimal[f]: {}", f);
            // f has value, like 0.35999999999999...
            assert c.compareTo(f) != 0;
        }
    }

    /**
     * String utilities.
     */
    private void invokeUtils() {
        double value = 0.5D;
        double sin = Math.sin(value);
        log.info("Math[sin]: {} -> {}", value, sin);
    }
}
