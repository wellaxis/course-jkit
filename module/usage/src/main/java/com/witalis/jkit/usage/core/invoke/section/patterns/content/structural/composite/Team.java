package com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.composite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc: Team class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public
class Team implements Assignee {
    private List<Employee> employees = new ArrayList<>();

    @Override
    public boolean canHandleTask(Task task) {
        for (Employee employee : employees) {
            if (employee.canHandleTask(task)) return true;
        }
        return false;
    }

    @Override
    public void takeTask(Task task) {
        for (Employee employee : employees) {
            if (employee.canHandleTask(task))
                employee.takeTask(task);
        }
    }
}
