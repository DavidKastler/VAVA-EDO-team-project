package vava.edo.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.w3c.dom.events.MouseEvent;
import vava.edo.models.FriendElementModel;
import vava.edo.models.FriendReqElementModel;

import java.net.URL;
import java.util.ResourceBundle;


public class FriendElementController implements Initializable {




    @FXML
    private Label label;

    private FriendElementModel modelElem;

    public void setModel(FriendElementModel model){
        this.modelElem = model;
        //label.setText(model.getUsername());
    }

    public void setUserName(String username){
        setLabel(username);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setLabel(String text) {
        this.label.setText(text);
    }

    public Label getLabel() {
        return label;
    }




    public void deleteFriend(javafx.scene.input.MouseEvent mouseEvent) {
        System.out.println(getLabel().getText());
    }

    public void reportFriend(javafx.scene.input.MouseEvent mouseEvent) {
        System.out.println(this.getLabel().getText());
    }
}