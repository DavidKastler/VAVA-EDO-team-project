package vava.edo.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import vava.edo.controllers.FriendElementController;
import vava.edo.controllers.FriendRequestController;
import vava.edo.controllers.FriendsController;

import java.io.IOException;

public class FriendElementModel {
    private HBox friendElement;

    private Relationship relationship;

    private User user;

    public User getUser() {
        return user;
    }

    private FriendsController friendsController;

    public FriendsController getFriendsController() {
        return friendsController;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public FriendElementModel(Relationship relationship, FriendsController friendsController, User user){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/FriendElement.fxml"));
            this.friendElement = loader.load();
            FriendElementController controller = loader.getController();
            this.relationship = relationship;
            this.friendsController = friendsController;
            this.user = user;
            controller.setModel(this);
            controller.setUserName(relationship.getUserName());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public HBox getFriendElement() {
        return friendElement;
    }

}
