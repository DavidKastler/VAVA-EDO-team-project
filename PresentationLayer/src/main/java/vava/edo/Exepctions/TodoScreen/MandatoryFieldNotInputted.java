package vava.edo.Exepctions.TodoScreen;

public class MandatoryFieldNotInputted extends Exception{
    /**
     * Constructor for throwing a exception with a custom message
     *
     * @param message Error message
     */
    public MandatoryFieldNotInputted(String message) {
        super(message);
    }
}
