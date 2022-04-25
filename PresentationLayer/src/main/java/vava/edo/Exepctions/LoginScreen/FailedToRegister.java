package vava.edo.Exepctions.LoginScreen;

public class FailedToRegister extends Exception{

    /**
     * Constructor for throwing a exception with a custom message
     *
     * @param message Error message
     */
    public FailedToRegister(String message) {
        super(message);
    }
}
