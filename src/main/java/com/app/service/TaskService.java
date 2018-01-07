package com.app.service;

import com.app.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTask(Integer id, Integer maxCount, Boolean isDone);

    Task getTaskById(Integer id);

    Long getCountTask(Boolean isDone);

    void addTask(Task task);

    void updateTask(Task task);

    void deleteTask(Integer id);
}
