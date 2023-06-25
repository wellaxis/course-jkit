package com.witalis.jkit.shell.command;

import com.witalis.jkit.shell.service.NumericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.context.InteractionMode;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import static com.witalis.jkit.shell.utils.Constants.GROUP;
import static com.witalis.jkit.shell.utils.Constants.PREFIX;

@ShellComponent
public class NumericCommand {
    private final NumericService numericService;

    @Autowired
    public NumericCommand(NumericService numericService) {
        this.numericService = numericService;
    }

    @ShellMethod(
        key = "add",
        prefix = PREFIX,
        value = "Add three numbers",
        group = GROUP,
        interactionMode = InteractionMode.ALL
    )
    public double add(
        @ShellOption(
            value = {"--numbers", "--n"},
            arity = 3,
            help = "Three numbers to calculate their sum"
        ) double[] numbers
    ) {
        return numericService.sum(numbers);
    }

    @ShellMethod(
        key = "sum",
        prefix = PREFIX,
        value = "Sum of numbers",
        group = GROUP,
        interactionMode = InteractionMode.ALL
    )
    public double sum(
        @ShellOption(
            value = {"--numbers", "--n"},
            help = "Any count of numbers to calculate their sum"
        ) double[] numbers
    ) {
        return numericService.sum(numbers);
    }

    @ShellMethod(
        key = "max",
        prefix = PREFIX,
        value = "Max of numbers",
        group = GROUP,
        interactionMode = InteractionMode.ALL
    )
    public double max(
        @ShellOption(
            value = {"--numbers", "--n"},
            arity = 4,
            help = "Four numbers to calculate their max"
        ) double[] numbers
    ) {
        return numericService.max(numbers);
    }
}
