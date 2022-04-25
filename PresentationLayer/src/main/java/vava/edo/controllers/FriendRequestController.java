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
import vava.edo.controllers.models.FriendRequestScreenModel;
import vava.edo.controllers.models.FriendsScreenModel;
import vava.edo.models.FriendReqElementModel;
import vava.edo.models.Relationship;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.ResourceBundle;


public class FriendRequestController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField search_friends;

    @FXML
    private VBox requests_vbox;

    private FriendRequestScreenModel model;

    public void setModel(FriendRequestScreenModel model) {
        this.model = model;
    }

    private MenuScreenController menuScreenController;

    public void setMenuScreenController(MenuScreenController menuScreenController) {
        this.menuScreenController = menuScreenController;
    }

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

    }

    public void loadFriendRequests() {
        this.model.getUser().setFriendRequests(RelationshipHandler.getAllRequests(this.model.getUser().getUid()));
        reloadFriendRequests();
    }

    public void reloadFriendRequests() {

        requests_vbox.getChildren().clear();

        @SuppressWarnings("unchecked")
        List<Relationship> searchedFriends = (List<Relationship>)(List) SearchHandler.searchInList(this.model.getUser().getFriendRequests(), "userName", search_friends.getText());

        for (Integer i = 0; i < this.model.getUser().getFriendRequests().size(); i++){
            try {
                FriendReqElementModel element = new FriendReqElementModel(searchedFriends.get(i), this, this.model.getUser());
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
        this.menuScreenController.gethBoxChangingScreen().getChildren().clear();
        this.menuScreenController.gethBoxChangingScreen().getChildren().add(new FriendsScreenModel(this.model.getUser(), this.menuScreenController).getFriendsScreen());
    }
}