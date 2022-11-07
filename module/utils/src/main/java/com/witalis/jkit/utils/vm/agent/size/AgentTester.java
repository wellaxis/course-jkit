package com.witalis.jkit.utils.vm.agent.size;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Desc: VM Tester
 * User: Wellaxis
 * Date: 03.04.19
 * Time: 10:58:25
 */
public class AgentTester {
    public static void main(String[] args) {
        printObjectSize(new Object());
        printObjectSize(new AgentMemory());
        printObjectSize(1);
        printObjectSize("string");
        printObjectSize(Calendar.getInstance());
        printObjectSize(new BigDecimal("999999999999999.999"));
        printObjectSize(new ArrayList<String>());
        printObjectSize(new Integer[100]);
    }

    public static void printObjectSize(Object obj) {
        System.out.println(
            String.format(
                "%s, size=%s",
                obj.getClass().getSimpleName(),
                AgentMemory.getSize(obj)
            )
        );
    }
}
