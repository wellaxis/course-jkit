package com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.facade;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Computer class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Slf4j
public class Computer {
    public void getElectricShock() {
        log.info(". Check On.");
    }

    public void makeSound() {
        log.info(".. beep-beep!");
    }

    public void loadingScreen() {
        log.info("... Loading...");
    }

    public void readyToUse() {
        log.info(".... Ready to use>");
    }

    public void saveResults() {
        log.info(".... Save all results..");
    }

    public void closeEverything() {
        log.info("... Close programs.");
    }

    public void soothe() {
        log.info(". S-s-s-s-s");
    }
}
