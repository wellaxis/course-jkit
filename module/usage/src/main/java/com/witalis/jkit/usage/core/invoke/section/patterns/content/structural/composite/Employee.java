package com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.composite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Employee class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Slf4j
public class Employee implements Assignee {
    public static final int THRESHOLD = 18;
    private String name;
    private int age;
    private boolean active = true;

    @Override
    public boolean canHandleTask(Task task) {
        return age > THRESHOLD && isActive();
    }

    @Override
    public void takeTask(Task task) {
        log.info("Employee {} {}", getName(), task.execute());
        setActive(false);
    }
}
