package com.witalis.jkit.utils.vm.agent;

/**
 * Desc: VM Agent
 * User: Wellaxis
 * Date: 03.04.19
 * Time: 10:58:25
 */
public class Agent {
    public static void premain(String args) {
        System.out.println("Hi, user! It is the java VM agent...");
    }

    public static void main(String[] args) {
    }
}
