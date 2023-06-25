package com.witalis.jkit.shell.error.handler;

import com.witalis.jkit.shell.error.exception.CommandException;
import org.springframework.shell.command.CommandHandlingResult;
import org.springframework.shell.command.annotation.ExceptionResolver;
import org.springframework.shell.standard.ShellComponent;

@ShellComponent
public class CommandExceptionHandler {

    @ExceptionResolver({Exception.class, RuntimeException.class})
    public CommandHandlingResult exceptionHandler(Exception e) {
        return CommandHandlingResult.of("Generic shell exception", 2);
    }

    @ExceptionResolver(CommandException.class)
    public CommandHandlingResult commandHandler(CommandException e) {
        return CommandHandlingResult.of("Command shell exception", 1);
    }
}
