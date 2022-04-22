package vava.edo.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import vava.edo.Exepctions.EmptyLoginFields;
import vava.edo.Exepctions.IncorrectCredentials;
import vava.edo.models.User;
import vava.edo.Handlers.UserHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {
    private User user;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField textUsername;

    @FXML
    private PasswordField textPassword;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    protected void handleLoginButton() throws IOException {

        try {
            this.user = UserHandler.loginUser(textUsername, textPassword, wrongCredentials);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/Todos.fxml"));
            AnchorPane todoScreen = loader.load();
            TodosScreenController todoController = loader.getController();
            todoController.initialize(user);

            rootPane.getChildren().setAll(todoScreen);
        }
        catch (EmptyLoginFields | IncorrectCredentials e){
            e.printStackTrace();
        }

    }

}