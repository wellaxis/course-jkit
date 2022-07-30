package com.witalis.jkit.usage.core.invoke.section.patterns.content.creational.abstractfactory;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Welder class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Slf4j
public class Welder implements TableExpert {

    @Override
    public void getDescription() {
        log.info("Expert: works with iron tables");
    }
}
