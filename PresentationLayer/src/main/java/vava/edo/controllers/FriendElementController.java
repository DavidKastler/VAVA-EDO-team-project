package vava.edo.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import vava.edo.Handlers.RelationshipHandler;
import vava.edo.models.FriendElementModel;

import java.net.URL;
import java.util.ResourceBundle;


public class FriendElementController implements Initializable {




    @FXML
    private Label label;

    private FriendElementModel model;

    public void setModel(FriendElementModel model){
        this.model = model;
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
        RelationshipHandler.rejectRequest(model.getUser().getUid(), model.getRelationship().getRelationshipId());
        this.model.getFriendsController().loadFriends();

    }

    public void reportFriend(javafx.scene.input.MouseEvent mouseEvent) {
        this.model.getFriendsController().handleReportUserButton(this.model.getRelationship());
    }
}