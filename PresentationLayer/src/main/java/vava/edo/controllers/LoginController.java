package vava.edo.controllers;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONObject;
import vava.edo.models.User;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

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
        try {
            URL checkConnectionURL = new URL("http://www.google.com");
            URLConnection checkConnection = checkConnectionURL.openConnection();
            checkConnection.connect();
            System.out.println("Internet is connected");
        } catch (IOException e) {
            System.out.println("Internet is not connected");
        }
    }

    @FXML
    protected void handleLoginButton(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        JSONObject jo = new JSONObject();
        jo.put("username", textUsername.getText());
        jo.put("password", textPassword.getText());
        System.out.println(jo);
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/users/login")
                    .header("Content-Type", "application/json").body(jo).asJson();
            User user = new Gson().fromJson(apiResponse.getBody().toString(), User.class);

            if(user.getUsername() != null) {
                user.setLogged(true);
                user.setLastActivity(LocalDateTime.now());
                System.out.println("Logged in\t->\t" + user);

                wrongCredentials.setVisible(false);
                AnchorPane todoScreen = FXMLLoader.load(getClass().getResource("/vava/edo/Todos.fxml"));
                rootPane.getChildren().setAll(todoScreen);
                stage.setUserData(user);
            }
            else {
                System.out.println("Nesprávne údaje");
                wrongCredentials.setVisible(true);
            }
        } catch (UnirestException e) {
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
            // e.printStackTrace();
        }
    }
}