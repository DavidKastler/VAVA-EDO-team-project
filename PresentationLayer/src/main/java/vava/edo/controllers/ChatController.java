package vava.edo.controllers;


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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;


public class ChatController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button search_button;

    @FXML
    private Button send_message_button;

    @FXML
    private Button block_user;

    @FXML
    private Label chat_name;

    @FXML
    private TextField chat_message;

    @FXML
    private VBox chat_list_pane;

    @FXML
    private ScrollPane chat_pane;



    public ChatController() {

    }


    public Button editButton(Button button, String username, Boolean color){
        button.setText(username);
        button.setWrapText(true);
        Font font = Font.font("Arial", FontWeight.BOLD, 22);
        button.setFont(font);
        button.setStyle("-fx-background-insets: 0 0 -1 0");

        if (color == true){
            button.setStyle("-fx-background-color: #c4c4c4");
        } else {
            button.setStyle("-fx-background-color:  #808080");
        }

        //button.setStyle("-fx-font-weight: bold");
        button.setPrefHeight(50);
        button.setPrefWidth(240);

        return button;
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
        //**** user 1 ***
        String id1 = "1234";
        String username1 = "Jano";
        String password1 = "";
        String role1 = "";
        String rememberMe1 = "";
        String isLogged1 = "";
        String lastActivity1 = "";

        //**** user 2 ***
        String id2 = "1235";
        String username2 = "Fero";
        String password2 = "";
        String role2 = "";
        String rememberMe2 = "";
        String isLogged2 = "";
        String lastActivity2 = "";


        for (int i = 1; i <= 12; i++){
            //System.out.println(id1);
            Button button = new Button();
            Boolean color;
            String username;
            if (i % 2 == 0){
                color = false;
                username = username1;
            } else {
                color = true;
                username = username2;
            }
            editButton(button, username1, color);


            this.chat_list_pane.getChildren().add(button);
        }


    }

    @FXML
    public void handleViewChatButton(MouseEvent mouseEvent) throws IOException {
        //metoda na vratenie userov v danych chatoch/groupach
    }

    @FXML
    public void handleSearchChatButton(MouseEvent mouseEvent) throws IOException {
        //searching v zozname
    }

    @FXML
    public void handleNewChatButton(MouseEvent mouseEvent) throws IOException {
        //
    }

    @FXML
    public void handleReportUserButton(MouseEvent mouseEvent) throws IOException {

    }

    @FXML
    public void handleSendMessageButton(MouseEvent mouseEvent) throws IOException {
    }

    //metoda na vratenie friendov
    //metoda na vyhladavanie medzi friendami
    //metoda na vymazanie frienda podla ID
    //report usera


}
