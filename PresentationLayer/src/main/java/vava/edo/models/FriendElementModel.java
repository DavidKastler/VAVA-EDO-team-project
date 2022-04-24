package vava.edo.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import vava.edo.controllers.FriendElementController;

import java.io.IOException;

public class FriendElementModel {
    private HBox friendElement;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;


    public FriendElementModel(String username){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/FriendElement.fxml"));
            this.friendElement = loader.load();
            FriendElementController controller = loader.getController();
            controller.setModel(this);
            controller.setUserName(username);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public HBox getFriendElement() {
        return friendElement;
    }

}
