package vava.edo.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import vava.edo.controllers.AdminViewElementController;
import vava.edo.controllers.FriendElementController;

import java.io.IOException;

public class AdminViewElementModel {
    public HBox getAdminViewElement() {
        return adminViewElement;
    }

    public String getUsername() {
        return username;
    }

    public String getType() {
        return type;
    }

    public String getRegistration() {
        return registration;
    }

    public void setAdminViewElement(HBox adminViewElement) {
        this.adminViewElement = adminViewElement;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }


    private HBox adminViewElement;
    private String username;
    private String type;
    private String registration;


    public AdminViewElementModel(String username, String type, String registration){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/AdminViewElement.fxml"));
            this.adminViewElement = loader.load();
            AdminViewElementController controller = loader.getController();
            setUsername(username);
            setType(type);
            setRegistration(registration);
            controller.setModel(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
