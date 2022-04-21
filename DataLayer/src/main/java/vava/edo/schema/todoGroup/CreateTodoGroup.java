package vava.edo.schema.todoGroup;

import lombok.Data;

/**
 * DTO Class that serves data for creating to-do group in database
 */
@Data
public class CreateTodoGroup {
    private Integer userId;
    private String todoGroupName;
}
