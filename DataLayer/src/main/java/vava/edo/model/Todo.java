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
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Column(name = "todo_name", nullable = false)
    private String todoName;
    @Column(name = "todo_description", nullable = false)
    private String todoDescription;
    @Column(name = "from_time", nullable = false)
    private Long fromTime;
    @Column(name = "to_time", nullable = false)
    private Long toTime;
    @Column(name = "completed", nullable = false)
    private boolean completed;
    @Column(name = "group_name")
    private String groupName;

    /**
     * Static casting method from TaskCreate object
     * @param taskDto    TaskCreate object that you want to cast
     * @return          cast Task object
     */
    public static Todo from(TaskCreate taskDto) {
        Todo todo = new Todo();
        todo.setUserId(taskDto.getUserId());
        todo.setTodoName(taskDto.getTodoName());
        todo.setTodoDescription(taskDto.getTodoDescription());
        todo.setFromTime(taskDto.getFromTime());
        todo.setToTime(taskDto.getToTime());
        todo.setCompleted(taskDto.getCompleted());
        todo.setGroupName(taskDto.getGroupName());
        return todo;
    }

    /**
     * Debugging method
     * @return  string with method variables
     */
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
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
