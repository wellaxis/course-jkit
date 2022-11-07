package com.witalis.jkit.utils.cpu;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Cache L2 Size
 * User: Wellaxis
 * Date: 15.02.2022
 */
@Slf4j
public class CacheL2Size {
    public static final int QTY = 20;
    public static final int SIZE = 512 * 1024;

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
     * Detect size of CPU L2 cache:
     * -------------------------------------
     * len: 65536, dt: 1825800, 10*dt/len: 278
     * len: 131072, dt: 3594100, 10*dt/len: 274
     * len: 196608, dt: 5356100, 10*dt/len: 272
     * len: 262144, dt: 7236600, 10*dt/len: 276
     * len: 327680, dt: 9558300, 10*dt/len: 291
     * len: 393216, dt: 14033200, 10*dt/len: 356
     * len: 458752, dt: 16800400, 10*dt/len: 366
     * len: 524288, dt: 19836700, 10*dt/len: 378
     * -------------------------------------
     * performance decline after 256 kilobytes, so L2 = 256 KB
     * -------------------------------------
     */
    private static void scenario(byte[] array) {
        for (int len = 64 * 1024; len <= array.length; len += 64 * 1024) {
            long t0 = System.nanoTime();
            for (int n = 0; n < 1000; n++) {
                for (int index = 0; index < len; index += 64) {
                    array[index] = 1;
                }
            }
            long dt = System.nanoTime() - t0;
            log.info("len: " + len + ", dt: " + dt + ", 10*dt/len: " + (10 * dt) / len);
        }
    }
}
