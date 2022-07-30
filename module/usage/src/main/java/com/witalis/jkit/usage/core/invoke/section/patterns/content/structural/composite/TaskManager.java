package com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.composite;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Desc: Task Manager class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@Data
@Slf4j
public class TaskManager {
    private List<Assignee> assignees;
    private List<Task> tasks;

    public void performTasks() {
        for (Task task : tasks) {
            for (Assignee assignee : assignees) {
                if (assignee.canHandleTask(task)) {
                    assignee.takeTask(task);
                    break;
                }
            }
            if (!task.done()) {
                log.error("Cannot handle the task {}, please hire more people", task.getId());
                return;
            }
        }
    }
}
