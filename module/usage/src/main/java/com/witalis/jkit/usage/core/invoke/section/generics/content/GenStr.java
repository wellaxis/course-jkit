package com.witalis.jkit.usage.core.invoke.section.generics.content;

import lombok.extern.slf4j.Slf4j;

/**
 * Realization generic
 */
@Slf4j
public class GenStr extends Gen<String> {

    public GenStr (String o) {
        super(o);
    }

    public String getValue() {
        log.info("Gen[String] value invocation");
        return value;
    }
}
