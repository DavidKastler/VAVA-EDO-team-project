package vava.edo.exeption;

import java.text.MessageFormat;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(final int id){
        super(MessageFormat.format("Could not find role with id: {0}", (long) id));
    }

}

