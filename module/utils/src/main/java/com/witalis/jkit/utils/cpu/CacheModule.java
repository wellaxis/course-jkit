package com.witalis.jkit.utils.cpu;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: ILP (Instruction Level Parallelism)
 * User: Wellaxis
 * Date: 12.02.2022
 */
@Slf4j
public final class CacheModule {

    private CacheModule() {
        super();
    }

    public static void main(String[] args) {
        singleOperation();
        doubleOperations();
        doubleVariables();
        multipleVariables();
    }

    /**
     * Execution time: 1383
     */
    public static void singleOperation() {
        long t0 = System.currentTimeMillis();
        double d0 = 0;
        for (int i = 0; i < 1_000_000_000; i++) {
            d0 = d0 * d0;
        }
        long t1 = System.currentTimeMillis();
        log.info("Value: {}", d0);
        log.info("Time: {}", t1 - t0);
    }

    /**
     * Execution time: 2706
     */
    public static void doubleOperations() {
        long t0 = System.currentTimeMillis();
        double d0 = 0;
        for (int i = 0; i < 1_000_000_000; i++) {
            d0 = d0 * d0;
            d0 = d0 * d0;
        }
        long t1 = System.currentTimeMillis();
        log.info("Value: {}", d0);
        log.info("Time: {}", t1 - t0);
    }

    /**
     * Execution time: 1378
     */
    public static void doubleVariables() {
        long t0 = System.currentTimeMillis();
        double d0 = 0;
        double d1 = 0;
        for (int i = 0; i < 1_000_000_000; i++) {
            d0 = d0 * d0;
            d1 = d1 * d1;
        }
        long t1 = System.currentTimeMillis();
        log.info("Value: {}", d0 + d1);
        log.info("Time: {}", t1 - t0);
    }

    /**
     * It is possible to calculate qty of instruction modules: ~ 2
     * Execution time: 3030
     */
    public static void multipleVariables() {
        long t0 = System.currentTimeMillis();
        double d0 = 0;
        double d1 = 0;
        double d2 = 0;
        double d3 = 0;
        double d4 = 0;
        double d5 = 0;
        double d6 = 0;
        double d7 = 0;
        double d8 = 0;
        double d9 = 0;
        for (int i = 0; i < 1_000_000_000; i++) {
            d0 = d0 * d0;
            d1 = d1 * d1;
            d2 = d2 * d2;
            d3 = d3 * d3;
            d4 = d4 * d4;
            d5 = d5 * d5;
            d6 = d6 * d6;
            d7 = d7 * d7;
            d8 = d8 * d8;
            d9 = d9 * d9;
        }
        long t1 = System.currentTimeMillis();
        log.info("Value: {}", d0 + d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8 + d9);
        log.info("Time: {}", t1 - t0);
    }
}
