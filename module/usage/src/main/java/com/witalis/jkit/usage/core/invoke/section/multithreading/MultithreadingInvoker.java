package com.witalis.jkit.usage.core.invoke.section.multithreading;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.multithreading.content.executor.*;
import com.witalis.jkit.usage.core.invoke.section.multithreading.content.forkjoin.*;
import com.witalis.jkit.usage.core.invoke.section.multithreading.content.group.*;
import com.witalis.jkit.usage.core.invoke.section.multithreading.content.local.*;
import com.witalis.jkit.usage.core.invoke.section.multithreading.content.lock.*;
import com.witalis.jkit.usage.core.invoke.section.multithreading.content.scenario.*;
import com.witalis.jkit.usage.core.invoke.section.multithreading.content.thread.*;
import com.witalis.jkit.usage.core.invoke.section.multithreading.content.utils.*;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.LockSupport;
import java.util.function.*;
import java.util.stream.IntStream;

import static java.lang.Thread.sleep;

/**
 * Desc: multithreading
 * User: Wellaxis
 * Date: 2019/11/18
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class MultithreadingInvoker extends Invoker {

    public MultithreadingInvoker() {
        setTitle("Multithreading chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // threads
        log.info("## Thread");
        invokeThread();
        // tab
        log.info("");
        // groups
        log.info("## Thread Group");
        invokeGroup();
        // tab
        log.info("");
        // locals
        log.info("## Thread Local");
        invokeLocal();
        // tab
        log.info("");
        // locking
        log.info("## Thread Locking");
        invokeLocking();
        // tab
        log.info("");
        // tasks
        log.info("## Thread Task");
        invokeTask();
        log.info("");
        // executors
        log.info("## Executors");
        invokeExecutor();
        // tab
        log.info("");
        // synchronizes
        log.info("## Synchronizes");
        invokeSynchronizer();
        // tab
        log.info("");
        // fork/join framework
        log.info("## Fork/Join");
        invokeForkJoin();
        // tab
        log.info("");
        // collections
        log.info("## Collections");
        invokeCollection();
        // tab
        log.info("");
        // classes
        log.info("## Scenarios");
        invokeScenario();
    }

    /**
     * Basic postulates of multithreading.
     */
    private void invokeBasis() {
        // System:
        // * in a single-core system, two or more threads are not actually executing at the same time,
        //   but are waiting for their queues for CPU time usage.
        // * on multicore systems, two or more threads can actually run simultaneously.

        // Multitasking: process-based (programs) and thread-based (threads)
        // Multithreading is a specialized form of multitasking - more detailed

        log.info("Multithreading is a special form of multitasking to do work at the same time.");

        // Process: a collection of code and data that shares a common virtual address space.
        // The process is allocated memory and other resources.
        // The process uses at least 1 thread. Each process has its own ID: PID.
    }

    /**
     * Thread definitions.
     */
    private void invokeThread() {
        // The Java Virtual Machine allows an application to have multiple threads of execution running concurrently.

        log.info("Definition: a thread is a thread of execution in a program.");

        // User Thread (Thread): the thread has a stack - some of its memory for execution (error -> stacktrace).
        // The rest of the process memory is shared by all its threads.
        // The Java thread is mapped to native the CPU thread in 1:1 ratio.

        log.info("Thread instances are not threads, they are an API for threads managed by the JVM.");

        // current
        Thread thread = Thread.currentThread();
        {
            log.info("");
            log.info("-- Current Thread");

            thread.setName("The JKIT Thread");
            String thName = thread.getName();

            log.info("Name: '{}', Info: {}", thName, Thread.currentThread());

            // details
            long id = thread.getId();
            int priority = thread.getPriority();
            boolean alive = thread.isAlive();
            boolean daemon = thread.isDaemon();
            boolean interrupted = thread.isInterrupted();

            log.info("ID: {}, Priority: {}", id, priority);
            log.info("Flags: alive -> {}, daemon -> {}, interrupted -> {}", alive, daemon, interrupted);

            // state
            Thread.State state = thread.getState();
            if (state.equals(Thread.State.RUNNABLE)) {
                log.info("State of Thread: " + state);
            }

            // sleep
            try {
                sleep(100);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }

        // states
        {
            log.info("");
            log.info("-- Thread States");

            // State: enum of thread states:
            // * New - created thread, but not yet running
            // * Runnable - running
            // * Blocked - blocked pending monitor
            // * Waiting - waiting for another thread indefinitely [3 ways: wait(), join(), LockSupport - park()]
            // * Timed_waiting - the thread is waiting for the specified time
            // * Terminated - terminated

            Arrays.stream(Thread.State.values()).forEach(
                state -> log.info(state.toString())
            );
        }

        // instance
        {
            log.info("");
            log.info("-- Thread Initialization");

            // thread initialization:
            // * extends Thread - bad, violation of SOLID (responsibility for the thread and task execution)
            // * implement Runnable - good, while this is a functional interface, you can use lambdas

            InitializationExtendingThread initOnThread = new InitializationExtendingThread();
            initOnThread.start();

            InitializationImplementingRunnable initOnRunnable = new InitializationImplementingRunnable();
            Thread invoker = new Thread(initOnRunnable);
            invoker.start();

            try {
                initOnThread.join();
                invoker.join();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }

        // daemon
        {
            log.info("");
            log.info("-- Daemon Thread");

            // Daemon: is a background (service) thread that performs work in the background (priority is LOW = 1).
            // The daemon will only run until as long as the rest of the program is running (normal threads are alive).

            Runnable logging = () -> log.info("Thread [daemon]: logging...");
            Thread logger = new Thread(logging);
            logger.setDaemon(true);
            logger.start();

            ThreadUtils.sleep(100);
        }

        // thread tasks
        {
            log.info("");
            log.info("-- Thread Tasks");

            RunnableThread tRunnable = new RunnableThread();
            ExtendedThread tExtended = new ExtendedThread();
            CallableThread tCallable = new CallableThread();

            // increase priority
            tExtended.setPriority(Thread.MAX_PRIORITY);

            // decrease priority
            tRunnable.t.setPriority(Thread.MIN_PRIORITY);

            // return value
            try {
                var call = tCallable.call();
                log.info("- Callable value: {}", call);
            } catch (Exception e) {
                log.error("Callable errors");
            }

            // pre-processing
            log.info("* Alive:");
            log.info("Is Alive thread extended: " + tExtended.isAlive());
            log.info("Is Alive thread runnable: " + tRunnable.t.isAlive());
            log.info("Is Alive thread main: " + thread.isAlive());

            // sleeping
            log.info("* Sleeping:");
            for (int i = 3; i > 0; i--) {
                log.info("    Main Sleep[" + i + "]");
                ThreadUtils.sleep(100);
            }
            log.info("    Main Done");

            // current main thread waits all sub-threads - joins them
            try {
                log.info("* Wait till all children will be finished.");
                tRunnable.t.join();
                tExtended.join();
            } catch (InterruptedException ie) {
                log.error("Main is interrupted");
                Thread.currentThread().interrupt();
            }

            // post-processing
            log.info("* Done:");
            log.info("Is Alive thread interface: " + tRunnable.t.isAlive());
            log.info("Is Alive thread extended: " + tExtended.isAlive());
            log.info("Is Alive thread main: " + thread.isAlive());
        }

        // lambda - since JDK 8
        {
            log.info("-- Lambda Thread");

            Runnable task = () -> {
                try {
                    String threadName = Thread.currentThread().getName();
                    TimeUnit.MILLISECONDS.sleep(10);
                    log.info("* Thread -> " + threadName);
                } catch (InterruptedException ie) {
                    log.error("* Thread -> Error", ie);
                    Thread.currentThread().interrupt();
                }
            };
            // main thread
            task.run();
            // task thread
            Thread taskThread = new Thread(task);
            taskThread.start();
            try {
                taskThread.join();
            } catch (InterruptedException e) {
                log.error("Task is interrupted");
                Thread.currentThread().interrupt();
            }
            log.info("* Thread -> Done!");
        }

        // thread yield - give processor resources
        {
            log.info("");
            log.info("-- Thread Yield");

            // Yield: is recommendation to the thread scheduler to give less execution time (spec bugs).
            // In this case, the current thread "skips its turn" and the JVM moves on to process another thread.
            // Invoke: Thread.yield()

            // processing
            YieldThread t1 = new YieldThread("Yield-1", true);
            YieldThread t2 = new YieldThread("Yield-2", true);
            YieldThread t3 = new YieldThread("Yield-3", false);
            YieldThread t4 = new YieldThread("Yield-4", false);

            try {
                t1.join();
                t2.join();
                t3.join();
                t4.join();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }

        // wait/notify
        {
            log.info("");
            log.info("-- Wait/Notify");

            // Description: sometimes when threads interact, the question arises of notifying some threads
            // about the actions of others (results of one thread depend on the results of the another one).
            // Thread methods wait/notify works ONLY into synchronized scopes.

            final Object lock = new Object();
            final boolean[] status = {true};
            Runnable task = () -> {
                synchronized(lock) {
                    try {
                        // take mutex and leave mutex - wait for notify of interrupt
                        while (status[0]) {
                            lock.wait();
                        }
                        // wait to log message
                        log.info("Task: {}", Thread.currentThread().getName());
                    } catch(InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            };
            Thread taskThread = new Thread(task, "The Task Thread");
            taskThread.start();
            // sleep 2sec, take mutex (because task left it), then notify child to do task (leave mutex)
            ThreadUtils.sleep(1000);
            log.info("Main: {}", Thread.currentThread().getName());
            synchronized(lock) {
                status[0] = false;
                // leave mutex - for one thread - for task one
                lock.notifyAll();
            }
            // wait for task finish processing
            try {
                taskThread.join();
            } catch (InterruptedException ie) {
                log.error("Main is interrupted");
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Thread groups.
     */
    private void invokeGroup() {
        // Thread Group: is a data structure that manages the state of an entire set of threads in general.
        // Groups can be included in each other, forming a hierarchy.

        log.info("> Thread group process: init");

        ThreadGroup groupA = new ThreadGroup("Group A");
        GroupThread t1 = new GroupThread(groupA, "TA-1st");
        GroupThread t2 = new GroupThread(groupA, "TA-2nd");

        ThreadGroup groupB = new ThreadGroup("Group B");
        GroupThread t3 = new GroupThread(groupB, "TB-3rd");
        GroupThread t4 = new GroupThread(groupB, "TB-4th");

        // output groups
        log.info("* Output groups with own threads:");
        log.info("- Group A: {}", groupA);
        if (false) groupA.list();
        log.info("- Group B: {}", groupB);
        if (false) groupB.list();

        // start threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        Thread[] threadsA = new Thread[groupA.activeCount()];

        // suspend A
        log.info("! Suspend Group A");
        groupA.enumerate(threadsA);
        for (Thread threadA : threadsA) {
            ((GroupThread) threadA).toSuspend();
        }

        // sleep...
        log.info("- Sleeping ...");
        ThreadUtils.sleep(1000);

        // resume A
        log.info("! Resume Group A");
        for (Thread threadA : threadsA) {
            ((GroupThread) threadA).toResume();
        }

        // finish
        log.info("- Processing ...");
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (Exception e) {
            log.error("Thread group process errors: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        // done
        log.info("> Thread group process: done");
    }

    /**
     * Thread locals.
     */
    private void invokeLocal() {
        // ThreadLocal: (thread) variables - each thread has its own individual instance of a variable (even a static).
        // Methods: get(), set(), init(), remove(). Declared as private static.
        // Advantages: provides thread safety (does not share its state), does not require synchronization.
        // Disadvantages: visibility restriction. This is another "specific scope". Automatic cleaning required.

        log.info("ThreadLocal: (thread) variables - each thread has its own individual instance of a variable.");

        // initialization
        {
            log.info("");
            log.info("-- Initialization");

            // classical
            ThreadLocal<LocalContext> classical = new ThreadLocal<>();
            classical.set(new LocalContext("Classical"));

            // functional
            ThreadLocal<LocalContext> functional = ThreadLocal.withInitial(
                () -> new LocalContext("Functional")
            );

            log.info("* Local-1: " + classical.get());
            log.info("* Local-2: " + functional.get());

            classical.remove();
            functional.remove();

            log.info("* Local-1: " + classical.get());
            log.info("* Local-2: " + functional.get());
        }

        // processing
        {
            log.info("");
            log.info("-- Processing");

            int value = ThreadLocalRandom.current().nextInt(1, 99);
            log.info("* Initial value = {}", value);

            LocalHolder holder = new LocalHolder(value);
            log.info("* Holder[before]: simple = {}, local = {}", holder.getSimple(), holder.getLocal().get());

            // processing
            try {
                Thread t1 = new LocalThread("T-1st", holder);
                Thread t2 = new LocalThread("T-2nd", holder);
                Thread t3 = new LocalThread("T-3rd", holder);
                // start
                t1.start();
                t2.start();
                t3.start();
                // gather
                t1.join();
                t2.join();
                t3.join();
            } catch (InterruptedException e) {
                log.error("Task interrupted");
                Thread.currentThread().interrupt();
            }
            log.info("* Holder[after]: simple = {}, local = {}", holder.getSimple(), holder.getLocal().get());
        }
    }

    /**
     * Thread locking.
     */
    private void invokeLocking() {
        // Mutex (mutual exclusion): mutex (mutual exclusion) is a synchronization primitive that provides mutual
        // exclusion of execution of critical sections of code. The purpose of a mutex is to provide a mechanism
        // so that access to an object had only one thread at a time.
        // Mutex is associated with each object, and threads can lock "lock" or unlock "unlock".

        // Monitor: this is an additional "superstructure" (add-on) over the mutex, a mechanism for providing
        // synchronization of access of several threads to shared resources - can be considered as a small box,
        // holding only one thread of execution at a time. Java uses synchronization to represent a monitor.

        log.info("Monitor [mutex]: mechanism for synchronization of access of several threads to shared resources.");

        // Data of the monitor is stored in object metadata - in headers (Java object header, contains 4 fields)
        // * locked - boolean flag whether the monitor is busy
        // * owner - the thread that occupied the monitor
        // * blocked set - blocked threads, waiting set for their turn
        // * wait set - the set of threads for which the wait method was called

        log.info("Types of monitor processing: volatile, atomics, synchronization, locking.");
        log.info("The both volatile and atomic operations are non-blocking.");

        // blocking
        {
            log.info("");
            invokeVolatile();
            log.info("");
            invokeAtomics();
        }
        // non-blocking
        {
            log.info("");
            invokeSynchronization();
            log.info("");
            invokeLocks();
        }
    }

    /**
     * Volatile processing (CAS - non blocking).
     */
    private void invokeVolatile() {
        log.info("-- Volatile");

        // When one thread modifies the variable, all other threads see the new value immediately.
        // read/write operations are atomic (especially for long, double).

        log.info("CAS (compare and swap): implementation of a non-blocking algorithm.");

        VolatileContent volatileContent = new VolatileContent(0);

        // atomic
        {
            Runnable atomicTask = () -> {
                final int counter = 100;
                final var random = ThreadLocalRandom.current();

                var values = new ArrayList<Integer>();
                for (int i = 0; i < counter; i++) {
                    int value = random.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
                    values.addAll(List.of(value, -value));
                }
                Collections.shuffle(values);
                values.forEach(
                    volatileContent::set
                );
                volatileContent.set(0);
            };

            try {
                log.info("* Value [atomic][before] = " + volatileContent.get());

                Thread atomicT1 = new Thread(atomicTask);
                Thread atomicT2 = new Thread(atomicTask);
                Thread atomicT3 = new Thread(atomicTask);
                // start
                atomicT1.start();
                atomicT2.start();
                atomicT3.start();
                // join
                atomicT1.join();
                atomicT2.join();
                atomicT3.join();

                log.info("* Value [atomic][after] = " + volatileContent.get());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // non atomic
        {
            Runnable nonAtomicTask = () -> {
                final int counter = 100;
                final var random = ThreadLocalRandom.current();

                var positive = new ArrayList<Integer>(counter);
                var negative = new ArrayList<Integer>(counter);
                for (int i = 0; i < counter; i++) {
                    int value = random.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
                    positive.add(value);
                    negative.add(-value);
                }

                var values = new ArrayList<Integer>();
                values.addAll(positive);
                values.addAll(negative);
                Collections.shuffle(values);

                values.forEach(
                    value -> {
                        if (value > 0) {
                            volatileContent.increment();
                        } else {
                            volatileContent.decrement();
                        }
                    }
                );
            };

            try {
                log.info("* Value [non-atomic][before] = " + volatileContent.get());

                Thread nonAtomicT1 = new Thread(nonAtomicTask);
                Thread nonAtomicT2 = new Thread(nonAtomicTask);
                Thread nonAtomicT3 = new Thread(nonAtomicTask);
                // start
                nonAtomicT1.start();
                nonAtomicT2.start();
                nonAtomicT3.start();
                // join
                nonAtomicT1.join();
                nonAtomicT2.join();
                nonAtomicT3.join();

                log.info("* Value [non-atomic][after] = " + volatileContent.get());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Atomics processing (CAS - non blocking).
     */
    private void invokeAtomics() {
        log.info("-- Atomics");

        // Volatile makes only 1 atomic operation (READ or WRITE), atomics - several.
        // Atomics (Atomic): classes with support for atomic operations on primitives and references.
        // Types: AtomicInteger, AtomicBoolean, AtomicReference<Object>, etc. - use volatile variables.
        // The work of atomics functionality is based on the principle of CAS.

        log.info("Atomic instructions work much faster than synchronizing with locks.");

        // Atomic operations do not use neither locks, nor - synchronized expressions.
        // Methods (get, set, compare, etc) works in one uninterruptible (that is, atomic) operation.

        // integer atomic
        {
            log.info("- AtomicInteger");

            // increment and get
            {
                ExecutorService executor = Executors.newFixedThreadPool(5);
                AtomicInteger atomicInt = new AtomicInteger(0);
                log.info("    Atomic Integer [IN/increment]: " + atomicInt.get());
                IntStream.range(0, 1000)
                    .forEach(
                        i -> executor.submit(atomicInt::incrementAndGet)
                    );
                ThreadUtils.finalizeExecutor(executor, false);
                log.info("    Atomic Integer [OUT/increment]: " + atomicInt.get());
            }
            // update and get
            {
                ExecutorService executor = Executors.newFixedThreadPool(5);
                AtomicInteger atomicInt = new AtomicInteger(0);
                log.info("    Atomic Integer [IN/update]: " + atomicInt.get());
                IntStream.range(0, 1000)
                    .forEach(
                        i -> {
                            Runnable task = () -> atomicInt.updateAndGet(n -> n + 2);
                            executor.submit(task);
                        }
                    );
                ThreadUtils.finalizeExecutor(executor, false);
                log.info("    Atomic Integer [OUT/update]: " + atomicInt.get());
            }
            // accumulate and get
            {
                IntBinaryOperator lambda;
                ExecutorService executor = Executors.newFixedThreadPool(5);
                AtomicInteger atomicInt = new AtomicInteger(0);
                log.info("    Atomic Integer [IN/accumulate]: " + atomicInt.get());
                IntStream.range(0, 1000)
                    .forEach(
                        i -> {
                            Runnable task = () -> atomicInt.accumulateAndGet(i, Integer::sum);
                            executor.submit(task);
                        }
                    );
                ThreadUtils.finalizeExecutor(executor, false);
                log.info("    Atomic Integer [OUT/accumulate]: " + atomicInt.get());
            }
        }

        // boolean atomic
        {
            log.info("- AtomicBoolean");

            AtomicBoolean atomicBool = new AtomicBoolean(false);
            ExecutorService executor = Executors.newFixedThreadPool(2);
            log.info("    Atomic Boolean [IN/set]: " + atomicBool.get());

            // consumer
            executor.submit(
                () -> {
                    Thread.currentThread().setName("T1");
                    while (true) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            log.error("    T1 errors: " + e.getClass().getName());
                            Thread.currentThread().interrupt();
                        }
                        log.info("  Thread [T1] is waiting for T2 to set atomic variable to true.");
                        log.info("    Thread [T1] current value => " + atomicBool.get());
                        if (atomicBool.compareAndSet(true, false)) {
                            log.info("  Thread [T1] - is ready to release...");
                            break;
                        }
                    }
                }
            );

            // producer
            executor.submit(
                () -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        log.error("    T2 errors: " + e.getClass().getName());
                        Thread.currentThread().interrupt();
                    }
                    Thread.currentThread().setName("T2");
                    log.info("  Thread [T2] is ready to activate T1...");
                    log.info("    Thread [T2] Previous value => " + atomicBool.get());
                    atomicBool.set(true);
                    log.info("    Thread [T2] Current value => " + atomicBool.get());
                }
            );
            ThreadUtils.finalizeExecutor(executor, false);
        }

        // long adder atomic
        {
            log.info("- LongAdder");

            // faster - adding numbers is much more often than getting result
            LongAdder longAdder = new LongAdder();
            ExecutorService executor = Executors.newFixedThreadPool(2);
            log.info("    Atomic Adder [IN/sum]: " + longAdder.longValue());
            IntStream.range(0, 1000)
                .forEach(
                    i -> executor.submit(longAdder::increment)
                );
            ThreadUtils.finalizeExecutor(executor, false);
            log.info("    Atomic Adder [OUT/sum]: " + longAdder.sumThenReset());
        }

        // long accumulator atomic
        {
            log.info("- LongAccumulator");

            // as long adder, but uses lambda expression
            LongBinaryOperator longOperator = (x, y) -> x + 2 * y;
            LongAccumulator longAccumulator = new LongAccumulator(longOperator, 1L);
            ExecutorService executor = Executors.newFixedThreadPool(2);
            log.info("    Atomic Accumulator [IN/accumulate]: " + longAccumulator.get());
            IntStream.range(0, 10)
                .forEach(
                    i -> executor.submit(
                        () -> {
                            longAccumulator.accumulate(i);
                            log.info("      Current value [" + i + "]: " + longAccumulator.get());
                        }
                    )
                );
            ThreadUtils.finalizeExecutor(executor, false);
            log.info("    Atomic Accumulator [OUT/accumulate]: " + longAccumulator.getThenReset());
        }

        // updater atomic
        {
            log.info("- AtomicReferenceFieldUpdater");

            // to process any method as atomic - without any synchronized expression
            log.info("* AtomicReferenceFieldUpdater");

            AtomicContent content = new AtomicContent("Java In Action.");

            AtomicUpdater updater = new AtomicUpdater(content);
            log.info("    Updater [IN]: " + updater.getContent());

            updater.setContent(new AtomicContent("Kotlin In Action."));
            log.info("    Updater [OUT]: " + updater.getContent());
        }
    }

    /**
     * Synchronization processing (Mutex - blocking).
     */
    private void invokeSynchronization() {
        log.info("-- Synchronization");

        // Synchronization: a process where a resource will only be used by one thread at a time,
        // i.e. no two threads can execute a synchronized method at the same time or in parallel.

        final int ITERATOR = 10_000;

        // not synchronized
        {
            log.info("- Not synchronized mode.");

            ExecutorService executor = Executors.newFixedThreadPool(4);
            SynchronizedContent noSyn = new SynchronizedContent();
            log.info("  Mutex [NO] [IN]: {}", noSyn.count);
            IntStream.range(0, ITERATOR)
                .forEach(i -> executor.submit(noSyn::increment));
            ThreadUtils.finalizeExecutor(executor, false);
            log.info("  Mutex [NO] [OUT]: {}", noSyn.count);
            if (noSyn.count != ITERATOR) {
                log.info("* Synchronization is absent!");
            }
        }

        // method synchronized
        {
            log.info("- Synchronized method mode.");

            ExecutorService executor = Executors.newFixedThreadPool(4);
            SynchronizedContent methSyn = new SynchronizedContent();
            log.info("  Mutex [METHOD] [IN]: {}", methSyn.count);
            IntStream.range(0, ITERATOR)
                .forEach(i -> executor.submit(methSyn::methodIncrement));
            ThreadUtils.finalizeExecutor(executor, false);
            log.info("  Mutex [METHOD] [OUT]: {}", methSyn.count);
            if (methSyn.count == ITERATOR) {
                log.info("* Synchronization is in use!");
            }
        }

        // block synchronized
        {
            log.info("- Synchronized block mode.");

            ExecutorService executor = Executors.newFixedThreadPool(4);
            SynchronizedContent blockSyn = new SynchronizedContent();
            log.info("  Mutex [BLOCK] [IN]: {}", blockSyn.count);
            IntStream.range(0, ITERATOR)
                .forEach(i -> executor.submit(blockSyn::blockIncrement));
            ThreadUtils.finalizeExecutor(executor, false);
            log.info("  Mutex [BLOCK] [OUT]: {}", blockSyn.count);
            if (blockSyn.count == ITERATOR) {
                log.info("* Synchronization is in use!");
            }
        }
    }

    /**
     * Locks processing (Mutex - blocking).
     */
    private void invokeLocks() {
        log.info("-- Lock");

        // Locking: a mechanism for controlling access to a shared resource.
        // First, the thread tries to access the shared resource. If it is free, then a lock (mutex) is placed on it.
        // When the work is completed, the lock on the share is released. If the resource is not free
        // and the lock is already on it, then the thread waits until this lock is released.
        // The lock is released even after completion due to errors or exceptions.

        // Reentrantability: if the same thread tries to acquire the monitor again (owner = same thread),
        // then the thread is not blocked (without dead-lock).

        final int ITERATOR = 10_000;

        // lock synchronization
        {
            log.info("- Reentrant lock mode.");
            ExecutorService executor = Executors.newFixedThreadPool(4);
            LockContent lockSyn = new LockContent();
            log.info("  Mutex [LOCK] [IN]: {}", lockSyn.count);
            log.info("    Locked: {}", lockSyn.lock.isLocked());
            IntStream.range(0, ITERATOR)
                .forEach(i -> {
                        executor.submit(lockSyn::lockIncrement);
                    }
                );
            ThreadUtils.finalizeExecutor(executor, false);
            log.info("  Mutex [LOCK] [OUT]: {}", lockSyn.count);
            boolean locked = lockSyn.lock.tryLock();
            log.info("    Lock acquired: {}", locked);
            if (lockSyn.count == ITERATOR) {
                log.info("* Synchronization is in use!");
            }
        }

        // read-write lock synchronization
        {
            log.info("- Read/Write lock mode.");
            ExecutorService executor = Executors.newFixedThreadPool(4);
            LockContent rwSyn = new LockContent();
            log.info("  Mutex [R/W] [IN]: {}", rwSyn.count);
            log.info("    Locked: {}", rwSyn.lock.isLocked());
            executor.submit(rwSyn::readWrite);
            ThreadUtils.finalizeExecutor(executor, false);
            log.info("  Mutex [STAMP] [OUT]: {}", rwSyn.count);
            boolean locked = rwSyn.lock.tryLock();
            log.info("    Lock acquired: {}", locked);
            log.info("* Synchronization is in use!");
        }

        // stamped lock synchronization
        {
            log.info("- Stamped lock mode.");
            ExecutorService executor = Executors.newFixedThreadPool(4);
            LockContent stampSyn = new LockContent();
            log.info("  Mutex [STAMP] [IN]: {}", stampSyn.count);
            log.info("    Locked: " + stampSyn.lock.isLocked());
            executor.submit(stampSyn::stampedRead);
            executor.submit(stampSyn::stampedWrite);
            ThreadUtils.finalizeExecutor(executor, false);
            log.info("  Mutex [STAMP] [OUT]: {}", stampSyn.count);
            boolean locked = stampSyn.lock.tryLock();
            log.info("    Lock acquired: {}", locked);
            log.info("* Synchronization is in use!");
        }

        // lock support
        {
            log.info("- Lock Support.");
            Runnable task = () -> {
                // begin state
                log.info("* Thread state [begin]: " + Thread.currentThread().getState());
                // park lock - enable
                log.info("LS [park]: Task will be parked");
                LockSupport.park();
                // task work - after call unpark
                log.info("LS [task]: Task after unpark");
                // end state
                log.info("* Thread state [end]: " + Thread.currentThread().getState());
            };
            Thread thread = new Thread(task, "LockSupport");
            log.info("* Thread state [before]: " + thread.getState());
            thread.start();
            ThreadUtils.sleep(1000);
            log.info("* Thread state [middle]: " + thread.getState());
            // unpark task processing
            LockSupport.unpark(thread);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            log.info("* Thread state [after]: " + thread.getState());
        }
    }

    /**
     * Thread task processing.
     */
    private void invokeTask() {
        // Runnable: the task is executed in a separate thread when the method is called - void run(). Returns no value.
        // Callable<V>: a functional interface, but can return a value - V call() throws Exception.

        // Future<V>: used to get a result in the future. Implementation: FutureTask<V>.
        // Interface: 5 methods in total: cancel(), isCancelled(), isDone(), get(), get(timeout).
        // Processing: in blocking mode - V get(). No method as complete() & exception handling mechanism.

        // CompletableFuture<T>: extends Future & CompletionStage. Lots of methods + chains + callbacks. Since JDK8.
        // Interface: 60+ methods (most of CompletionStage are non-blocking methods)
        // Processing: blocking methods get(), getNow() & non-blocking - thenApply, thenAccept, thenRun, combine, anyOf.

        // runnable future
        {
            log.info("-- Runnable Future");

            final String information = "Task: runnable future";

            Runnable task = "Task: without value"::toUpperCase;
            RunnableFuture<String> future = new FutureTask<>(task, information);
            // computation
            new Thread(future).start();
            try {
                log.info("  Future [value]: {}", future.get());
            } catch (InterruptedException | ExecutionException e) {
                Thread.currentThread().interrupt();
            }
        }

        // callable future
        {
            log.info("-- Callable Future");

            final String information = "Task: callable future";

            Callable<String> task = () -> information;
            FutureTask<String> future = new FutureTask<>(task);
            // computation
            new Thread(future).start();
            try {
                log.info("  Future [value]: {}", future.get());
            } catch (InterruptedException | ExecutionException e) {
                Thread.currentThread().interrupt();
            }
        }

        // completable future
        {
            log.info("-- Completable Future");

            final String information = "Task: completable future";

            AtomicLong atomicLong = new AtomicLong(0);
            Runnable task = () -> atomicLong.set(new Date().getTime());
            Function<Long, Date> dateConverter = (longValue) -> new Date(longValue);
            Consumer<Date> printer = date -> log.info("  CompletableFuture: {}", date);
            // computation
            CompletableFuture.runAsync(task)
                .thenApply((Void v) -> information)
                .exceptionally(Throwable::getMessage)
                .thenApply((v) -> atomicLong.get())
                .thenApply(dateConverter)
                .thenAccept(printer);
        }
    }

    /**
     * Service executor processing.
     */
    private void invokeExecutor() {
        // Executors: designed to create and manage threads of execution (replacing work with streams directly).
        // Tasks are executed asynchronously. The work must be terminated explicitly: shutdown(), shutdownNow().

        // Executor Chain: Executor -> ExecutorService -> ThreadPoolExecutor -> Future/Callable/Runnable.
        // Executor: interface for running tasks (functional) — void execute(Runnable command).
        // ExecutorService: inherited interface (methods to stop work + Future to track work).
        // AbstractExecutorService: abstract class (newTaskFor methods).
        // ThreadPoolExecutor: functionality depends on passed arguments.
        // Executors: a factory for creating different default implementations of Executor Services.

        // Executors.newSingleThreadExecutor() -> single thread is used only.
        // Executors.newFixedThreadPool(10) -> fixed thread pool, N = (CPU) Runtime.getRuntime().availableProcessors().
        // Executors.newCachedThreadPool() -> cached thread pool, threads are deleted in idle (60s) short-lived tasks.
        // Executors.newWorkStealingPool(10) -> creates a fork/join pool of threads (idle threads pick up other tasks).
        // Executors.newScheduledThreadPool(1) -> start tasks according to schedule (schedule, fixed rate/delay).

        log.info("Executors: designed to create and manage threads of execution (replacing work with streams directly)");

        // single thread executor
        {
            log.info("");
            log.info("-- Single Executor");

            // task
            Runnable runnable = () -> {
                String threadName = Thread.currentThread().getName();
                log.info("    $ Task [single] -> {}", threadName);
            };

            // executors are working indefinitely
            ExecutorService singleExecutor = Executors.newSingleThreadExecutor();
            log.info("  Executor [info]: {}", singleExecutor.getClass().getCanonicalName());
            singleExecutor.submit(runnable);
            ThreadUtils.finalizeExecutor(singleExecutor, false);
        }

        // fixed thread pool
        {
            log.info("");
            log.info("-- Fixed Executor");

            // task
            Callable<Long> callable = () -> {
                String threadName = Thread.currentThread().getName();
                log.info("    $ Task [fixed] -> {}", threadName);
                TimeUnit.SECONDS.sleep(1);
                return Thread.currentThread().getId();
            };

            // future to get callable result
            final int cpu = Runtime.getRuntime().availableProcessors();
            ExecutorService fixedExecutor = Executors.newFixedThreadPool(cpu);
            log.info("  Executor [info]: {}", fixedExecutor.getClass().getCanonicalName());
            Future<Long> future = fixedExecutor.submit(callable);
            try {
                log.info("    @ Future [done]: {}", future.isDone());
                // set timeout to avoid indefinite retrieving of result -> error
                Long result = future.get(1, TimeUnit.SECONDS);
                log.info("    @ Future [done]: {}", future.isDone());
                log.info("    > Service result: {}", result);
            } catch (ExecutionException | InterruptedException | TimeoutException e) {
                Thread.currentThread().interrupt();
            }
            ThreadUtils.finalizeExecutor(fixedExecutor, false);
        }

        // cached thread pool
        {
            log.info("");
            log.info("-- Cached Executor");

            // task
            Callable<String> task1 = () -> {
                String threadName = Thread.currentThread().getName();
                log.info("    $ Task [cached] -> {}", threadName);
                TimeUnit.SECONDS.sleep(2);
                return "T1: " + Thread.currentThread();
            };
            Callable<String> task2 = () -> {
                String threadName = Thread.currentThread().getName();
                log.info("    $ Task [cached] -> {}", threadName);
                TimeUnit.SECONDS.sleep(1);
                return "T2: " + Thread.currentThread();
            };
            Callable<String> task3 = () -> {
                String threadName = Thread.currentThread().getName();
                log.info("    $ Task [cached] -> {}", threadName);
                TimeUnit.SECONDS.sleep(3);
                return "T3: " + Thread.currentThread();
            };
            List<Callable<String>> tasks = Arrays.asList(task1, task2, task3);

            // cached executor is suitable for applications that launch many short-lived tasks
            ExecutorService cachedExecutor = Executors.newCachedThreadPool();
            log.info("  Executor [info]: {}", cachedExecutor.getClass().getCanonicalName());
            try {
                String result = cachedExecutor.invokeAny(tasks);
                log.info("    > Service result: {}", result);
            } catch (InterruptedException | ExecutionException e) {
                Thread.currentThread().interrupt();
            }
            ThreadUtils.finalizeExecutor(cachedExecutor, false);
        }

        // scheduled thread pool
        {
            log.info("");
            log.info("-- Scheduled Executor");

            // delay execution onto 3 seconds
            {
                ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);
                log.info("  Executor [info]: {}", scheduledExecutor.getClass().getCanonicalName());
                Runnable task = () -> log.info("    $ Scheduling Invoke: {}", new Date());
                ScheduledFuture<?> scheduled = scheduledExecutor.schedule(task, 2, TimeUnit.SECONDS);
                ThreadUtils.sleep(500);
                long remainingDelay = scheduled.getDelay(TimeUnit.MILLISECONDS);
                log.info(String.format("    > Remaining Delay: %sms", remainingDelay));
                ThreadUtils.finalizeExecutor(scheduledExecutor, false);
            }

            log.info("");

            // fixed rate - invoke in exact
            {
                ScheduledExecutorService atFixedRateExecutor = Executors.newScheduledThreadPool(1);
                log.info("  Executor [info]: {}", atFixedRateExecutor.getClass().getCanonicalName());
                atFixedRateExecutor.scheduleAtFixedRate(
                    () -> {
                        try {
                            log.info("    @ Thread: {}", Thread.currentThread());
                            TimeUnit.MILLISECONDS.sleep(100);
                            log.info("    $ Fixed Rate: {}", new Date());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    },
                    0, 1L, TimeUnit.SECONDS
                );
                ThreadUtils.sleep(3_000);
                ThreadUtils.finalizeExecutor(atFixedRateExecutor, false);
            }

            log.info("");

            // fixed delay - invoke since finish
            {
                ScheduledExecutorService withFixedDelayExecutor = Executors.newScheduledThreadPool(1);
                log.info("  Executor [info]: {}", withFixedDelayExecutor.getClass().getCanonicalName());
                withFixedDelayExecutor.scheduleWithFixedDelay(
                    () -> {
                        try {
                            log.info("    @ Thread: {}", Thread.currentThread());
                            TimeUnit.MILLISECONDS.sleep(100);
                            log.info("    $ Fixed Delay: {}", new Date());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    },
                    0, 1L, TimeUnit.SECONDS
                );
                ThreadUtils.sleep(3_000);
                ThreadUtils.finalizeExecutor(withFixedDelayExecutor, false);
            }
        }

        // work stealing pool
        {
            log.info("");
            log.info("-- Work Stealing Pool");

            // since JDK 8
            ExecutorService workExecutor = Executors.newWorkStealingPool(4);
            log.info("  Executor [info]: {}", workExecutor.getClass().getCanonicalName());
            List<Callable<String>> callables = Arrays.asList(
                () -> "    T1: " + Thread.currentThread(),
                () -> "    T2: " + Thread.currentThread(),
                () -> "    T3: " + Thread.currentThread(),
                () -> "    T4: " + Thread.currentThread(),
                () -> "    T5: " + Thread.currentThread(),
                () -> "    T6: " + Thread.currentThread()
            );
            try {
                workExecutor.invokeAll(callables)
                    .stream()
                    .map(
                        future -> {
                            try {
                                return future.get();
                            } catch (Exception e) {
                                throw new IllegalStateException(e);
                            }
                        }
                    )
                    .forEach(log::info);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            ThreadUtils.finalizeExecutor(workExecutor, false);
        }

        // named pool - guava
        {
            log.info("");
            log.info("-- Named Executor");

            final var random = ThreadLocalRandom.current();

            // runnable
            List<Runnable> runnableTasks = List.of(
                new RunnableClass(random.nextInt(10,30)),
                new RunnableClass(random.nextInt(30,50)),
                new RunnableClass(random.nextInt(50,70)),
                new RunnableClass(random.nextInt(70,90)),
                new RunnableClass(random.nextInt(90,100))
            );
            // callable
            Set<Callable<String>> callableTasks = Set.of(
                new CallableClass(random.nextInt(10,30)),
                new CallableClass(random.nextInt(30,50)),
                new CallableClass(random.nextInt(50,70)),
                new CallableClass(random.nextInt(70,90)),
                new CallableClass(random.nextInt(90,100))
            );

            // thread factory
            final ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("Guava-%d")
                .setDaemon(false)
                .setPriority(Thread.NORM_PRIORITY)
                .build();

            // future to get callable result
            final ExecutorService namedExecutor = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() + 1,
                threadFactory
            );
            log.info("  Executor [info]: {}", namedExecutor.getClass().getCanonicalName());

            // processing
            try {
                // invoke single runnable
                namedExecutor.submit(() -> log.info("  Pool [runnable]: --"));
                // invoke single callable
                final CompletableFuture<String> async = CompletableFuture.supplyAsync(
                    () -> {
                        log.info("  Pool [callable]: --");
                        return "Completable";
                    },
                    namedExecutor
                );
                log.info("    @ Async [before get] done: {}", async.isDone());
                log.info("    > Service result: {}", async.get());
                log.info("    @ Async [after get] done: {}", async.isDone());
                // invoke runnable
                runnableTasks.forEach(namedExecutor::submit);
                // invoke callable
                List<Future<String>> futures = namedExecutor.invokeAll(callableTasks);
                // sleep
                TimeUnit.SECONDS.sleep(1);
                // result callable
                List<String> results = futures.stream()
                    .map(
                        future -> {
                            try {
                                return future.get();
                            } catch (Exception e) {
                                log.error("Future errors");
                                Thread.currentThread().interrupt();
                            }
                            return "Erroneous";
                        }
                    )
                    .toList();
                log.info("    > Callable results: {}", results);
            } catch (InterruptedException | ExecutionException e) {
                Thread.currentThread().interrupt();
            } finally {
                ThreadUtils.finalizeExecutor(namedExecutor, false);
            }
        }
    }

    /**
     * Synchronizes processing.
     */
    private void invokeSynchronizer() {
        // Synchronizer: part of Concurrency API, used to actively manage (synchronize) threads:
        // semaphore, count down latch, cyclic barrier, exchanger, phaser, etc.

        log.info("Synchronizer: part of Concurrency API, used to manage (synchronize) threads.");

        log.info("");
        invokeSemaphore();
        log.info("");
        invokeCountDownLatch();
        log.info("");
        invokeCyclicBarrier();
        log.info("");
        invokeExchanger();
        log.info("");
        invokePhaser();
    }

    /**
     * Semaphore synchronizer.
     */
    private void invokeSemaphore() {
        // Semaphore: a way to limit the number of threads that simultaneously work on the same resource
        // It manages access to a shared resource using a counter (acquire - get access, release - release).
        // Every acquire: counter--, if the counter is 0, then the current thread is blocked until another one releases.

        log.info("-- Semaphore");

        log.info("Semaphore: manages access to a shared resource using a counter.");

        // to limit the number of threads that are simultaneously working on the same resource.
        {
            log.info("");

            final int THREADS_QTY = Runtime.getRuntime().availableProcessors();
            final int PERMITS_QTY = 5;

            // synchronizer
            Semaphore semaphore = new Semaphore(PERMITS_QTY, true);
            log.info("  Synchronizer [info]: {}", semaphore.getClass().getCanonicalName());
            // executor
            ExecutorService executor = Executors.newFixedThreadPool(THREADS_QTY);
            // ids
            List<Integer> identifiers = new ArrayList<>(9);
            for (int i = 1; i <= 9; i++) identifiers.add(i);
            // task
            Runnable runningTask = () -> {
                boolean permit = false;
                try {
                    permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
                    if (permit) {
                        log.info("    Semaphore [acquired] ==> {}", Thread.currentThread());
                        // work processing (sleep > acquire timeout to ignore processing)
                        sleep(2_000);
                    } else {
                        log.info("    Semaphore [acquired] --> {}", "unable...");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    if (permit) {
                        semaphore.release();
                        log.info("    Semaphore [released] <== {}", Thread.currentThread());
                    } else {
                        log.info("    Semaphore [released] <-- {}", "unable...");
                    }
                }
            };
            log.info("    Semaphore [permits] => {}", semaphore.availablePermits());
            // invoke
            IntStream.rangeClosed(1, 9).forEach(
                i -> {
                    final int id = identifiers.get(i - 1);
                    ThreadUtils.sleep(100);
                    log.info("  Task [" + id + "]: available permits = {}", semaphore.availablePermits());
                    executor.submit(runningTask);
                }
            );
            // done
            ThreadUtils.sleep(1_000);
            log.info("  Synchronizer [done]: Semaphore");
            ThreadUtils.finalizeExecutor(executor, false);
        }
    }

    /**
     * Count Down Latch synchronizer.
     */
    private void invokeCountDownLatch() {
        // CountDownLatch: implements self-locking with countdown (number of events).
        // Allows one or multiple threads to wait until a certain number of operations have completed, running on other threads.
        // The waiting threads hang (await()) & executing threads decrement the counter (countDown()) unit.
        // When the counter is 0, all waiting threads continue their work. Counter is disposable (one-off).

        log.info("-- Count Down Latch");

        log.info("CountDownLatch: allows multiple threads to wait until a certain number of operations have completed.");

        // created with a count of events that must occur before the latch is released.
        {
            log.info("");

            final int THREADS_QTY = Runtime.getRuntime().availableProcessors();
            final int EVENTS_QTY = 5;

            // synchronizer
            CountDownLatch startSignal = new CountDownLatch(1);
            CountDownLatch countDownLatch = new CountDownLatch(EVENTS_QTY);
            log.info("  Synchronizer [info]: {}", countDownLatch.getClass().getCanonicalName());
            // executor
            ExecutorService executor = Executors.newFixedThreadPool(THREADS_QTY);
            // ids
            Queue<Integer> identifiers = new ArrayDeque<>(9);
            for (int i = 1; i <= 9; i++) identifiers.offer(i);
            // task
            Runnable runningTask = () -> {
                final int id = identifiers.poll();

                try {
                    log.info("    CountDownLatch [down] ==> {}", Thread.currentThread());
                    // wait for signal to process
                    startSignal.await();
                    // work processing
                    sleep(id * 100L);
                    // post processing
                    long countdown = countDownLatch.getCount();
                    if (countdown > 0) {
                        // decrement count
                        log.info("  Task [" + id + "]: available events = {}", countDownLatch.getCount());
                        countDownLatch.countDown();
                        if (countDownLatch.getCount() == 0) {
                            log.info("    CountDownLatch [count] => events are over...");
                        }
                    } else {
                        log.info("  Task [" + id + "]: available events are absent");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            };
            log.info("    CountDownLatch [count] => {}", countDownLatch.getCount());
            // invoke
            IntStream.rangeClosed(1, 9).forEach(i -> executor.submit(runningTask));
            // start & wait
            try {
                // let all threads proceed
                startSignal.countDown();
                // wait for all to finish
                countDownLatch.await();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            // done
            ThreadUtils.sleep(1_000);
            log.info("  Synchronizer [done] => CountDownLatch");
            ThreadUtils.finalizeExecutor(executor, false);
        }
    }

    /**
     * Cyclic Barrier synchronizer.
     */
    private void invokeCyclicBarrier() {
        // CyclicBarrier: The synchronization object is suspended until the threads reach some barrier point.
        // Used to synchronize a given number of threads at one point, calling await() method.
        // After the barrier is reached (the required number of threads are blocked), the counter is reset
        // to the initial position, and the waiting threads are released, and so on in a circle.

        log.info("-- Cyclic Barrier");

        log.info("CyclicBarrier: The synchronization object is suspended until the threads reach some barrier point.");

        // enables object until the specified number of threads has reached the barrier point.
        {
            log.info("");

            final int THREADS_QTY = Runtime.getRuntime().availableProcessors();
            final int PARTIES_QTY = 3;

            // barrier task
            AtomicInteger counter = new AtomicInteger();
            Runnable barrierTask = () -> {
                log.info("  Barrier Task [{}] => Handle...", counter.incrementAndGet());
            };
            // synchronizer
            CyclicBarrier cyclicBarrier = new CyclicBarrier(PARTIES_QTY, barrierTask);
            log.info("  Synchronizer [info]: {}", cyclicBarrier.getClass().getCanonicalName());
            // executor
            ExecutorService executor = Executors.newFixedThreadPool(THREADS_QTY);
            // ids
            Queue<Integer> identifiers = new ArrayDeque<>(9);
            for (int i = 1; i <= 9; i++) identifiers.offer(i);
            // task
            Runnable runningTask = () -> {
                final int id = identifiers.poll();

                // wait of events
                try {
                    log.info("    CyclicBarrier [await] ==> {}", Thread.currentThread());
                    // work processing
                    sleep(id * 100L);
                    // cyclic parties
                    log.info("  Task [{}] => parties = {}", id, cyclicBarrier.getParties());
                    // wait for barrier
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException ie) {
                    Thread.currentThread().interrupt();
                }
            };
            log.info("    CyclicBarrier [parties] => {}", cyclicBarrier.getParties());
            // invoke
            IntStream.rangeClosed(1, 9).forEach(i -> executor.submit(runningTask));
            // done
            ThreadUtils.sleep(2_000);
            log.info("  Synchronizer [done] => CyclicBarrier");
            ThreadUtils.finalizeExecutor(executor, false);
        }
    }

    /**
     * Exchanger synchronizer.
     */
    private void invokeExchanger() {
        // Exchanger<V>: designed to exchange data between two threads of execution via the exchange() method.
        // The first thread that calls the exchange() method will block until the same method will be called by second.
        // As soon as this happens, the threads will exchange values and continue working. Support for null values.

        log.info("-- Exchanger");

        log.info("Exchanger<V>: designed to exchange data between two threads of execution via the exchange() method.");

        // exchanger - is designed to simplify the exchange of data between two threads.
        {
            log.info("");

            final int THREADS_QTY = Runtime.getRuntime().availableProcessors();

            // synchronizer
            Exchanger<String> exchanger = new Exchanger<>();
            log.info("  Synchronizer [info]: {}", exchanger.getClass().getCanonicalName());
            // executor
            ExecutorService executor = Executors.newFixedThreadPool(THREADS_QTY);
            // producer task
            Runnable producerTask = () -> {
                log.info("  Task [producer] => {}", Thread.currentThread().getId());
                // 3 times
                var random = ThreadLocalRandom.current();
                for (int i = 0; i < 3; i++) {
                    // generate
                    var value = "Batch N" + random.nextInt(1_000, 10_000);
                    // exchange
                    try {
                        log.info("    Producer [{}] ==> {}", i, value);
                        value = exchanger.exchange(value);
                        log.info("    Producer [{}] <== {}", i, value);
                        // delimiter
                        log.info("    --------------------------------");
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            };
            // consumer task
            Runnable consumerTask = () -> {
                log.info("  Task [consumer] => {}", Thread.currentThread().getId());
                // 3 times
                for (int i = 0; i < 3; i++) {
                    var value = "";
                    try {
                        log.info("    Consumer [{}] ==> {}", i, value);
                        value = exchanger.exchange(value);
                        log.info("    Consumer [{}] <== {}", i, value);
                        // delimiter
                        ThreadUtils.sleep(100);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            };
            // invoke
            executor.submit(consumerTask);
            executor.submit(producerTask);
            // done
            ThreadUtils.sleep(2_000);
            log.info("  Synchronizer [done] => Exchanger");
            ThreadUtils.finalizeExecutor(executor, false);
        }
    }

    /**
     * Phaser synchronizer.
     */
    private void invokePhaser() {
        // Phaser: synchronizes threads of execution that represent one or more stages (or phases) of actions,
        // calling the arriveAndDeregister() method. Improved Implementation of the Synchronization Barrier.
        // Phaser combines the functionality of CyclicBarrier and CountDownLatch, incorporating the best of them.
        // The number of threads is not hardcoded and can change dynamically. Since JDK 7.

        log.info("-- Phaser");

        log.info("Phaser: synchronizes threads of execution that represent one or more stages (or phases) of actions.");

        // phaser - to enable the synchronization of threads that represent one or more phases of activity.
        {
            log.info("");

            final int THREADS_QTY = Runtime.getRuntime().availableProcessors();
            final int PARTIES_QTY = 1;

            // worker task
            class PhaseRunner implements Runnable {
                private final Phaser phaser;
                private final String name;

                public PhaseRunner(Phaser phaser, String name) {
                    this.phaser = phaser;
                    this.name = name;
                    var register = phaser.register();
                    log.info("  Task [register]: task ID = {}, phase ID = {}", name, register);
                }

                @Override
                public void run() {
                    int phase;
                    try {
                        Thread.sleep(1);
                        // 1st part
                        log.info("  Task [action]: task ID = {}, phase ID = {}", name, phaser.getPhase());
                        phase = phaser.arriveAndAwaitAdvance();
                        Thread.sleep(1);
                        // 2nd part
                        log.info("  Task [action]: task ID = {}, phase ID = {}", name, phaser.getPhase());
                        phase = phaser.arriveAndAwaitAdvance();
                        Thread.sleep(1);
                        // 3rd part
                        log.info("  Task [action]: task ID = {}, phase ID = {}", name, phaser.getPhase());
                        phase = phaser.arriveAndDeregister();
                        Thread.sleep(1);
                        // the end
                        log.info("  Task [deregister]: task ID = {}, phase ID = {}", name, phase);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }

            // synchronizer
            Phaser phaser = new Phaser(PARTIES_QTY);
            log.info("  Synchronizer [info]: {}", phaser.getClass().getCanonicalName());
            // executor
            ExecutorService executor = Executors.newFixedThreadPool(THREADS_QTY);
            // task
            var runner1 = new PhaseRunner(phaser, "#1");
            var runner2 = new PhaseRunner(phaser, "#2");
            var runner3 = new PhaseRunner(phaser, "#3");
            // invoke
            int currentPhase;
            log.info("      Phaser [invoke] => {}", "run");
            executor.execute(runner1);
            executor.execute(runner2);
            executor.execute(runner3);
            // 1st phase
            currentPhase = phaser.getPhase();
            phaser.arriveAndAwaitAdvance();
            log.info("    Phaser [done]: phase ID = {}", currentPhase);
            // 2nd phase
            currentPhase = phaser.getPhase();
            phaser.arriveAndAwaitAdvance();
            log.info("    Phaser [done]: phase ID = {}", currentPhase);
            // 3rd phase
            currentPhase = phaser.getPhase();
            phaser.arriveAndAwaitAdvance();
            log.info("    Phaser [done]: phase ID = {}", currentPhase);
            // deregister
            phaser.arriveAndDeregister();
            if (phaser.isTerminated()) {
                log.info("    Phaser [invoke] => {}", "done");
            }
            // done
            ThreadUtils.sleep(1_000);
            log.info("  Synchronizer [done] => Phaser");
            ThreadUtils.finalizeExecutor(executor, false);
        }
    }

    /**
     * Fork/Join framework.
     */
    private void invokeForkJoin() {
        // The Fork/Join Framework enhances multithreaded programming.
        // It uses for recursive tasks (which are independent & without states).
        // Strategy - work stealing, threads can switch their tasks handling.

        // RecursiveAction - does not return results
        {
            log.info("-- Recursive Action");

            // pool - custom
            final var parallelism = 5;
            ForkJoinPool pool = new ForkJoinPool(parallelism);
            log.info("  Pool [parallelism]: {}", pool.getParallelism());

            // data
            double[] nums = new double[100_000];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = i;
            }
            var range = Arrays.copyOfRange(nums, 0, 10);
            log.info("  Data [before]: {}", Arrays.toString(range));

            // task
            var time = System.nanoTime();
            SqrtHandler task = new SqrtHandler(nums, 0, nums.length);
            pool.invoke(task);
            var duration = System.nanoTime() - time;

            // result
            range = Arrays.copyOfRange(nums, 0, 10);
            log.info("  Data [after]: {}", Arrays.toString(range));

            // done
            log.info("  Task [processing]: {} ms", TimeUnit.NANOSECONDS.toMillis(duration));
        }

        // RecursiveTask<V> - does return results
        {
            log.info("");
            log.info("-- Recursive Task");

            // pool - common
            ForkJoinPool pool = ForkJoinPool.commonPool();
            log.info("  Pool [parallelism]: {}", pool.getParallelism());

            // data
            double[] nums = new double[10_000];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = (((i % 2) == 0) ? i : -i);
            }
            var range = Arrays.copyOfRange(nums, 0, 10);
            log.info("  Data [before]: {}", Arrays.toString(range));

            // task
            var time = System.nanoTime();
            SumHandler task = new SumHandler(nums, 0, nums.length);
            double sum = pool.invoke(task);
            var duration = System.nanoTime() - time;

            // result
            range = Arrays.copyOfRange(nums, 0, 10);
            log.info("  Data [after]: {}", Arrays.toString(range));
            log.info("  Data [result]: {}", sum);

            // done
            log.info("  Task [processing]: {} ms", TimeUnit.NANOSECONDS.toMillis(duration));
        }

        // CountedCompleter<V> - can return results
        {
            log.info("");
            log.info("-- Counted Completer");

            // pool
            ForkJoinPool pool = ForkJoinPool.commonPool();
            log.info("  Pool [parallelism]: {}", pool.getParallelism());

            // data
            List<BigInteger> list = new ArrayList<>();
            for (int i = 3; i < 20; i++) {
                list.add(new BigInteger(Integer.toString(i)));
            }
            log.info("  Data [before]: {}", Arrays.toString(list.toArray()));

            // task
            var time = System.nanoTime();
            FactorHandler task = new FactorHandler(
                null,
                new AtomicReference<>(BigInteger.ZERO),
                list
            );
            BigInteger factorial = pool.invoke(task);
            var duration = System.nanoTime() - time;

            // result
            log.info("  Data [after]: {}", Arrays.toString(list.toArray()));
            log.info("  Data [result]: {}", factorial);

            // done
            log.info("  Task [processing]: {} ms", TimeUnit.NANOSECONDS.toMillis(duration));
        }
    }

    /**
     * Thread-safe collections.
     */
    private void invokeCollection() {

        // concurrent map
        {
            log.info("-- Concurrent Map");

            ExecutorService executor = Executors.newFixedThreadPool(3);
            BiConsumer<String, String> output = (key, value) -> log.info(String.format("    %s = %s", key, value));

            ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
            map.put("Harry", "Potter");
            map.put("Ron", "Weasley");

            // begin
            map.forEach(output);
            log.info("  Concurrent Map [IN]: {} entries.", map.size());

            executor.submit(
                () -> {
                    Thread.currentThread().setName("Wizard1");
                    log.info("    W1: Characters -> " + map);
                    log.info("    W1: Dudley -> " + map.putIfAbsent("Dudley", "Dursley"));
                    log.info("    W1: Hermione -> " + map.getOrDefault("Hermione", "Witch"));
                    log.info("    W1: Luna -> " + map.computeIfAbsent("Luna", key -> "Lovegood"));
                }
            );
            executor.submit(
                () -> {
                    Thread.currentThread().setName("Wizard2");
                    log.info("    W2: Characters -> " + map);
                    log.info("    W2: Dudley -> " + map.putIfAbsent("Dudley", "Dursley"));
                    log.info("    W2: Hermione -> " + map.getOrDefault("Hermione", "Witch"));
                    log.info("    W2: Luna -> " + map.computeIfPresent("Luna", (key, value) -> value.toLowerCase()));
                }
            );
            executor.submit(
                () -> {
                    Thread.currentThread().setName("Wizard3");
                    log.info("    W3: Characters -> " + map);
                    log.info("    W3: Hermione -> " + map.putIfAbsent("Hermione", "Granger"));
                    log.info("    W3: Hermione -> " + map.getOrDefault("Hermione", "Witch"));
                    log.info("    W3: Luna -> " + map.merge("Luna", "Lovegood", (key, value) -> value.toUpperCase()));
                }
            );
            ThreadUtils.finalizeExecutor(executor, false);

            // end
            log.info("  Concurrent Map [OUT]: {} entries.", map.size());
            map.forEach(output);
        }

        // concurrent hash map
        {
            log.info("");
            log.info("-- Concurrent Hash Map");

            // added stream methods
            int parallelismThreshold = 1;
            log.info("  Pool parallelism: {}", ForkJoinPool.getCommonPoolParallelism());
            BiConsumer<String, String> output = (key, value) -> log.info(String.format("    %s = %s, T: %s", key, value, Thread.currentThread().getName()));

            ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
            map.put("R2", "D2");
            map.put("C", "3PO");
            map.put("L3", "37");
            map.put("K", "2SO");

            // begin
            map.forEach(output);
            log.info("  Concurrent Hash Map [IN]: {} entries.", map.size());

            // put entry
            {
                map.putIfAbsent("BB", "8");
            }

            // search entry
            {
                String result = map.search(
                    parallelismThreshold,
                    (key, value) -> {
                        log.info("    Search Entry: {}", Thread.currentThread().getName());
                        return "C".equals(key) ? value : null;
                    }
                );
                log.info("  Result [search]: {}", result);
            }

            // search value
            {
                String result = map.searchValues(
                    parallelismThreshold,
                    value -> {
                        log.info("    Search Value: {}", Thread.currentThread().getName());
                        return (value.length() > 2) ? value : null;
                    }
                );
                log.info("  Result [search]: {}", result);
            }

            // reduce
            {
                String result = map.reduce(
                    parallelismThreshold,
                    (key, value) -> {
                        log.info("    Transform: {}", Thread.currentThread().getName());
                        return key + "=" + value;
                    },
                    (s1, s2) -> {
                        log.info("    Reduce: {}", Thread.currentThread().getName());
                        return s1 + ", " + s2;
                    }
                );
                log.info("  Result [reduce]: {}", result);
            }

            // end
            log.info("  Concurrent Hash Map [OUT]: {} entries.", map.size());
            map.forEach(output);
        }
    }

    /**
     * Multithreading scenarios.
     */
    private void invokeScenario() {
        // synchronization - message processing
        {
            log.info("-- Synchronization");

            SynchronizeCalling calling = new SynchronizeCalling();
            try {
                // vars
                SynchronizeCaller caller1 = new SynchronizeCaller(calling, "This", Thread.MAX_PRIORITY);
                SynchronizeCaller caller2 = new SynchronizeCaller(calling, "is", Thread.NORM_PRIORITY + 2);
                SynchronizeCaller caller3 = new SynchronizeCaller(calling, "the", Thread.NORM_PRIORITY);
                SynchronizeCaller caller4 = new SynchronizeCaller(calling, "default", Thread.NORM_PRIORITY - 2);
                SynchronizeCaller caller5 = new SynchronizeCaller(calling, "synchronization", Thread.MIN_PRIORITY);
                // race
                caller1.getThread().join();
                caller2.getThread().join();
                caller3.getThread().join();
                caller4.getThread().join();
                caller5.getThread().join();
            } catch (InterruptedException ie) {
                log.error("Main is interrupted");
                Thread.currentThread().interrupt();
            }
        }

        // deadlock - two threads have a circular dependency on a pair of synchronized objects
        {
            log.info("");
            log.info("-- Deadlock");

            final boolean ignore = true;

            if (!ignore) {
                DeadLock deadLock = new DeadLock();
                deadLock.deadlock();
            }
        }

        // threads communication - classical producer/consumer
        {
            log.info("");
            log.info("-- Producer/Consumer simple");

            StoreThread store = new StoreThread();
            store.process();
            ThreadUtils.sleep(500);
        }

        // inter-thread communication - wait, notify, notifyAll
        {
            log.info("");
            log.info("-- Producer/Consumer common");

            var batchSize = 5;
            TopSupplier<TopProduct> supplier = new TopSupplier<>(batchSize);
            var stockSize = 3;
            TopStock<TopProduct> stock = new TopStock<>(supplier, stockSize);
            TopProducer<TopProduct> producer1 = new TopProducer<>("P1", stock);
            TopProducer<TopProduct> producer2 = new TopProducer<>("P2", stock);
            TopConsumer<TopProduct> consumer1 = new TopConsumer<>("C1", stock);

            Thread producerHandler1 = new Thread(producer1, "TopProducer #1");
            Thread producerHandler2 = new Thread(producer2, "TopProducer #2");
            Thread consumerHandler1 = new Thread(consumer1, "TopConsumer #1");

            producerHandler1.start();
            producerHandler2.start();
            consumerHandler1.start();

            try {
                producerHandler1.join();
                producerHandler2.join();

                Thread.sleep(500);
                consumer1.complete();
                consumerHandler1.interrupt();
            } catch (InterruptedException ie) {
                log.info("Producer/Consumer errors: {}", ie.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}
