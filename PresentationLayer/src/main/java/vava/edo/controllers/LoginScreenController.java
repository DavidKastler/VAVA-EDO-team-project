package vava.edo.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import vava.edo.Exepctions.LoginScreen.EmptyLoginFields;
import vava.edo.Exepctions.LoginScreen.IncorrectCredentials;
import vava.edo.Handlers.UserHandler;
import vava.edo.models.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {
    private User user = null;
    private boolean rememberMeState = false;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField textUsername;

    @FXML
    private PasswordField textPassword;

    @FXML
    private Label wrongCredentials;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnLoginGuest;

    @FXML
    private Button btnForgotPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    protected void handleLoginButton() throws IOException {

        try {
            this.user = UserHandler.loginUser(textUsername, textPassword, wrongCredentials);

            this.user.setRememberMe(this.rememberMeState);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/Menu.fxml"));
            AnchorPane todoScreen = loader.load();
            MenuScreenController menuScreen = loader.getController();
            menuScreen.initialize(user, rootPane);

            rootPane.getChildren().setAll(todoScreen);
        }
        catch (EmptyLoginFields | IncorrectCredentials e){
            e.printStackTrace();
        }

    }

    /**
     * Method that on click of the remember me box changes the state
     */
    @FXML
    public void rememberMe() {
        this.rememberMeState = !this.rememberMeState;
    }
}