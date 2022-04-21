package vava.edo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vava.edo.schema.todoGroup.CreateTodoGroup;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class representing one to-do group in todo_group table
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todo_group")
public class TodoGroup {
    @Id
    @Column(name = "td_g_id")
    private Integer todoGroupId;
    @Column(name = "u_id")
    private Integer uId;
    @Column(name = "td_group_name")
    private String tdGroupName;


    /**
     * Cast method for creating TodoGroup from CreateTodoGroup class
     * @param createTodoGroup
     * @return
     */
    public static TodoGroup from(CreateTodoGroup createTodoGroup) {
        TodoGroup todoGroup = new TodoGroup();
        todoGroup.setUId(createTodoGroup.getUserId());
        todoGroup.setTdGroupName(createTodoGroup.getTodoGroupName());
        return todoGroup;
    }
}
