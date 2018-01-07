package com.app.dao;

import com.app.entity.Task;

import java.util.List;

public interface TaskDao {
    List<Task> getAllTask(Integer startingId, Integer maxCount, Boolean isDone);

    Task getTaskById(Integer id);

    Long getCountTask(Boolean isDone);

    void addTask(Task task);

    void updateTask(Task task);

    void deleteTask(Integer id);
}
