package vava.edo.models;

import java.io.Serializable;
// TODO zmenit typ due time je to ako string kvoli BE
public class Todo implements Serializable {
    private int todoId;
    private int userId;
    private String taskName;  // bude to todoName
    private String taskDescription;
    private String dueTime;
    private boolean completed;


    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    @Override
    public String toString() {
        return "Todo{" +
                "todoId=" + todoId +
                ", userId=" + userId +
                ", todoName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", dueTime=" + dueTime +
                ", completed=" + completed +
                '}';
    }
}
