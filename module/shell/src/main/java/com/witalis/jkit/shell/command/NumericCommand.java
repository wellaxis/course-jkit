package com.witalis.jkit.shell.command;

import com.witalis.jkit.shell.service.NumericService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class NumericCommand {
    private final NumericService numericService;

    @Autowired
    public NumericCommand(NumericService numericService) {
        this.numericService = numericService;
    }

    @ShellMethod(
        key = "sum",
        prefix = "--",
        value = "Sum of numbers",
        group = "Jkit"
    )
    public double sum(
        @ShellOption(
            value = {"--numbers", "--n"},
            arity = 3,
            defaultValue = "three numbers should be specified"
        ) double[] numbers
    ) {
        return numericService.sum(numbers);
    }

    @ShellMethod(
        key = "max",
        prefix = "--",
        value = "Max of numbers",
        group = "Jkit"
    )
    public double max(
        @ShellOption(
            value = {"--numbers", "--n"},
            arity = 4,
            defaultValue = "three numbers should be specified"
        ) double[] numbers
    ) {
        return numericService.max(numbers);
    }
}
