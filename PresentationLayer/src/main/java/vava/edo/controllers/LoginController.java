package vava.edo.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import vava.edo.models.User;

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
        JsonObject loginCredentials = new JsonObject();
        loginCredentials.addProperty("username", textUsername.getText());
        loginCredentials.addProperty("password", textPassword.getText());
        System.out.println(loginCredentials);
        System.out.println(loginCredentials.getAsJsonObject());
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/users/login").body(loginCredentials.getAsJsonObject()).asJson();
            // User user = new Gson().fromJson(apiResponse.getBody().toString(), User.class);
            System.out.println(apiResponse.getBody().toString());
            // System.out.println(user.toString());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    private static class UserLogin {
        private String username;
        private String password;

        public UserLogin(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }
}