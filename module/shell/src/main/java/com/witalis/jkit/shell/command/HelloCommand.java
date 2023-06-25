package com.witalis.jkit.shell.command;

import com.witalis.jkit.shell.service.HelloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.context.InteractionMode;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import jakarta.validation.constraints.Size;

import static com.witalis.jkit.shell.utils.Constants.*;

@ShellComponent
public class HelloCommand {
    public static final String DEFAULT_USER = "User";
    private final HelloService helloService;

    @Autowired
    public HelloCommand(HelloService helloService) {
        this.helloService = helloService;
    }

    @ShellMethod(
        key = "greeting",
        prefix = PREFIX,
        value = "Greeting for user",
        group = GROUP,
        interactionMode = InteractionMode.ALL
    )
    public String greeting(
        @ShellOption(
            value = {"--username", "--user", "--u"},
            arity = 1,
            defaultValue = DEFAULT_USER,
            help = "Name of the user to greet"
        )
        @Size(min = 3, max = 40)
        String username
    ) {
        return helloService.greeting(username);
    }
}
