package com.witalis.jkit.shell.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@Service
public class ScenarioService {
    private boolean activated;

    public ScenarioService() {
        setActivated(false);
    }

    /**
     * It activates the scenario.
     */
    public void activate() {
        log.info("Scenario [activate]: true");
        setActivated(true);
    }

    /**
     * It deactivates the scenario.
     */
    public void deactivate() {
        log.info("Scenario [deactivate]: false");
        setActivated(false);
    }

    /**
     * It emulates an action of the scenario.
     */
    public String action() {
        log.info("Scenario [action]: download");
        return activated ? "Download data..." : "Access denied...";
    }
}
