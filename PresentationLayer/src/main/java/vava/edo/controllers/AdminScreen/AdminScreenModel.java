package vava.edo.controllers.AdminScreen;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import vava.edo.controllers.MenuScreen.MenuScreenController;
import vava.edo.models.User;

import java.io.IOException;
import java.util.ResourceBundle;

public class AdminScreenModel {
    private AnchorPane adminScreen;
    private final User user;
    private final MenuScreenController menuScreenController;

    public MenuScreenController getMenuScreenController() {
        return this.menuScreenController;
    };

    public AdminScreenModel(User user, MenuScreenController menuScreenController) {
        this.user = user;
        this.menuScreenController = menuScreenController;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/Admin.fxml"),
                    ResourceBundle.getBundle("Localization Bundle"));
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
