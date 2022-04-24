package vava.edo.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import vava.edo.controllers.AdminViewElementController;
import vava.edo.controllers.SpravcaViewElementController;

import java.io.IOException;

public class SpravcaViewElementModel {

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

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getReported() {
        return reported;
    }

    public void setReported(String reported) {
        this.reported = reported;
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
    private String registration;
    private String reported;
    private String status;



    public SpravcaViewElementModel(String username, String type, String registration, String reported, String status){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/SpravcaViewElement.fxml"));
            this.element = loader.load();
            SpravcaViewElementController controller = loader.getController();
            setUsername(username);
            setType(type);
            setRegistration(registration);
            setReported(reported);
            setStatus(status);
            controller.setModel(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
