package vava.edo.controllers.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import vava.edo.Handlers.RefreshUserScreen;
import vava.edo.controllers.FriendElementAdminController;
import vava.edo.models.Relationship;
import vava.edo.models.User;

import java.io.IOException;

public class FriendElementAdminModel {
    private HBox friendHBox;
    private User user;
    private Relationship friend;
    private RefreshUserScreen refresher;

    public FriendElementAdminModel(RefreshUserScreen refresher, User user, Relationship friend) {
        this.refresher = refresher;
        this.user= user;
        this.friend = friend;

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

    public User getUser() {
        return user;
    }

    public Relationship getFriend() {
        return friend;
    }

    public RefreshUserScreen getRefresher() {
        return refresher;
    }
}
