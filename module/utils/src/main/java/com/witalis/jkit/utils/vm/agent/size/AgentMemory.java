package com.witalis.jkit.utils.vm.agent.size;

import java.lang.instrument.Instrumentation;

/**
 * Desc: VM Memory
 * User: Wellaxis
 * Date: 03.04.19
 * Time: 10:58:25
 */
public class AgentMemory {
    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation instrumentation) {
        AgentMemory.instrumentation = instrumentation;
    }

    public static long getSize(Object obj) {
        if (instrumentation == null) {
            throw new IllegalStateException("Agent not initialised");
        }
        return instrumentation.getObjectSize(obj);
    }
}
