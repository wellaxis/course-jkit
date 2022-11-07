package com.witalis.jkit.utils.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadStore {
    // wait vs notify - for all object [monitor on object]

    // Store with products
    class Store {
        // to store not more than 3 products
        public static final int MAX_QTY = 3;
        volatile private int product = 0;

        public synchronized void get() {
            while (product < 1) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    log.error("Store [GET] errors", e);
                }
            }
            System.out.println("Consumer bought 1 product");
            System.out.println("    Products quantity: " + --product);
            notify();
        }
        public synchronized void put() {
            while (product >= MAX_QTY) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    log.error("Store [PUT] errors", e);
                }
            }
            System.out.println("Producer created 1 product");
            System.out.println("    Products quantity: " + ++product);
            notify();
        }
    }

    // Producer - create products
    class Producer implements Runnable{
        Store store;

        Producer(Store store) {
            this.store = store;
        }

        public void run(){
            for (int i = 1; i < 6; i++) {
                store.put();
            }
        }
    }

    // Consumer - buy products
    class Consumer implements Runnable{
        Store store;

        Consumer(Store store) {
            this.store = store;
        }

        public void run(){
            for (int i = 1; i < 6; i++) {
                store.get();
            }
        }
    }

    public static void main(String[] args) {
        ThreadStore threadStore = new ThreadStore();
        threadStore.process();
    }

    private void process() {
        Store store = new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
