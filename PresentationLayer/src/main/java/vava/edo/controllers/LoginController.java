package vava.edo.controllers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONObject;

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
        JSONObject jo = new JSONObject();
        jo.put("username", textUsername.getText());
        jo.put("password", textPassword.getText());
        System.out.println(jo);
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/users/login")
                    .header("Content-Type", "application/json").body(jo).asJson();
            // User user = new Gson().fromJson(apiResponse.getBody().toString(), User.class);
            System.out.println(apiResponse.getBody().toString());
            // System.out.println(user.toString());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}