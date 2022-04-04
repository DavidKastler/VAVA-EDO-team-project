package vava.edo.exeption;

import java.text.MessageFormat;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(final int id){
        super(MessageFormat.format("Could not find user with id: {0}", id));
    }
}
