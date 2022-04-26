package vava.edo.controllers.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import vava.edo.controllers.FriendElementAdminController;

import java.io.IOException;

public class FriendElementAdminModel {
    private HBox friendHBox;
    private String friendName;

    public FriendElementAdminModel(String friendName) {
        this.friendName = friendName;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/FriendElementAdmin.fxml"));
            this.friendHBox = loader.load();
            FriendElementAdminController controller = loader.getController();
            controller.setModel(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HBox getFriendHBox() {
        return friendHBox;
    }

    public String getFriendName() {
        return friendName;
    }
}
