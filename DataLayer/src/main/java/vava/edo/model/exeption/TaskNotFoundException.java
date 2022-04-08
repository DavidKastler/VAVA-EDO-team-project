package vava.edo.model.exeption;

import java.text.MessageFormat;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(final int id){
        super(MessageFormat.format("Could not find task with id: {0}", id));
    }
}
