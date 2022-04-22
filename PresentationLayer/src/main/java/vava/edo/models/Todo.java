package vava.edo.models;

import java.io.Serializable;

public class Todo implements Serializable {
    private int todoId;
    private int userId;
    private String todoName;
    private String todoDescription;
    private long fromTime;
    private long toTime;
    private boolean completed;
    private String groupName;


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

    public void setTodoName(String taskName) {
        this.todoName = todoName;
    }

    public String getTodoDescription() {
        return todoDescription;
    }

    public void setTodoDescription(String todoDescription) {
        this.todoDescription = todoDescription;
    }

    public long getFromTime() {
        return fromTime;
    }

    public void setFromTime(long fromTime) {
        this.fromTime = fromTime;
    }

    public long getToTime() {
        return toTime;
    }

    public void setToTime(long toTime) {
        this.toTime = toTime;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
                ", todoDescription='" + todoDescription + '\'' +
                ", fromTime=" + fromTime +
                ", toTime=" + toTime +
                ", completed=" + completed +
                ", groupName=" + groupName +
                '}';
    }
}
