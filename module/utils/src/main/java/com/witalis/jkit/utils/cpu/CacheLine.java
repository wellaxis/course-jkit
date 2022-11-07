package com.witalis.jkit.utils.cpu;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Cache Line Size
 * User: Wellaxis
 * Date: 15.02.2022
 */
@Slf4j
public class CacheLine {
    public static final int QTY = 20;
    public static final int SIZE = 16 * 1024 * 1024;

    public static void main(String[] args) {
        detection(QTY);
    }

    private static void detection(int quantity) {
        byte[] bytes = new byte[SIZE];

        for (int i = 0; i < quantity; i++) {
            scenario(bytes);
            log.info("------");
        }
    }

    /**
     * Detect size of CPU cache line:
     * -------------------------------------
     * stepSize: 4, dt: 403724800, dt/stepCount: 96
     * stepSize: 8, dt: 194228900, dt/stepCount: 92
     * stepSize: 16, dt: 136161300, dt/stepCount: 129
     * stepSize: 32, dt: 110138600, dt/stepCount: 210
     * stepSize: 64, dt: 104647300, dt/stepCount: 399
     * stepSize: 128, dt: 84724100, dt/stepCount: 646
     * stepSize: 256, dt: 42029800, dt/stepCount: 641
     * stepSize: 512, dt: 20137300, dt/stepCount: 614
     * -------------------------------------
     * performance decline after 64 kilobytes, so cache line = 64 KB
     * -------------------------------------
     */
    private static void scenario(byte[] array) {
        for (int stepSize = 4; stepSize <= 512; stepSize *= 2) {
            int sum = 0;

            long t0 = System.nanoTime();
            for (int n = 0; n < 100; n++) {
                for (int index = 0; index < array.length; index += stepSize) {
                    sum += array[index];
                }
            }
            long dt = System.nanoTime() - t0;

            if (sum > 0) throw new Error();
            int stepCount = SIZE / stepSize;

            log.info("stepSize: " + stepSize + ", dt: " + dt + ", dt/stepCount: " + dt / stepCount);
        }
    }
}
