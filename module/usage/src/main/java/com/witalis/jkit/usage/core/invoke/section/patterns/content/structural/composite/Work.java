package com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.composite;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

/**
 * Desc:
 * User: Wellaxis
 * Date: 4/28/2022
 */
@ToString
@Data
public
class Work implements Task {
    private String id;
    private boolean status;

    public Work() {
        this.id = UUID.randomUUID().toString();
        this.status = false;
    }

    @Override
    public String execute() {
        setStatus(true);
        return "executes work " + getId();
    }

    @Override
    public boolean done() {
        return getStatus();
    }

    private boolean getStatus() {
        return status;
    }
}
