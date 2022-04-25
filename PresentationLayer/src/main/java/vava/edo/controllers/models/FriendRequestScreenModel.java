package vava.edo.controllers.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import vava.edo.controllers.FriendRequestController;
import vava.edo.controllers.MenuScreenController;
import vava.edo.models.User;

import java.io.IOException;

public class FriendRequestScreenModel {
    private AnchorPane requestScreen;
    private final User user;

    public FriendRequestScreenModel(User user, MenuScreenController menuScreenController) {
        this.user = user;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/FriendRequests.fxml"));
            this.requestScreen = loader.load();
            FriendRequestController controller = loader.getController();
            controller.setModel(this);
            controller.setMenuScreenController(menuScreenController);
            controller.loadFriendRequests();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AnchorPane getFriendRequestScreen() {
        return requestScreen;
    }

    public User getUser() {
        return user;
    }
}
