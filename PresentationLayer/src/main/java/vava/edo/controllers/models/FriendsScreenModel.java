package vava.edo.controllers.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import vava.edo.controllers.FriendsController;
import vava.edo.controllers.MenuScreenController;
import vava.edo.controllers.TodosScreenController;
import vava.edo.models.User;

import java.io.IOException;

public class FriendsScreenModel {
    private AnchorPane friendsScreen;
    private final User user;

    public FriendsScreenModel(User user, MenuScreenController menuScreenController) {
        this.user = user;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/Friends.fxml"));
            this.friendsScreen = loader.load();
            FriendsController controller = loader.getController();
            controller.setModel(this);
            controller.setMenuScreenController(menuScreenController);
            controller.loadFriends();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AnchorPane getFriendsScreen() {
        return friendsScreen;
    }

    public User getUser() {
        return user;
    }
}
