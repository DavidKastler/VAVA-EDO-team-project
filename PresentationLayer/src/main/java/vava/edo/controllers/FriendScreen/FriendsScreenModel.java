package vava.edo.controllers.FriendScreen;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import vava.edo.controllers.MenuScreen.MenuScreenController;
import vava.edo.models.User;

import java.io.IOException;
import java.util.ResourceBundle;

public class FriendsScreenModel {
    private AnchorPane friendsScreen;
    private final User user;

    public FriendsScreenModel(User user, MenuScreenController menuScreenController) {
        this.user = user;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/Friends.fxml"),
                    ResourceBundle.getBundle("Localization Bundle"));
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
