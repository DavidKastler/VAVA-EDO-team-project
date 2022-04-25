package vava.edo.controllers.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import vava.edo.controllers.AdminController;
import vava.edo.controllers.FriendsController;
import vava.edo.controllers.MenuScreenController;
import vava.edo.models.User;

import java.io.IOException;

public class AdminScreenModel {
    private AnchorPane adminScreen;
    private final User user;

    public AdminScreenModel(User user) {
        this.user = user;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/Admin.fxml"));
            this.adminScreen = loader.load();
            AdminController controller = loader.getController();
            controller.setModel(this);
            controller.loadAllUsers();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AnchorPane getAdminScreen() {
        return adminScreen;
    }

    public User getUser() {
        return user;
    }
}
