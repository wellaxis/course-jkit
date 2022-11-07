package com.witalis.jkit.utils.cpu;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Cache L1 Size
 * User: Wellaxis
 * Date: 15.02.2022
 */
@Slf4j
public class CacheL1Size {
    public static final int QTY = 20;
    public static final int SIZE = 64 * 1024;

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
     * Detect size of CPU L1 cache:
     * -------------------------------------
     * len: 8192, dt: 8600, 10*dt/len: 10
     * len: 16384, dt: 18500, 10*dt/len: 11
     * len: 24576, dt: 27100, 10*dt/len: 11
     * len: 32768, dt: 34700, 10*dt/len: 10
     * len: 40960, dt: 116400, 10*dt/len: 28
     * len: 49152, dt: 142000, 10*dt/len: 28
     * len: 57344, dt: 161300, 10*dt/len: 28
     * len: 65536, dt: 185700, 10*dt/len: 28
     * -------------------------------------
     * performance decline after 32 kilobytes, so L1 = 32 KB
     * -------------------------------------
     */
    private static void scenario(byte[] array) {
        for (int len = 8192; len <= array.length; len += 8192) {
            long t0 = System.nanoTime();
            for (int n = 0; n < 100; n++) {
                for (int index = 0; index < len; index += 64) {
                    array[index] = 1;
                }
            }
            long dt = System.nanoTime() - t0;
            log.info("len: " + len + ", dt: " + dt + ", 10*dt/len: " + (10 * dt) / len);
        }
    }
}
