package vava.edo.models;

import java.io.Serializable;
import java.sql.Date;
// TODO zmenit typ due time je to ako string kvoli BE
public class Todo implements Serializable {
    private int todoId;
    private int userId;
    private String todoName;
    private String todoDescription;
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

    public String getTodoName() {
        return todoName;
    }

    public void setTodoName(String todoName) {
        this.todoName = todoName;
    }

    public String getTodoDescription() {
        return todoDescription;
    }

    public void setTodoDescription(String todoDescription) {
        this.todoDescription = todoDescription;
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
                ", todoName='" + todoName + '\'' +
                ", taskDescription='" + todoDescription + '\'' +
                ", dueTime=" + dueTime +
                ", completed=" + completed +
                '}';
    }
}
