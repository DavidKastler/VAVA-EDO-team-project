package vava.edo.controllers.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import vava.edo.controllers.ManagerController;
import vava.edo.controllers.MenuScreenController;
import vava.edo.models.User;

import java.io.IOException;

public class ManagerScreenModel {
    private AnchorPane managerScreen;
    private final User user;
    private final MenuScreenController menuScreenController;

    public ManagerScreenModel(User user, MenuScreenController menuScreenController) {
        this.user = user;
        this.menuScreenController = menuScreenController;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/Manager.fxml"));
            this.managerScreen = loader.load();
            ManagerController controller = loader.getController();
            controller.setModel(this);
            controller.loadReports(false);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AnchorPane getManagerScreen() {
        return managerScreen;
    }

    public User getUser() {
        return user;
    }

    public MenuScreenController getMenuScreenController() { return this.menuScreenController; }
}
