package vava.edo.schema;

import com.sun.istack.NotNull;
import lombok.Data;

import java.sql.Date;


/**
 * Data transfer object for Task class
 * It is used for creating a task
 */
@Data
public class TaskCreate {

    private Integer todoGroupId;

    private String todoName;
    private String todoDescription;
    private Long toTime;
    private Long fromTime;
    private Boolean completed;
    private String tag;

    @Override
    public String toString() {
        return "TaskCreate{" +
                "todoGroupId=" + todoGroupId +
                ", todoName='" + todoName + '\'' +
                ", todoDescription='" + todoDescription + '\'' +
                ", toTime=" + toTime +
                ", fromTime=" + fromTime +
                ", completed=" + completed +
                ", tag='" + tag + '\'' +
                '}';
    }
}