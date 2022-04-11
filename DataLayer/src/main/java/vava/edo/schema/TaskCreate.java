package vava.edo.schema;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;


/**
 * Data transfer object for Task class
 * It is used for creating a task
 */
@Data
public class TaskCreate {
    @NotNull
    private int userId;
    private String taskName;
    private String taskDescription;
    private Date dueTime;

    @Override
    public String toString() {
        return "TaskCreate{" +
                ", userId='" + userId + '\'' +
                ", taskName=" + taskName +
                ", taskDescription=" + taskDescription +
                ", dueTime=" + dueTime +
                '}';
    }
}