package vava.edo.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vava.edo.Exepctions.HttpStatusExceptions.UnexpectedHttpStatusException;
import vava.edo.Handlers.RelationshipHandler;
import vava.edo.Handlers.SearchHandler;
import vava.edo.controllers.models.FriendRequestScreenModel;
import vava.edo.controllers.models.FriendsScreenModel;
import vava.edo.models.FriendElementModel;
import vava.edo.models.Relationship;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.ResourceBundle;


public class FriendsController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField search_field_new_friend;

    @FXML
    private TextField search_friends;

    @FXML
    private VBox users_vbox;

    private FriendsScreenModel model;

    public void setModel(FriendsScreenModel model) {
        this.model = model;
    }

    private MenuScreenController menuScreenController;

    public void setMenuScreenController(MenuScreenController menuScreenController) {
        this.menuScreenController = menuScreenController;
    }



    public FriendsController() {

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

    public void loadFriends() {
        this.model.getUser().setFriends(RelationshipHandler.getAllFriends(this.model.getUser().getUid()));
        reloadFriends();
    }

    public void reloadFriends() {

        users_vbox.getChildren().clear();

        @SuppressWarnings("unchecked")
        List<Relationship> searchedFriends = (List<Relationship>)(List)SearchHandler.searchInList(this.model.getUser().getFriends(), "userName", search_friends.getText());

        for (Integer i = 0; i < this.model.getUser().getFriends().size(); i++){
            try {
                FriendElementModel element = new FriendElementModel(searchedFriends.get(i), this, this.model.getUser());
                HBox hbox = element.getFriendElement();
                users_vbox.getChildren().add(hbox);
            } catch (Exception e) {

            }

        }
    }

    public void handleSearchNewFriend(KeyEvent keyEvent) throws IOException {
        System.out.println(search_field_new_friend.getText());
    }

    public void addFriend() {
        try {
            RelationshipHandler.createFriendRequest(this.model.getUser().getUid(), search_field_new_friend.getText());
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleSearchFriend(KeyEvent keyEvent) throws IOException {
        reloadFriends();
    }

    public void switchRequestsScreen(MouseEvent mouseEvent) {
        this.menuScreenController.gethBoxChangingScreen().getChildren().clear();
        this.menuScreenController.gethBoxChangingScreen().getChildren().add(new FriendRequestScreenModel(this.model.getUser(), this.menuScreenController).getFriendRequestScreen());
    }
}