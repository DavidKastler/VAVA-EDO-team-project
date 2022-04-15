package vava.edo.models;

import java.io.Serializable;
import java.sql.Date;

public class Task implements Serializable {
    private int todoId;
    private int userId;
    private String taskName;
    private String taskDescription;
    private Date dueTime;
    private boolean completed;

    @Override
    public String toString() {
        return "Task{" +
                "todoId=" + todoId +
                ", userId=" + userId +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", dueTime=" + dueTime +
                ", completed=" + completed +
                '}';
    }
}
