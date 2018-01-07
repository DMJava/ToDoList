package com.app.dao;

import com.app.entity.Task;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class TaskDaoImpl implements TaskDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    private List<Task> convertResult(Query query) {
        List<Task> result = new LinkedList<Task>();
        for (Object obj : query.list()) {
            Object[] array = (Object[]) obj;
            Task task = new Task();
            task.setId((Integer) array[0]);
            task.setName((String) array[1]);
            task.setCreateDate((Date) array[2]);
            task.setDescription((String) array[3]);
            task.setDone((Boolean) array[4]);
            result.add(task);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<Task> getAllTask(Integer startingId, Integer maxCount, Boolean isDone) {
        String resultString = "";
        if (isDone != null) {
            if (isDone) {
                resultString = "WHERE d.IS_DONE = TRUE";
            } else {
                resultString = "WHERE d.IS_DONE = FALSE";
            }
        }
        String sql = String.format("SELECT * FROM deal as d %s ORDER BY d.ID ASC LIMIT %d %d",
                resultString, startingId, startingId + maxCount);
        Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
        return convertResult(query);
    }

    public Task getTaskById(Integer id) {
        return hibernateTemplate.get(Task.class, id);
    }

    public Long getCountTask(Boolean isDone) {
        String resultString = "";
        if (isDone != null) {
            if (isDone) {
                resultString = "WHERE d.IS_DONE = TRUE";
            } else {
                resultString = "WHERE d.IS_DONE = FALSE";
            }
        }
        Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("select count (*) from Task " + resultString);
        return (Long) query.uniqueResult();
    }

    public void addTask(Task task) {
        hibernateTemplate.save(task);
    }

    public void updateTask(Task task) {
        hibernateTemplate.flush();
        Task tempTask = getTaskById(task.getId());
        tempTask.setName(task.getName());
        tempTask.setDescription(task.getDescription());
        tempTask.setDone(task.getDone());
        hibernateTemplate.update(tempTask);
    }

    public void deleteTask(Integer id) {
        hibernateTemplate.delete(getTaskById(id));
    }
}