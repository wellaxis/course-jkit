package com.witalis.jkit.utils.vm.agent.info;

import java.lang.instrument.Instrumentation;

/**
 * Desc: VM Stats
 * User: Wellaxis
 * Date: 03.04.19
 * Time: 10:58:25
 */
public class AgentStats {
    public static void premain(String agentArgument, Instrumentation instrumentation) {
        System.out.println("Hi, user! It is the java VM stats...");
        instrumentation.addTransformer(new AgentTrans());
    }
}
