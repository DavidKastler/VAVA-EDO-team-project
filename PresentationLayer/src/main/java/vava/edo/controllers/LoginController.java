package vava.edo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField textUsername;

    @FXML
    private TextField textPassword;

    @FXML
    private CheckBox checkBoxRememberMe;

    @FXML
    private Label wrongCredentials;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnLoginGuest;

    @FXML
    private Button btnForgotPassword;

    @FXML
    protected void handleLoginButton() {
        wrongCredentials.setVisible(true);
    }
}