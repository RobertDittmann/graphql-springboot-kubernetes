package com.rdittmann.graphql.sample.GraphQGSample.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id",
            nullable = false, updatable = false)
    private User user;

    public Task() {
    }

    public Task(TaskType taskType, String description, User user) {
        this.taskType = taskType;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                taskType == task.taskType &&
                Objects.equals(description, task.description) &&
                Objects.equals(user, task.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskType, description, user);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskType=" + taskType +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
