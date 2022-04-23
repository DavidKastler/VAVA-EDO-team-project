package vava.edo.Exepctions.TodoScreen;

public class TodoDatabaseFail extends Exception{
    /**
     * Constructor for throwing a exception with a custom message
     *
     * @param message Error message
     */
    public TodoDatabaseFail(String message) {
        super(message);
    }
}
