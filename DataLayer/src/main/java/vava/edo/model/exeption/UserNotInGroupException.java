package vava.edo.model.exeption;

import java.text.MessageFormat;

public class UserNotInGroupException extends RuntimeException {

    public UserNotInGroupException(){
        super("User isn't part of select group");
    }
}
