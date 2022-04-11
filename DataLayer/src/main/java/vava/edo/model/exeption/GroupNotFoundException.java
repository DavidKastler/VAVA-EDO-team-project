package vava.edo.model.exeption;

import java.text.MessageFormat;

public class GroupNotFoundException extends RuntimeException {

    public GroupNotFoundException(final int id){
        super(MessageFormat.format("Could not find group with id: {0}", id));
    }
}