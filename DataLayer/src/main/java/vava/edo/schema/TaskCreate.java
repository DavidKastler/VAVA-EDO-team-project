package vava.edo.schema;

import lombok.Data;


/**
 * Data transfer object for Task class
 * It is used for creating a task
 */
@Data
public class TaskCreate {

    private Integer userId;
    private String todoName;
    private String todoDescription;
    private Long toTime;
    private Long fromTime;
    private Boolean completed;
    private String groupName;

    @Override
    public String toString() {
        return "TaskCreate{" +
                "todoGroupId=" + userId +
                ", todoName='" + todoName + '\'' +
                ", todoDescription='" + todoDescription + '\'' +
                ", toTime=" + toTime +
                ", fromTime=" + fromTime +
                ", completed=" + completed +
                ", tag='" + groupName + '\'' +
                '}';
    }
}