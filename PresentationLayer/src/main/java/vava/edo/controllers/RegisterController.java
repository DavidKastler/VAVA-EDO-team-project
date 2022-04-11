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

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;


public class RegisterController implements Initializable {

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
    public void handleRegisterButton(MouseEvent mouseEvent) {
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        if (!this.textPassword1.getText().equals(this.textPassword2.getText()) || this.textUsername.getText().isEmpty()){
            System.out.println("Wrong Input!");
            wrongInput.setVisible(true);
            this.textUsername.clear();
            this.textPassword1.clear();
            this.textPassword2.clear();
        }else {
            wrongInput.setVisible(false);

            JSONObject jo = new JSONObject();
            jo.put("use rname", this.textUsername.getText());
            jo.put("password", this.textPassword1.getText());
            jo.put("confirm_password", this.textPassword2.getText());
            System.out.println(jo);

            try {
                HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/users/register").header("Content-Type", "application/json").body(jo).asJson();
                System.out.println(apiResponse.getBody().toString());
                //AnchorPane todoScreen = FXMLLoader.load(getClass().getResource("/vava/edo/Login.fxml"));
                //this.rootPane.getChildren().setAll(new Node[]{todoScreen});

            } catch (UnirestException e) {
                e.printStackTrace();
            }
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
