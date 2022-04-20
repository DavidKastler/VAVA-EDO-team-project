package vava.edo.controllers;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.json.JSONObject;
import vava.edo.models.User;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
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
        if(!Objects.equals(textUsername.getText(), "") && !Objects.equals(textPassword.getText(), "")) {
            JSONObject jo = new JSONObject();
            jo.put("username", textUsername.getText());
            jo.put("password", textPassword.getText());
            System.out.println(jo);
            try {
                HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/users/login")
                        .header("Content-Type", "application/json").body(jo).asJson();
                this.user = new Gson().fromJson(apiResponse.getBody().toString(), User.class);

                if(user.getUsername() != null) {
                    user.setLogged(true);
                    user.setLastActivity(LocalDateTime.now());
                    System.out.println("Logged in\t->\t" + user);
                    wrongCredentials.setVisible(false);
                    // UserHolder userHolder = UserHolder.getInstance();
                    // userHolder.setUser(user);

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/Todos.fxml"));
                    AnchorPane todoScreen = loader.load();
                    TodosController todoController = loader.<TodosController>getController();
                    todoController.initController(user);

                    //AnchorPane todoScreen = FXMLLoader.load(getClass().getResource("/vava/edo/Todos.fxml"));
                    // TodosController todosController = new TodosController();
                    rootPane.getChildren().setAll(todoScreen);
                }
                else {
                    System.out.println("Nesprávne údaje");
                    wrongCredentials.setText("Uncorrect username or password");
                    wrongCredentials.setVisible(true);
                }
            } catch (UnirestException e) {
                System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
                // e.printStackTrace();
            }
        }
        else {
            wrongCredentials.setText("Login fields are empty");
            wrongCredentials.setVisible(true);
        }
    }
}