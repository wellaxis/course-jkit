package com.witalis.jkit.utils.thread;

public class ThreadSupply {

    static class Queue {
        int n;

        synchronized int get() {
            System.out.println("Get: " + n);
            return n;
        }

        synchronized void put(int n) {
            this.n = n;
            System.out.println("Put: " + n);
        }
    }

    static class Producer implements Runnable {
        Queue queue;

        Producer(Queue queue) {
            this.queue = queue;
            new Thread(this, "Producer").start();
        }

        public void run() {
            int i = 0;
            while (true) {
                queue.put(i++);
            }
        }
    }

    static class Consumer implements Runnable {
        Queue queue;

        Consumer(Queue queue) {
            this.queue = queue;
            new Thread(this, "Consumer").start();
        }

        public void run() {
            while (true) {
                queue.get();
            }
        }
    }

    public static void main(String[] args) {
        Queue queue = new Queue();
        new Producer(queue);
        new Consumer(queue);
        System.out.println("Push 'Ctrl+C' to exit.");
    }
}
