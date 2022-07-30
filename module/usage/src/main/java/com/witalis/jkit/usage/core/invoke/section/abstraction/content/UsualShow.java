package com.witalis.jkit.usage.core.invoke.section.abstraction.content;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Class Show
 * User: Wellaxis
 * Date: 21.12.2021
 */
@Slf4j
public class UsualShow extends AbstractShow {

    public UsualShow() {
        note = "DEF";
    }

    @Override
    public void show() {
        log.info("Show, me.");
    }
}
