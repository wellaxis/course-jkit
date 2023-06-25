package com.witalis.jkit.shell.command;

import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.context.InteractionMode;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static com.witalis.jkit.shell.utils.Constants.GROUP;
import static com.witalis.jkit.shell.utils.Constants.PREFIX;

@ShellComponent
public class CourseCommand {
    public static final String MESSAGE = "Java Kit Course in Action!";
    private final Terminal terminal;

    @Autowired
    public CourseCommand(final Terminal terminal) {
        this.terminal = terminal;
    }

    @ShellMethod(
        key = "course",
        prefix = PREFIX,
        value = "Course Introduction",
        group = GROUP,
        interactionMode = InteractionMode.ALL
    )
    public void course() {
        terminal.writer().println(MESSAGE);
        terminal.writer().flush();
    }
}
