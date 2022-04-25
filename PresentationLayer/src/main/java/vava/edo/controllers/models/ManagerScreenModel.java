package vava.edo.controllers.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import vava.edo.controllers.ManagerController;
import vava.edo.models.User;

import java.io.IOException;

public class ManagerScreenModel {
    private AnchorPane managerScreen;
    private final User user;

    public ManagerScreenModel(User user) {
        this.user = user;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/Admin.fxml"));
            this.managerScreen = loader.load();
            ManagerController controller = loader.getController();
            controller.setModel(this);
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
}
