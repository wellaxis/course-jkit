package com.witalis.jkit.utils.thread;

public class hreadImplementation {

    static class VDThread implements Runnable {
        private String name;
        private Thread thread;

        public VDThread(String name) {
            this.name = name;
            this.thread = new Thread(this, name);
            System.out.println("It's a new thread: " + thread);
            thread.start();
        }

        public void run() {
            try {
                for (int i = 5; i > 0; i--) {
                    System.out.println("Thread " + name + ": " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ie) {
                ie.printStackTrace();
                System.err.println("Thread " + name + " has been interrupted.");
            }
            System.out.println("Thread " + name + " has been finished.");
        }
    }

    public static void main(String[] args) throws Exception {
        // init
        VDThread t1 = new VDThread("1st");
        VDThread t2 = new VDThread("2nd") ;
        VDThread t3 = new VDThread("3rd") ;
        VDThread t4 = new VDThread("4th") ;
        // check
        System.out.println("Thread " + t1.name + ": alive=" + t1.thread.isAlive());
        System.out.println("Thread " + t2.name + ": alive=" + t2.thread.isAlive());
        System.out.println("Thread " + t3.name + ": alive=" + t3.thread.isAlive());
        System.out.println("Thread " + t4.name + ": alive=" + t4.thread.isAlive());
        // handle
        try {
            System.out.println("Wait until finish of all threads...");
            t1.thread.join();
            t2.thread.join();
            t3.thread.join();
            t4.thread.join();
        } catch (InterruptedException ie) {
            System.err.println("The main thread has been interrupted.");
        }
        // re-check
        System.out.println("Thread " + t1.name + ": alive=" + t1.thread.isAlive());
        System.out.println("Thread " + t2.name + ": alive=" + t2.thread.isAlive());
        System.out.println("Thread " + t3.name + ": alive=" + t3.thread.isAlive());
        System.out.println("Thread " + t4.name + ": alive=" + t4.thread.isAlive());
        // finish
        System.out.println("The main thread has been finished.");
    }
}
