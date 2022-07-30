package com.witalis.jkit.usage.core.invoke.section.system;

import com.witalis.jkit.usage.core.invoke.Invoker;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.io.Console;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.nio.channels.Channel;

/**
 * Desc: system handle
 * User: Wellaxis
 * Date: 2019/11/16
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class SystemInvoker extends Invoker {

    public SystemInvoker() {
        setTitle("System chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // system
        log.info("## System");
        invokeSystem();
        // tab
        log.info("");
        // package
        log.info("## Package");
        invokePackage();
        // tab
        log.info("");
        // factory
        log.info("## Factory");
        invokeFactory();
        // tab
        log.info("");
        // process
        log.info("## Process");
        invokeProcess();
    }

    /**
     * Basic postulates of objects.
     */
    private void invokeBasis() {
        // Facilities provided by the System class are standard input, standard output, and error output streams;
        // access to externally defined properties and environment variables; a means of loading files and libraries;
        // and a utility method for quickly copying a portion of an array.

        log.info("The System class contains several useful class fields and methods. It cannot be instantiated.");
    }

    /**
     * Operations with system info.
     */
    @SuppressWarnings("removal")
    private void invokeSystem() {
        log.info("> Current time millis: " + System.currentTimeMillis());
        log.info("> System nano time: " + System.nanoTime());
        log.info("> Environment variables: " + System.getenv().size());
        log.info("> System properties: " + System.getProperties().size());
        log.info("> System line separator: " + System.lineSeparator());
        log.info("> System file separator: " + System.getProperty("file.separator"));
        // info
        log.info("> User info: " + System.getProperty("user.name") + " in " + System.getProperty("user.home"));
        // hash
        var system = new String("System");
        log.info("> Identity hash: " + System.identityHashCode(system));
        // console
        Console console = System.console();
        if (console != null) {
            log.info("> System Console: " + console.toString());
            console.flush();
        }
        // security
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            log.info("> Security context: " + securityManager.getSecurityContext().toString());
        }
        // channel
        try {
            Channel channel = System.inheritedChannel();
            if (channel != null) {
                log.info("> Inherited channel is open: " + channel.isOpen());
            }
        } catch (IOException e) {
            log.error("System inherited channel errors: " + e.getMessage());
        }
        // finalization
        System.runFinalization();
        // garbage
        System.gc();
    }

    /**
     * Operations with package info.
     */
    private void invokePackage() {
        boolean outputInfo = false;
        Package[] packages = Package.getPackages();
        log.info("Qty of packages: " + packages.length);
        if (!outputInfo) return;
        int counter = 0;
        for (Package p: packages) {
            String info = """
                  [%d]: name = %s, title = %s, vendor = %s, version = %s""".formatted(
                    counter++,
                    p.getName(),
                    p.getImplementationTitle(),
                    p.getImplementationVendor(),
                    p.getImplementationVersion()
                );
            log.info(info);
        }
    }

    /**
     * Operations with factory info.
     */
    private void invokeFactory() {
        var information = new StringBuilder("Information: ");
        log.info("==============================");
        try {
            information = new StringBuilder();
            var runtimeVersion = Runtime.version();
            information.append("> Runtime Version:").append('\n');
            information.append("    - Version: ").append(runtimeVersion.version()).append('\n');
            information.append("    - Build: ").append(runtimeVersion.build()).append('\n');
            information.append("    - Pre-release: ").append(runtimeVersion.pre()).append('\n');
            information.append("    - Optional: ").append(runtimeVersion.optional()).append('\n');
            information.append("    * Release:").append('\n');
            information.append("        - Feature: ").append(runtimeVersion.feature()).append('\n');
            information.append("        - Interim: ").append(runtimeVersion.interim()).append('\n');
            information.append("        - Update: ").append(runtimeVersion.update()).append('\n');
            information.append("        - Patch: ").append(runtimeVersion.patch()).append('\n');
            log.info(information.toString());
        } catch (Exception e) {
            log.error("Runtime version errors: " + e.getMessage());
        } finally {
            log.info("==============================");
        }
        try {
            information = new StringBuilder();
            var runtimeSystem = ManagementFactory.getRuntimeMXBean();
            information.append("> Runtime System:").append('\n');
            information.append("    - Name: ").append(runtimeSystem.getName()).append('\n');
            information.append("    - PID: ").append(runtimeSystem.getPid()).append('\n');
            information.append("    - Version: ").append(Runtime.version()).append('\n');
            information.append("    - Up Time: ").append(runtimeSystem.getUptime()).append('\n');
            information.append("    * Specification:").append('\n');
            information.append("        - Name: ").append(runtimeSystem.getSpecName()).append('\n');
            information.append("        - Version: ").append(runtimeSystem.getSpecVersion()).append('\n');
            information.append("        - Management: ").append(runtimeSystem.getManagementSpecVersion()).append('\n');
            information.append("        - Vendor: ").append(runtimeSystem.getSpecVendor()).append('\n');
            information.append("    * Virtual Machine:").append('\n');
            information.append("        - Name: ").append(runtimeSystem.getVmName()).append('\n');
            information.append("        - Version: ").append(runtimeSystem.getVmVersion()).append('\n');
            information.append("        - Vendor: ").append(runtimeSystem.getVmVendor());
            log.info(information.toString());
        } catch (Exception e) {
            log.error("Runtime system errors: " + e.getMessage());
        } finally {
            log.info("==============================");
        }
        // operation system
        try {
            information = new StringBuilder();
            var operatingSystem = ManagementFactory.getOperatingSystemMXBean();
            information.append("> Operating System:").append('\n');
            information.append("    - Name: ").append(operatingSystem.getName()).append('\n');
            information.append("    - Version: ").append(operatingSystem.getVersion()).append('\n');
            information.append("    - Arch: ").append(operatingSystem.getArch()).append('\n');
            information.append("    - Processors: ").append(operatingSystem.getAvailableProcessors());
            log.info(information.toString());
        } catch (Exception e) {
            log.error("Operation system errors: " + e.getMessage());
        } finally {
            log.info("==============================");
        }
        // platform system
        try {
            information = new StringBuilder();
            var platformServer = ManagementFactory.getPlatformMBeanServer();
            information.append("> Platform Server:").append('\n');
            information.append("    - Beans Count: ").append(platformServer.getMBeanCount()).append('\n');
            information.append("    - Domains Count: ").append(platformServer.getDomains().length);
            if (platformServer.getDomains().length > 0) {
                var counter = 0;
                for (String domain : platformServer.getDomains()) {
                    information.append('\n');
                    information.append("    * [" + ++counter + "]: ").append('\n');
                    information.append("        - Name: ").append(domain);
                }
            }
            log.info(information.toString());
        } catch (Exception e) {
            log.error("Platform system errors: " + e.getMessage());
        } finally {
            log.info("==============================");
        }
        // memory usage
        try {
            information = new StringBuilder();
            var memoryUsage = ManagementFactory.getMemoryMXBean();
            information.append("> Memory Usage:").append('\n');
            information.append("    - Heap: ").append(memoryUsage.getHeapMemoryUsage().toString()).append('\n');
            information.append("    - Non Heap: ").append(memoryUsage.getNonHeapMemoryUsage().toString());
            log.info(information.toString());
        } catch (Exception e) {
            log.error("Memory usage errors: " + e.getMessage());
        } finally {
            log.info("==============================");
        }
        // thread usage
        try {
            information = new StringBuilder();
            var threadUsage = ManagementFactory.getThreadMXBean();
            information.append("> Thread Usage:").append('\n');
            information.append("    - Total Count: ").append(threadUsage.getThreadCount()).append('\n');
            information.append("    - Daemon Count: ").append(threadUsage.getDaemonThreadCount()).append('\n');
            information.append("    - Peak Count: ").append(threadUsage.getPeakThreadCount()).append('\n');
            information.append("    - Shared Count: ").append(threadUsage.getTotalStartedThreadCount()).append('\n');
            information.append("    - CPU Time: ").append(threadUsage.getCurrentThreadCpuTime()).append('\n');
            information.append("    - User Time: ").append(threadUsage.getCurrentThreadUserTime());
            log.info(information.toString());
        } catch (Exception e) {
            log.error("Thread usage errors: " + e.getMessage());
        } finally {
            log.info("==============================");
        }
        // garbage collector
        try {
            information = new StringBuilder();
            var garbageCollectors = ManagementFactory.getGarbageCollectorMXBeans();
            information.append("> Garbage Collector:").append('\n');
            information.append("    - Count: ").append(garbageCollectors.size());
            if (!garbageCollectors.isEmpty()) {
                var counter = 0;
                for (GarbageCollectorMXBean garbageCollector: garbageCollectors) {
                    information.append('\n');
                    information.append("    * [" + ++counter + "]: ").append('\n');
                    information.append("        - Name: ").append(garbageCollector.getName()).append('\n');
                    information.append("        - Collection Count: ").append(garbageCollector.getCollectionCount()).append('\n');
                    information.append("        - Collection Time: ").append(garbageCollector.getCollectionTime());
                }
            }
            log.info(information.toString());
        } catch (Exception e) {
            log.error("Garbage collector errors: " + e.getMessage());
        } finally {
            log.info("==============================");
        }
        // class loading
        try {
            information = new StringBuilder();
            var classLoading = ManagementFactory.getClassLoadingMXBean();
            information.append("> Class Loading:").append('\n');
            information.append("    - Total Count: ").append(classLoading.getTotalLoadedClassCount()).append('\n');
            information.append("    - Loaded Count: ").append(classLoading.getLoadedClassCount()).append('\n');
            information.append("    - Unloaded Count: ").append(classLoading.getUnloadedClassCount());
            log.info(information.toString());
        } catch (Exception e) {
            log.error("Class loading errors: " + e.getMessage());
        } finally {
            log.info("==============================");
        }
        // compilation system
        try {
            information = new StringBuilder();
            var compilationSystem = ManagementFactory.getCompilationMXBean();
            information.append("> Compilation System:").append('\n');
            information.append("    - Name: ").append(compilationSystem.getName()).append('\n');
            information.append("    - Compilation Time: ").append(compilationSystem.getTotalCompilationTime());
            log.info(information.toString());
        } catch (Exception e) {
            log.error("Compilation system errors: " + e.getMessage());
        } finally {
            log.info("==============================");
        }
    }

    /**
     * Operations with process handle.
     */
    private void invokeProcess() {
        final boolean invoke = false;

        var waitCloseManually = false;
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try {
            var name = "cmd";
            log.info("Process name: " + name);
            process = runtime.exec(name);
            log.info("Process info: " + process.info());
            Thread.sleep(1000);
            if (waitCloseManually) {
                process.waitFor();
            }
            var pid = process.pid();
            log.info("Process PID: " + pid);
            process.destroy();
        } catch (Exception e) {
            log.error("Unable to handle process correctly: " + e.getMessage());
        }
        if (process != null) {
            log.info("Process exit: " + process.exitValue());
        }
        // builder
        ProcessBuilder builder = null;
        try {
            builder = new ProcessBuilder("Notepad.exe");
            if (invoke) builder.start();
        } catch (Exception e) {
            log.error("Unable to handle process builder: " + e.getMessage());
        }
    }
}
