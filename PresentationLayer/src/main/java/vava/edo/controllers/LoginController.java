package vava.edo.controllers;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.json.JSONObject;
import vava.edo.models.User;

import java.io.IOException;

public class LoginController {

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

    @FXML
    protected void handleLoginButton() throws IOException {
        JSONObject jo = new JSONObject();
        jo.put("username", textUsername.getText());
        jo.put("password", textPassword.getText());
        System.out.println(jo);
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/users/login")
                    .header("Content-Type", "application/json").body(jo).asJson();
            User user = new Gson().fromJson(apiResponse.getBody().toString(), User.class);

            if(user.getUsername() != null) {
                System.out.println("Logged in\t->\t" + user);
                wrongCredentials.setVisible(false);
                AnchorPane todoScreen = FXMLLoader.load(getClass().getResource("/vava/edo/Todos.fxml"));
                rootPane.getChildren().setAll(todoScreen);
            }
            else {
                System.out.println("Nesprávne údaje");
                wrongCredentials.setVisible(true);
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}