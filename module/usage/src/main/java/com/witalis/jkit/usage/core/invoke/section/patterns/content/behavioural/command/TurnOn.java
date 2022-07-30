package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.command;

/**
 * Desc: Turn On class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class TurnOn implements Command {
    private Switchable switchable;

    public TurnOn(Switchable switchable) {
        this.switchable = switchable;
    }

    @Override
    public void execute() {
        switchable.turnOn();
    }

    @Override
    public void undo() {
        switchable.turnOff();
    }

    @Override
    public void redo() {
        execute();
    }
}
