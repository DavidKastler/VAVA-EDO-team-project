package vava.edo.Exepctions.HttpStatusExceptions;

import java.text.MessageFormat;

public class UnexpectedHttpStatusException extends Exception{

    public UnexpectedHttpStatusException(final int id){
        super(MessageFormat.format("Unexpected response status: {0}", id));
    }
}
