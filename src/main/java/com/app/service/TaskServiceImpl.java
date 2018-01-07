package com.app.service;

import com.app.dao.TaskDaoImpl;
import com.app.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDaoImpl taskDao;

    public List<Task> getAllTask(Integer id, Integer maxCount, Boolean isDone) {
        return taskDao.getAllTask(id, maxCount, isDone);
    }

    public Task getTaskById(Integer id) {
        return taskDao.getTaskById(id);
    }

    public Long getCountTask(Boolean isDone) {
        return taskDao.getCountTask(isDone);
    }

    public void addTask(Task task) {
        taskDao.addTask(task);
    }

    public void updateTask(Task task) {
        taskDao.updateTask(task);
    }

    public void deleteTask(Integer id) {
        taskDao.deleteTask(id);
    }
}
