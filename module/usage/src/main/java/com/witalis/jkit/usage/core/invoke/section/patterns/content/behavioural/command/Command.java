package com.witalis.jkit.usage.core.invoke.section.patterns.content.behavioural.command;

/**
 * Desc: Command interface.
 * User: Wellaxis
 * Date: 4/28/2022
 */
public interface Command {
    void execute();
    void undo();
    void redo();
}
