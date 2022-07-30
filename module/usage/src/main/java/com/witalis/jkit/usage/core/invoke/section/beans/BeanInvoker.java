package com.witalis.jkit.usage.core.invoke.section.beans;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.beans.content.*;
import com.witalis.jkit.usage.core.invoke.section.beans.content.scenario.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Java beans
 * User: Wellaxis
 * Date: 2021/04/01
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class BeanInvoker extends Invoker {

    public BeanInvoker() {
        setTitle("Java Beans chapter.");
    }

    @Override
    public void invoke() {
        // beans
        log.info("## Beans");
        invokeBeans();
        // tab
        log.info("");
        // pojos
        log.info("## POJOs");
        invokePojos();
        // tab
        log.info("");
        // scenario
        log.info("## Scenario");
        invokeScenario();
    }

    /**
     * Java Beans.
     */
    public void invokeBeans() {
        var bean = new BeanSample();
        bean.setIdentifier(1234);
        bean.setName("Bean");
        log.info("Java Bean: {}", bean);
    }

    /**
     * Java POJOs.
     */
    public void invokePojos() {
        var pojo = new PojoSample(5678, "Pojo");
        log.info("Java POJO: {}", pojo);
    }

    /**
     * Scenario with java beans.
     */
    public void invokeScenario() {
        var introspect = new ColorsIntrospector();
        introspect.introspection();
    }
}
