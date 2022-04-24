package vava.edo.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import vava.edo.models.SpravcaViewElementModel;

import java.net.URL;
import java.util.ResourceBundle;


public class SpravcaViewElementController implements Initializable {
    public Label getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    public Label getType() {
        return type;
    }

    public void setType(String type) {
        this.type.setText(type);
    }

    public Label getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration.setText(registration);
    }

    public Label getReported() {
        return reported;
    }

    public void setReported(String reported) {
        this.reported.setText(reported);
    }




    @FXML
    private Label username;
    @FXML
    private Label type;
    @FXML
    private Label registration;
    @FXML
    private Label reported;


    private SpravcaViewElementModel model;

    public SpravcaViewElementModel getModel() {
        return model;
    }





    public void setModel(SpravcaViewElementModel model) {
        this.model = model;

        setUsername(model.getUsername());
        setType(model.getType());
        setRegistration(model.getRegistration());
        setReported(model.getReported());



    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
