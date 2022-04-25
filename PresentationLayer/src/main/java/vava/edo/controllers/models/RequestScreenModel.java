package vava.edo.controllers.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import vava.edo.controllers.FriendRequestController;
import vava.edo.controllers.FriendsController;
import vava.edo.models.User;

import java.io.IOException;

public class RequestScreenModel {
    private AnchorPane requestScreen;
    private final User user;

    public RequestScreenModel(User user) {
        this.user = user;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/Friends.fxml"));
            this.requestScreen = loader.load();
            FriendRequestController controller = loader.getController();
            controller.setModel(this);
            controller.loadFriendRequests();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AnchorPane getRequestScreen() {
        return requestScreen;
    }

    public User getUser() {
        return user;
    }
}
