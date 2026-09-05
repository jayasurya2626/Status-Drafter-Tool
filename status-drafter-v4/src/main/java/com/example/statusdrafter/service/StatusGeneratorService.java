package com.example.statusdrafter.service;

import com.example.statusdrafter.entity.StatusUpdate;
import com.example.statusdrafter.entity.Task;
import com.example.statusdrafter.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StatusGeneratorService {

    private final TaskService taskService;

    public StatusGeneratorService(TaskService taskService) {
        this.taskService = taskService;
    }

    public StatusUpdate generate(User user, LocalDate date) {
        return generate(user, date, null);
    }

    public StatusUpdate generate(User user, LocalDate date, Set<Long> selectedIds) {
        List<Task> tasks = taskService.mine(user);

        if (selectedIds != null && !selectedIds.isEmpty()) {
            tasks = tasks.stream()
                    .filter(task -> selectedIds.contains(task.getId()))
                    .toList();
        }

        StatusUpdate statusUpdate = new StatusUpdate();
        statusUpdate.setEmployee(user);
        statusUpdate.setUpdateDate(date);

        statusUpdate.setCompletedWork(join(tasks.stream()
                .filter(task -> task.getStatus() == Task.Status.COMPLETED)
                .limit(10)
                .map(Task::getTaskName)
                .toList()));

        statusUpdate.setCurrentWork(join(tasks.stream()
                .filter(task -> task.getStatus() == Task.Status.IN_PROGRESS)
                .limit(10)
                .map(Task::getTaskName)
                .toList()));

        statusUpdate.setPendingWork(join(tasks.stream()
                .filter(task -> task.getStatus() == Task.Status.NOT_STARTED
                        || task.getStatus() == Task.Status.ON_HOLD)
                .limit(10)
                .map(Task::getTaskName)
                .toList()));

        statusUpdate.setBlockers(join(tasks.stream()
                .filter(task -> task.getStatus() == Task.Status.BLOCKED)
                .limit(10)
                .map(task -> task.getTaskName() + " (blocked)")
                .toList()));

        statusUpdate.setNextSteps(join(tasks.stream()
                .filter(task -> task.getStatus() != Task.Status.COMPLETED)
                .sorted(Comparator.comparing(
                        Task::getDeadline,
                        Comparator.nullsLast(Comparator.naturalOrder())))
                .limit(5)
                .map(Task::getTaskName)
                .toList()));

        return statusUpdate;
    }

    private String join(List<String> values) {
        if (values.isEmpty()) {
            return "None";
        }

        return values.stream()
                .map(value -> "- " + value)
                .collect(Collectors.joining("\n"));
    }

    public String text(StatusUpdate statusUpdate) {
        return "Daily Status Update - " + statusUpdate.getUpdateDate() + "\n\n"
                + "Today's Completed Work\n"
                + statusUpdate.getCompletedWork() + "\n\n"
                + "Current Work\n"
                + statusUpdate.getCurrentWork() + "\n\n"
                + "Pending Tasks\n"
                + statusUpdate.getPendingWork() + "\n\n"
                + "Blockers\n"
                + statusUpdate.getBlockers() + "\n\n"
                + "Next Steps\n"
                + statusUpdate.getNextSteps();
    }
}
