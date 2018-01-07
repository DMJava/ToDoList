package com.app.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "task")
public class Task implements Serializable {
    private static final long UID = 35145646513L;

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "description")
    private String description;

    @Column(name = "is_done")
    private boolean isDone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (isDone != task.isDone) return false;
        if (name != null ? !name.equals(task.name) : task.name != null) return false;
        if (createDate != null ? !createDate.equals(task.createDate) : task.createDate != null) return false;
        return description != null ? description.equals(task.description) : task.description == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isDone ? 1 : 0);
        return result;
    }
}
