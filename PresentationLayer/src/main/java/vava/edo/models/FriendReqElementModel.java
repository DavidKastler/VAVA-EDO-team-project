package vava.edo.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import vava.edo.controllers.FriendElementController;
import vava.edo.controllers.FriendReqElementController;

import java.io.IOException;

public class FriendReqElementModel {

    private HBox friendReqElement;

    private Relationship relationship;

    public Relationship getRelationship() {
        return relationship;
    }

    public HBox getFriendReqElement() {
        return friendReqElement;
    }


    public FriendReqElementModel(Relationship relationship){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/FriendRequestElement.fxml"));
            this.friendReqElement = loader.load();
            FriendReqElementController controller = loader.getController();
            this.relationship = relationship;
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
