package com.witalis.jkit.shell.command;

import com.witalis.jkit.shell.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import static com.witalis.jkit.shell.utils.Constants.GROUP;
import static com.witalis.jkit.shell.utils.Constants.PREFIX;

@ShellComponent
public class ScenarioCommand {
    private final ScenarioService scenarioService;

    @Autowired
    public ScenarioCommand(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }

    @ShellMethod(
        key = "activate",
        prefix = PREFIX,
        value = "Scenario activation",
        group = GROUP
    )
    public void activate() {
        scenarioService.activate();
    }

    @ShellMethod(
        key = "deactivate",
        prefix = PREFIX,
        value = "Scenario deactivation",
        group = GROUP
    )
    public void deactivate() {
        scenarioService.deactivate();
    }

    @ShellMethod(
        key = "action",
        prefix = PREFIX,
        value = "Scenario action",
        group = GROUP
    )
    @ShellMethodAvailability("checkActionValidity")
    public String action() {
        return scenarioService.action();
    }

    @ShellMethodAvailability({"action", "pause", "cancel"})
    public Availability checkActionValidity() {
        final String reason = "the access to scenario is switched off. Please, enable scenario!";

        return scenarioService.isActivated()
            ? Availability.available()
            : Availability.unavailable(reason);
    }
}
