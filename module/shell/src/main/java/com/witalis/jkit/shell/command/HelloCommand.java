package com.witalis.jkit.shell.command;

import com.witalis.jkit.shell.service.HelloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.validation.constraints.Size;

@ShellComponent
public class HelloCommand {
    private final HelloService helloService;

    @Autowired
    public HelloCommand(HelloService helloService) {
        this.helloService = helloService;
    }

    @ShellMethod(
        key = "greeting",
        prefix = "--",
        value = "Greeting for user",
        group = "Jkit"
    )
    public String greeting(
        @ShellOption(
            value = {"--username", "--user", "--u"},
            arity = 1,
            defaultValue = "User"
        )
        @Size(min = 3, max = 40)
        String username
    ) {
        return helloService.greeting(username);
    }
}
