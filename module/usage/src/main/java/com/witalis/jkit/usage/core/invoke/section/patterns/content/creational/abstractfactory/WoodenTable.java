package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.abstractfactory;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Wooden Table class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Slf4j
public class WoodenTable implements Table {

    @Override
    public void getDescription() {
        log.info("Table: is wooden");
    }
}
