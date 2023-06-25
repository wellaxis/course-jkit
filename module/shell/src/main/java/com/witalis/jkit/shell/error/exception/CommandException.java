package com.witalis.jkit.shell.error.exception;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.core.annotation.Order;
import org.springframework.shell.command.CommandExceptionResolver;

@Order(CommandExceptionResolver.DEFAULT_PRECEDENCE)
public class CommandException extends RuntimeException implements ExitCodeGenerator {

    public CommandException() {
        super();
    }

    public CommandException(String message) {
        super(message);
    }

    @Override
    public int getExitCode() {
        return 0;
    }
}
