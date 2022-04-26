package vava.edo.controllers.FriendScreen;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import vava.edo.models.Relationship;
import vava.edo.models.User;

import java.io.IOException;
import java.util.ResourceBundle;

public class FriendReqElementModel {

    private HBox friendReqElement;

    private Relationship relationship;

    private User user;

    public User getUser() {
        return user;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    private FriendRequestController friendRequestController;

    public FriendRequestController getFriendRequestController() {
        return friendRequestController;
    }

    public HBox getFriendReqElement() {
        return friendReqElement;
    }


    public FriendReqElementModel(Relationship relationship, FriendRequestController friendRequestController, User user){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/FriendRequestElement.fxml"), ResourceBundle.getBundle("Localization Bundle"));
            this.friendReqElement = loader.load();
            FriendReqElementController controller = loader.getController();
            this.relationship = relationship;
            this.friendRequestController = friendRequestController;
            this.user = user;
            controller.setModel(this);
            controller.setUserName(relationship.getUserName());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public HBox getFriendElement() {
        return friendReqElement;
    }

}
