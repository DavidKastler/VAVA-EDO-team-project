package vava.edo.schema.todos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


/**
 * Data transfer object for To-do class
 * It is used for creating a to-do
 */
@Data
@NoArgsConstructor
public class TodoCreate {

    @NonNull
    private Integer userId;
    @NonNull
    private String todoName;
    @NonNull
    private String todoDescription;
    @NonNull
    private Long toTime;
    @NonNull
    private Long fromTime;
    @NonNull
    private Boolean completed;
    @NonNull
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