package vava.edo.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class Error404NotFoundException extends RuntimeException{

    public Error404NotFoundException(String exception) {
        super(exception);
    }
}
