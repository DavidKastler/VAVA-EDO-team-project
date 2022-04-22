package vava.edo.Exepctions.LoginScreen;

import javafx.scene.control.Label;

/**
 * Exception which is thrown when the user inputted incorrect credentials
 */
public class IncorrectCredentials extends Exception{

    /**
     * Constructor for throwing a exception with a custom message
     *
     * @param message Error message
     */
    public IncorrectCredentials(String message, Label label) {
        super(message);
        label.setText(message);
        label.setVisible(true);
    }
}
