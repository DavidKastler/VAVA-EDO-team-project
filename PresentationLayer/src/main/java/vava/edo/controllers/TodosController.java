package vava.edo.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import vava.edo.models.User;
import vava.edo.models.UserHolder;

import java.net.URL;
import java.util.ResourceBundle;

public class TodosController implements Initializable {
    User user;

    @FXML
    private Label labelLeftBarAll;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserHolder holder = UserHolder.getInstance();
        this.user = holder.getUser();
        labelLeftBarAll.setText(this.user.getUsername());
    }
}