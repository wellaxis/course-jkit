package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.command;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Bulb class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Slf4j
public class Bulb implements Switchable {

    @Override
    public void turnOn() {
        log.info("Bulb is turned on");
    }

    @Override
    public void turnOff() {
        log.info("Bulb is turned off");
    }
}
