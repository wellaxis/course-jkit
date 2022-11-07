package com.witalis.jkit.utils.performance;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PerformanceRating {
    // constant
    private static final char MD_SUCCESSIVELY = 'S'; // Process: time = 265 sec. main [1]. Done.
    private static final char MD_PARALLEL     = 'P'; // Process: time = 98 sec. main [1]. Done.
    private static final long[] ARR = new long[] {
        1000, 6000,  11000, 16000, 21000,
        2000, 7000,  12000, 17000, 22000,
        3000, 8000,  13000, 18000, 23000,
        4000, 9000,  14000, 19000, 24000,
        5000, 10000, 15000, 20000, 25000,
    };
    // variable
    private char mode;

    public PerformanceRating(char mode) {
        this.mode = mode;
        System.out.println("------------------------------------------------");
        System.out.println(
            "MODE => " + this.mode
        );
        System.out.println("------------------------------------------------");
    }

    private void process() {
        long current = System.currentTimeMillis();
        switch (mode) {
            case MD_SUCCESSIVELY: {
                for (long item : ARR) {
                    congestion(item);
                }
                break;
            }
            case MD_PARALLEL: {
                ExecutorService executor = Executors.newFixedThreadPool(4);
                for (long item : ARR) {
                    Runnable worker = new Runnable() {
                        public void run() {
                            try {
                                congestion(item);
                            } catch (Exception e) {
                                e.printStackTrace();
                                throw new RuntimeException(e.getMessage());
                            }
                        }
                    };
                    executor.execute(worker);
                }
                // finish all existing threads in the queue
                executor.shutdown();
                // wait until all threads are finish
                try {
                    boolean done = executor.awaitTermination(10, TimeUnit.MINUTES);
                    if (!done) {
                        throw new RuntimeException("Timeout");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        System.out.println("");
        System.out.println(
            "Process: time = " + (System.currentTimeMillis() - current) / 1000 + " sec. " +
            Thread.currentThread().getName() + " [" +
            Thread.currentThread().getId() + "]. Done."
        );
        System.out.println("------------------------------------------------");
        System.out.println("");
    }

    private void congestion(long x) {
        long current = System.currentTimeMillis();
        long v = 1;
        for (long i = 1; i <= x; i++) {
            v += i;
            for (long j = 1; j <= v; j++) {
                v -= j;
                for (long k = j; k <= i; k++) {
                    v += k;
                }
            }
        }
        System.out.println(
            "Congestion[" + x + "]: time = " + (System.currentTimeMillis() - current) + ". " +
            Thread.currentThread().getName() + " [" +
            Thread.currentThread().getId() + "]. Value = " + v
        );
    }

    public static void main(String[] args) {
        // successively
        new PerformanceRating(MD_SUCCESSIVELY).process();
        // parallel
        new PerformanceRating(MD_PARALLEL).process();
    }
}
