package com.witalis.jkit.usage.core.invoke.section.interfaces.content;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Call implementation
 * User: Wellaxis
 * Date: 4/11/2022
 */
@Slf4j
public class Caller implements Call {

    @Override
    public void call(int parameter) {
        log.info("Caller[parameter]: {}", parameter);
    }
}
