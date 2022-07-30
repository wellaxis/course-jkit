package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.command;

/**
 * Desc: Remote Control class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public class RemoteControl {

    public void submit(Command command) {
        command.execute();
    }
}
