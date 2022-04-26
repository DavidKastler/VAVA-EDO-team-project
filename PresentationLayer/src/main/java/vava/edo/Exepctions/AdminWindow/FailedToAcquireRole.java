package vava.edo.Exepctions.AdminWindow;

public class FailedToAcquireRole extends Exception{
    /**
     * Constructor for throwing a exception with a custom message
     *
     * @param message Error message
     */
    public FailedToAcquireRole(String message) {
        super(message);
    }
}
