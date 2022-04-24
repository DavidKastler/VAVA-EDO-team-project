package vava.edo.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import vava.edo.controllers.FriendElementController;
import vava.edo.controllers.FriendReqElementController;

import java.io.IOException;

public class FriendReqElementModel {

    private HBox friendReqElement;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;

    public HBox getFriendReqElement() {
        return friendReqElement;
    }


    public FriendReqElementModel(String username){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/FriendRequestElement.fxml"));
            this.friendReqElement = loader.load();
            FriendReqElementController controller = loader.getController();
            controller.setModel(this);
            controller.setUserName(username);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public HBox getFriendElement() {
        return friendReqElement;
    }

}
