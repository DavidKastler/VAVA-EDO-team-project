package vava.edo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vava.edo.schema.TaskCreate;

import javax.persistence.*;

/**
 * Class representing to-do in todos table
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "td_id", nullable = false)
    private Integer todoId;
    //TODO ak bude treba tak mapovanie
    @Column(name = "group_id", nullable = false)
    private Integer groupId;
    @Column(name = "task_name", nullable = false)
    private String taskName;
    @Column(name = "task_description", nullable = false)
    private String taskDescription;
    @Column(name = "from_time", nullable = false)
    private Long fromTime;
    @Column(name = "to_time", nullable = false)
    private Long toTime;
    @Column(name = "completed", nullable = false)
    private boolean completed;
    @Column(name = "tag", nullable = false)
    private String tag;

    //TODO prispojobit taskDto po jeho uprave
    /**
     * Static casting method from TaskCreate object
     * @param taskDto    TaskCreate object that you want to cast
     * @return          cast Task object
     */
//    public static Todo from(TaskCreate taskDto) {
//        Todo todo = new Todo();
//        todo.setUserId(taskDto.getUserId());
//        todo.setTaskName(taskDto.getTaskName());
//        todo.setTaskDescription(taskDto.getTaskDescription());
//        todo.setDueTime(taskDto.getDueTime());
//        return todo;
//    }


    /**
     * Debugging method
     * @return  string with method variables
     */
    @Override
    public String toString() {
        return "Todo{" +
                "todoId=" + todoId +
                ", groupId=" + groupId +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", fromTime=" + fromTime +
                ", toTime=" + toTime +
                ", completed=" + completed +
                ", tag='" + tag + '\'' +
                '}';
    }
}
