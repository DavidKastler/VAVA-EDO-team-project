package vava.edo.Exepctions.MenuScreen;

public class FeedbackCreationError extends Exception{
    /**
     * Constructor for throwing a exception with a custom message
     *
     * @param message Error message
     */
    public FeedbackCreationError(String message) {
        super(message);
    }
}
