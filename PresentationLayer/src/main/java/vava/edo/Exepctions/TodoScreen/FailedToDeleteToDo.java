package vava.edo.Exepctions.TodoScreen;

public class FailedToDeleteToDo extends Exception{
    /**
     * Constructor for throwing a exception with a custom message
     *
     * @param message Error message
     */
    public FailedToDeleteToDo(String message) {
        super(message);
    }
}
