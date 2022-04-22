package vava.edo.Exepctions.TodoScreen;

public class FailedToCreateTodo extends Exception{
    /**
     * Constructor for throwing a exception with a custom message
     *
     * @param message Error message
     */
    public FailedToCreateTodo(String message) {
        super(message);
    }
}
