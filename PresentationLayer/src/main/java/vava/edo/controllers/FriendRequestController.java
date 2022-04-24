package vava.edo.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vava.edo.models.FriendElementModel;
import vava.edo.models.FriendReqElementModel;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class FriendRequestController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField search_friends;

    @FXML
    private VBox requests_vbox;


    public FriendRequestController() {

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

        for (Integer i = 0; i < usernames.size(); i++){
            FriendReqElementModel element = new FriendReqElementModel(usernames.get(i));
            HBox hbox = element.getFriendReqElement();
            requests_vbox.getChildren().add(hbox);
        }

    }

    public void handleSearchFriend(KeyEvent keyEvent) throws IOException {
        System.out.println(search_friends.getText());
    }


}