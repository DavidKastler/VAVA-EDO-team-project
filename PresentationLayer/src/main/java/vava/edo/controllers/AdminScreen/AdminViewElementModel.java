package vava.edo.controllers.AdminScreen;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import vava.edo.models.User;

import java.io.IOException;
import java.util.ResourceBundle;

public class AdminViewElementModel {
    public HBox getAdminViewElement() {
        return adminViewElement;
    }

    private HBox adminViewElement;

    private User user;

    private User displayedUser;

    private AdminController adminController;

    public AdminController getAdminController() {
        return adminController;
    }

    public User getDisplayedUser() {
        return displayedUser;
    }

    public User getUser() {
        return user;
    }


    public AdminViewElementModel(User user, User displayedUser, AdminController adminController){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/AdminViewElement.fxml"), ResourceBundle.getBundle("Localization Bundle"));
            this.adminViewElement = loader.load();
            AdminViewElementController controller = loader.getController();
            this.user = user;
            this.displayedUser = displayedUser;
            this.adminController = adminController;
            controller.setModel(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
