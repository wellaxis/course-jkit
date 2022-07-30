package com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Laboratory Room class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Slf4j
public class LaboratoryRoom implements Room {

    @Override
    public void open() {
        log.info("Open the laboratory room.");
    }

    @Override
    public void close() {
        log.info("Close the laboratory room.");
    }
}
