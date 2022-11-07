package com.witalis.jkit.utils.vm;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.Properties;

public class VMAgent {
    public static void main(String[] args) throws IOException, InterruptedException, AttachNotSupportedException {
        VirtualMachine vm = null;
        // jvm
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        long pid = runtimeMXBean.getPid();
        System.out.println("JVM Process ID: " + pid);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println("JVM Process Name: " + name);
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        int peakThreadCount = threadMXBean.getPeakThreadCount();
        System.out.println("Peak Thread Count: " + peakThreadCount);
        try {
            vm = VirtualMachine.attach(String.valueOf(pid));
        } catch (AttachNotSupportedException x) {
            throw new IOException(x.getMessage(), x);
        }
        String javaHome = vm.getSystemProperties().getProperty("java.home");
        Properties properties = vm.getAgentProperties();
        String connectorAddress = properties.getProperty("com.sun.management.jmxremote.localConnectorAddress");
        try {
            vm.loadAgent("D:/dwp/lib/agent/agent.jar");
        } catch (AgentLoadException | AgentInitializationException e) {
            e.printStackTrace();
        }
        Thread.sleep(1000);
        vm.detach();
    }
}