package com.witalis.jkit.usage.core.invoke.section.exception.content;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Exception cause -- since JDK 4
 * User: Wellaxis
 * Date: 4/27/2022
 */
@Slf4j
public class CauseExample {
    boolean printTrace = false;

    public void causing() {
        try {
            NullPointerException npe = new NullPointerException("Top level exception");
            npe.initCause(
                new UnsupportedOperationException(
                    "Middle level exception",
                    new RuntimeException("Bottom level exception")
                )
            );
            throw npe;
        } catch (Exception e) {
            if (printTrace) e.printStackTrace();
            log.error("Catch [" + e.getClass() + "]: " + e.getMessage());
            printErrorCause(e);
            throw e;
        }
    }

    public static void printErrorCause(Throwable t) {
        if (t != null && t.getCause() != null) {
            log.error("    Cause [" + t.getClass().getName() + " -> " + t.getCause().getClass().getName() + "]: " + t.getCause().getMessage());
            printErrorCause(t.getCause());
        }
    }
}
