package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.abstractfactory;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Carpenter class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Slf4j
public class Carpenter implements TableExpert {

    @Override
    public void getDescription() {
        log.info("Expert: works with wooden tables");
    }
}
