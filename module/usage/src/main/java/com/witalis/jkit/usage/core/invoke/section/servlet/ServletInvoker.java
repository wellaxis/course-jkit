package com.witalis.jkit.usage.core.invoke.section.servlet;

import com.witalis.jkit.usage.core.invoke.Invoker;

import com.witalis.jkit.usage.core.invoke.section.servlet.content.LifeCycleServlet;
import com.witalis.jkit.usage.core.invoke.section.servlet.content.SimpleServlet;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: servlets
 * User: Wellaxis
 * Date: 2021/04/01
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class ServletInvoker extends Invoker {

    public ServletInvoker() {
        setTitle("Servlet chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // scenarios
        log.info("## Scenarios");
        invokeScenario();
    }

    /**
     * Basic postulates of servlets.
     */
    private void invokeBasis() {
        // Java Servlet is a program that runs on the Web or on an application server
        // (Application Server) and acts as an intermediate layer between
        // a request from a web browser or other HTTP client (Client)
        // and a database or applications on an HTTP server (HTTP Server).

        log.info("    The servlet-api is part of the Java EE specification.");

        log.info("    The latest servlets specification:");
        log.info("      - Servlet API version: Jakarta Servlet 5.0.0 M1");
        log.info("      - Released: Jun 12, 2020");
        log.info("      - Specification: 5.0");
        log.info("      - Platform: Jakarta EE 9");
        log.info("      - Important Changes: API moved from package javax.servlet to jakarta.servlet");

        log.info("    API moved from package javax.servlet to jakarta.servlet");
    }

    private void invokeScenario() {
        // simple
        {
            SimpleServlet simple = new SimpleServlet();
            log.info(simple.getServletInfo());
        }

        // lifecycle
        {
            LifeCycleServlet lifecycle = new LifeCycleServlet();
            log.info(lifecycle.getServletInfo());
        }
    }
}
