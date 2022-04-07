package vava.edo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vava.edo.schema.TaskCreate;

import javax.persistence.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "todos")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "td_id", nullable = false)
    private int todoId;
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Column(name = "task_name", nullable = false)
    private String taskName;
    @Column(name = "task_description", nullable = false)
    private String taskDescription;
    @Column(name = "due_time", nullable = false)
    private Date dueTime;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "completed", nullable = false)
    private boolean completed;

    public static Task from(TaskCreate taskCreate) {
        Task task = new Task();
        task.setUserId(taskCreate.getUserId());
        task.setTaskName(taskCreate.getTaskName());
        task.setTaskDescription(taskCreate.getTaskDescription());
        task.setDueTime(taskCreate.getDueTime());
        return task;
    }


    /**
     * Debugging method
     * @return  string with method variables
     */

    @Override
    public String toString() {
        return "Task{" +
                "rId=" + todoId +
                ", userId='" + userId + '\'' +
                ", taskName=" + taskName +
                ", taskDescription=" + taskDescription +
                ", dueTime=" + dueTime +
                ", completed=" + completed +
                '}';
    }

}
