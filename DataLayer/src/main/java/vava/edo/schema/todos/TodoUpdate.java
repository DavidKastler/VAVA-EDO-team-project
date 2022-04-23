package vava.edo.schema.todos;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Data transfer object for Task class
 * It is used for updating tasks
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class TodoUpdate extends TodoCreate {
    @NotNull
    private boolean completed;

    @Override
    public String toString() {
        return "TaskUpdate{" +
                super.toString() +
                "completed=" + completed+
                "} ";
    }
}
