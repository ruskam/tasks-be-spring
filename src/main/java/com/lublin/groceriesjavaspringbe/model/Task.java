package com.lublin.groceriesjavaspringbe.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "task_spring")
public class Task {

    @Id
    private String id;

    private String taskId;
    private String title;
    private boolean isDone;
    private Date date;

    private String userId;

    public Task(String taskId, String title, boolean isDone, Date date, String userId) {
        this.taskId = taskId;
        this.title = title;
        this.isDone = isDone;
        this.date = date;
        this.userId = userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", taskId='" + taskId + '\'' +
                ", title='" + title + '\'' +
                ", isDone=" + isDone +
                ", date=" + date +
                ", userId='" + userId + '\'' +
                '}';
    }
}
