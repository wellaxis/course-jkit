package com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.facade;

import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Computer facade class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Slf4j
public class ComputerFacade {
    private Computer computer;

    public ComputerFacade(Computer computer) {
        this.computer = computer;
    }

    public void turnOn() {
        log.info("> TURN ON");
        computer.getElectricShock();
        computer.makeSound();
        computer.loadingScreen();
        computer.readyToUse();
        log.info("> STARTED");
    }

    public void turnOff() {
        log.info("> TURN OFF");
        computer.saveResults();
        computer.closeEverything();
        computer.makeSound();
        computer.soothe();
        log.info("> STOPPED");
    }
}
