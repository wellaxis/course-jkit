package com.witalis.jkit.utils.vm.agent.info;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

/**
 * Desc: VM Trans
 * User: Wellaxis
 * Date: 03.04.19
 * Time: 10:58:25
 */
public class AgentTrans implements ClassFileTransformer {
    private static int count = 0;

    @Override
    public byte[] transform(
        ClassLoader loader,
        String className,
        Class<?> classBeingRedefined,
        ProtectionDomain protectionDomain,
        byte[] classfileBuffer
    ) {
        System.out.println("\tLoad class: " + className.replaceAll("/", "."));
        System.out.println(String.format("loaded %s classes", ++count));
        return classfileBuffer;
    }
}
