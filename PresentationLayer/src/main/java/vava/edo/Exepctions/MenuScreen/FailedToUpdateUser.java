package vava.edo.Exepctions.MenuScreen;

public class FailedToUpdateUser extends Exception{
    /**
     * Constructor for throwing a exception with a custom message
     *
     * @param message Error message
     */
    public FailedToUpdateUser(String message) {
        super(message);
    }
}
