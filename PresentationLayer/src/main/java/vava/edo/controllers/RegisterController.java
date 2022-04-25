package vava.edo.controllers;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONObject;
import vava.edo.Exepctions.LoginScreen.FailedToRegister;
import vava.edo.Handlers.UserHandler;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;


public class RegisterController{

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField textUsername;

    @FXML
    private PasswordField textPassword1;

    @FXML
    private PasswordField textPassword2;

    @FXML
    private Label wrongInput;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnBackToLogin;

    public RegisterController() {
    }


    @FXML
    public void handleRegisterButton(MouseEvent mouseEvent) {
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            UserHandler.registerUser(this.textUsername, this.textPassword1, this.textPassword2, this.wrongInput);

            AnchorPane loginScreen = FXMLLoader.load(getClass().getResource("/vava/edo/Login.fxml"));

            this.rootPane.getChildren().setAll(new Node[]{loginScreen});

        }catch (IOException | FailedToRegister e){
            e.printStackTrace();
        }

    }

    @FXML
    public void handleBackToLoginButton(MouseEvent mouseEvent) throws IOException {
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        AnchorPane loginScreen = FXMLLoader.load(getClass().getResource("/vava/edo/Login.fxml"));

        this.rootPane.getChildren().setAll(new Node[]{loginScreen});

    }
}
