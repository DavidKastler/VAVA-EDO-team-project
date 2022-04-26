package vava.edo.controllers.AdminScreen;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import vava.edo.controllers.MenuScreen.MenuScreenController;
import vava.edo.models.User;

import java.io.IOException;
import java.util.ResourceBundle;

public class SelectedUserModel {
    private AnchorPane selectedUserScreen;
    private final User currentUser;
    private final User selectedUser;

    private MenuScreenController menuScreenController;

    public SelectedUserModel(User currentUser, User selectedUser, MenuScreenController menuScreenController) {
        this.currentUser = currentUser;
        this.selectedUser = selectedUser;
        this.menuScreenController = menuScreenController;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/SelectedUser.fxml"), ResourceBundle.getBundle("Localization Bundle"));
            this.selectedUserScreen = loader.load();
            SelectedUserController controller = loader.getController();
            controller.setModel(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AnchorPane getSelectedUserScreen() {
        return selectedUserScreen;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public MenuScreenController getMenuScreenController() {
        return menuScreenController;
    }
}
