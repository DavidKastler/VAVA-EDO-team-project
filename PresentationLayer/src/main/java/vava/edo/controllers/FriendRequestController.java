package vava.edo.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vava.edo.Handlers.RelationshipHandler;
import vava.edo.Handlers.SearchHandler;
import vava.edo.models.FriendElementModel;
import vava.edo.models.FriendReqElementModel;
import vava.edo.models.Relationship;

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

    private List<Relationship> requests = null;


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

        this.requests = RelationshipHandler.getAllRequests(5);

        reloadFriendRequests();

    }

    public void reloadFriendRequests() {

        requests_vbox.getChildren().clear();

        @SuppressWarnings("unchecked")
        List<Relationship> searchedFriends = (List<Relationship>)(List) SearchHandler.searchInList(requests, "userName", search_friends.getText());

        for (Integer i = 0; i < requests.size(); i++){
            try {
                FriendElementModel element = new FriendElementModel(searchedFriends.get(i).getUserName());
                HBox hbox = element.getFriendElement();
                requests_vbox.getChildren().add(hbox);
            } catch (Exception e) {

            }

        }
    }

    public void handleSearchFriend(KeyEvent keyEvent) throws IOException {
        reloadFriendRequests();
    }


    public void switchFriendsScreen(MouseEvent mouseEvent) {
    }
}