package vava.edo.model.exeption;

public class UserNotInGroupException extends RuntimeException {

    public UserNotInGroupException(){
        super("User isn't part of select group");
    }
}
