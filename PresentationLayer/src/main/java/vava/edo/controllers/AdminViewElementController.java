package vava.edo.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import vava.edo.Handlers.UserHandler;
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
    private Button username;

    @FXML
    private Label type_label;



    public Button getUsername_label() {
        return username;
    }
    public Label getType_label() {
        return type_label;
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    public void setType_label(String type_label) {
        this.type_label.setText(type_label);
    }





    public void setModel(AdminViewElementModel model) {
        this.model = model;
        setUsername(this.model.getDisplayedUser().getUsername());
        setType_label(this.model.getDisplayedUser().getUserRole().getRoleName());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void deleteUser(MouseEvent mouseEvent) {
        UserHandler.deleteUser(this.model.getUser().getUid(), this.model.getDisplayedUser().getUid());
        this.model.getAdminController().loadAllUsers();
    }
}
