package vava.edo.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import vava.edo.models.AdminViewElementModel;
import vava.edo.models.FriendElementModel;

import java.net.URL;
import java.util.ResourceBundle;


public class AdminViewElementController implements Initializable {

    private AdminViewElementModel model;

    public AdminViewElementModel getModel() {
        return model;
    }

    @FXML
    private Label username_label;

    @FXML
    private Label type_label;

    @FXML
    private Label date_label;

    public Label getUsername_label() {
        return username_label;
    }
    public Label getType_label() {
        return type_label;
    }
    public Label getDate_label() {
        return date_label;
    }

    public void setModel(AdminViewElementModel model) {
        this.model = model;

        username_label.setText(model.getUsername());
        type_label.setText(model.getType());
        date_label.setText(model.getRegistration());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void deleteUser(MouseEvent mouseEvent) {
        System.out.println(this.getUsername_label().getText() + this.getType_label().getText() + this.getDate_label().getText());
    }
}
