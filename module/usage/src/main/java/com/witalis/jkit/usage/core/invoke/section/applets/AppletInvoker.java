package com.witalis.jkit.usage.core.invoke.section.applets;

import com.witalis.jkit.usage.core.invoke.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: applets
 * User: Wellaxis
 * Date: 2019/11/18
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class AppletInvoker extends Invoker {

    public AppletInvoker() {
        setTitle("Applet chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
    }

    /**
     * Applet functionality.
     */
    @Deprecated(since = "9")
    private void invokeBasis() {
        // appletviewer - utilities to run applets
        // deprecated since JDK 9

        log.info("    It is the deprecated functionality since jdk 9.");
        log.info("    It has been removed since jdk 11.");
    }
}
