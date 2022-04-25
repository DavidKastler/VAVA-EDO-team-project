package vava.edo.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import vava.edo.controllers.FriendElementController;
import vava.edo.controllers.FriendsController;

import java.io.IOException;

public class FriendElementModel {
    private HBox friendElement;

    private Relationship relationship;

    private FriendsController friendsController;

    public FriendsController getFriendsController() {
        return friendsController;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public FriendElementModel(Relationship relationship, FriendsController friendsController){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/FriendElement.fxml"));
            this.friendElement = loader.load();
            FriendElementController controller = loader.getController();
            this.relationship = relationship;
            this.friendsController = friendsController;
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
