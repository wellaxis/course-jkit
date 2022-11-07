package com.witalis.jkit.utils.thread;

public class ThreadSynchronization {

    static class CallMe1 {

        synchronized void call(String msg) {
            System.out.print("[" + msg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                System.err.println("Interrupted");
            }
            System.out.println("]");
        }
    }

    static class Caller1 implements Runnable {
        private String msg;
        private int priority;
        private CallMe1 target;
        private Thread thread;

        public Caller1(CallMe1 target, String msg, int priority) {
            this.target = target;
            this.msg = msg;
            this.priority = priority;
            this.thread = new Thread(this);
            this.thread.setPriority(priority);
            thread.start();
        }

         public void run() {
            target.call(msg);
        }
    }

    static class CallMe2 {

        void call(String msg) {
            System.out.print("[" + msg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                System.err.println("Interrupted");
            }
            System.out.println("]");
        }
    }

    static class Caller2 implements Runnable {
        private String msg;
        private int priority;
        private CallMe2 target;
        private Thread thread;

        public Caller2(CallMe2 target, String msg, int priority) {
            this.target = target;
            this.msg = msg;
            this.priority = priority;
            this.thread = new Thread(this);
            this.thread.setPriority(priority);
            thread.start();
        }

        public void run() {
            synchronized (target) {
                target.call(msg);
            }
        }
    }

    public static void main(String[] args) {
        // synchronized method
        CallMe1 target1 = new CallMe1();
        Caller1 caller11 = new Caller1(target1, "Hello,", Thread.MAX_PRIORITY);
        Caller1 caller12 = new Caller1(target1, "into synchronized", Thread.NORM_PRIORITY);
        Caller1 caller13 = new Caller1(target1, "Java world!", Thread.MIN_PRIORITY);
        try {
            caller11.thread.join();
            caller12.thread.join();
            caller13.thread.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
            System.err.println("Interrupted1 " + Thread.currentThread());
        }
        System.out.println("Done1. " + Thread.currentThread());

        // synchronized block
        CallMe2 target2 = new CallMe2();
        Caller2 caller21 = new Caller2(target2, "Hello,", Thread.MAX_PRIORITY);
        Caller2 caller22 = new Caller2(target2, "into synchronized", Thread.NORM_PRIORITY);
        Caller2 caller23 = new Caller2(target2, "Java world!", Thread.MIN_PRIORITY);
        try {
            caller21.thread.join();
            caller22.thread.join();
            caller23.thread.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
            System.err.println("Interrupted2 " + Thread.currentThread());
        }
        System.out.println("Done2. " + Thread.currentThread());
    }
}
