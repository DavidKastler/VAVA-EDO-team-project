package vava.edo.Exepctions.HttpStatusExceptions;

import java.text.MessageFormat;

public class UnexpectedHttpStatusException extends Exception{

    public UnexpectedHttpStatusException(final int status, final int expectedStatus){
        super(MessageFormat.format("Unexpected response status: {0}, Expected : {1}", status, expectedStatus));
    }
}
