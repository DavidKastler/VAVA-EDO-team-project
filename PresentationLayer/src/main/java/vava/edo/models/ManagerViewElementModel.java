package vava.edo.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import vava.edo.controllers.ManagerViewElementController;

import java.io.IOException;

public class ManagerViewElementModel {

    public HBox getElement() {
        return element;
    }

    public void setElement(HBox element) {
        this.element = element;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private HBox element;
    private String username;
    private String type;
    private String status;



    public ManagerViewElementModel(String username, String type, String status){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/ManagerViewElement.fxml"));
            this.element = loader.load();
            ManagerViewElementController controller = loader.getController();
            setUsername(username);
            setType(type);
            setStatus(status);
            controller.setModel(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
