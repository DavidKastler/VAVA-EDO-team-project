package vava.edo.controllers;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.collections.ObservableList;
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
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
        Font font = Font.font("Arial", FontWeight.BOLD, 24);
        button.setFont(font);
        //button.setStyle("-fx-background-insets: 0 0 -1 0");

        if (color == true){
            button.setStyle("-fx-background-color: #c4c4c4");
        } else {
            button.setStyle("-fx-background-color:  #808080");
        }

        //button.setStyle("-fx-font-weight: bold");
        button.setPrefHeight(50);
        button.setPrefWidth(240);
        button.setOnMouseClicked(event -> {
            try {
                handleViewChatButton(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return button;
    }

    public Label editLabel(Label label, String username){

        return label;
    }

    public void refreshChatList(List<String> usernames){
        for (int i = 0; i < usernames.size(); i++){
            //System.out.println(id1);
            Button button = new Button();
            Boolean color;
            String username;
            if (i % 2 == 0){
                color = false;
            } else {
                color = true;
            }
            editButton(button, usernames.get(i), color);


            this.chat_list_pane.getChildren().add(button);
        }
    }

    public void refreshColorsOfChats(ObservableList<Node> chats){
        for (Integer i = 0; i < chats.size(); i++){
            if ((i % 2) == 1){
                chats.get(i).setStyle("-fx-background-color: #c4c4c4");
            } else {
                chats.get(i).setStyle("-fx-background-color:  #808080");
            }
        }
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

        /*
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
        */

        List<String> usernames = new ArrayList<>();
        usernames.add("Jano");
        usernames.add("Fero");
        usernames.add("Kubo");
        usernames.add("Lubo");
        usernames.add("Eva");
        usernames.add("Katka");
        usernames.add("Hana");
        usernames.add("Karol");
        usernames.add("Adam");
        usernames.add("Erzika");
        usernames.add("Gizela");
        usernames.add("Gustav");
        usernames.add("Adolf");


        refreshChatList(usernames);


    }

    @FXML
    public void handleViewChatButton(MouseEvent mouseEvent) throws IOException {
        refreshColorsOfChats(this.chat_list_pane.getChildren());

        //metoda na vratenie userov v danych chatoch/groupach
        Button actualButton = ((Button)mouseEvent.getSource());
        //System.out.println("Skuska");
        for (Integer i = 0; i < this.chat_list_pane.getChildren().size(); i++){
            if (actualButton.equals(this.chat_list_pane.getChildren().get(i))){
                this.chat_list_pane.getChildren().get(i).setStyle("-fx-background-color:  #ff9797");
            }
        }

        chat_name.setStyle("-fx-text-fill: #000000");
        chat_name.setText(actualButton.getText());


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
