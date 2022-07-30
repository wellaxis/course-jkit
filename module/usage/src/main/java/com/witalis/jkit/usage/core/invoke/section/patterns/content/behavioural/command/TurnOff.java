package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.command;

/**
 * Desc: Turn Off class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class TurnOff implements Command {
    private Switchable switchable;

    public TurnOff(Switchable switchable) {
        this.switchable = switchable;
    }

    @Override
    public void execute() {
        switchable.turnOff();
    }

    @Override
    public void undo() {
        switchable.turnOn();
    }

    @Override
    public void redo() {
        execute();
    }
}
