package vava.edo.controllers.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import vava.edo.controllers.MenuScreenController;
import vava.edo.controllers.SelectedUserController;
import vava.edo.models.User;

import java.io.IOException;

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/SelectedUser.fxml"));
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
