package vava.edo.Exepctions.LoginScreen;

import javafx.scene.control.Label;

public class EmptyLoginFields extends Exception{
    /**
     * Constructor for throwing a exception with a custom message
     *
     * @param message Error message
     */
    public EmptyLoginFields(String message, Label label) {
        super(message);
        label.setText(message);
        label.setVisible(true);
    }
}
