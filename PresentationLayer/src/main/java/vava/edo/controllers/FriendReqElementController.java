package vava.edo.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import vava.edo.Handlers.RelationshipHandler;
import vava.edo.models.FriendElementModel;
import vava.edo.models.FriendReqElementModel;

import java.net.URL;
import java.util.ResourceBundle;


public class FriendReqElementController implements Initializable {




    @FXML
    private Label label;

    private FriendReqElementModel model;

    public void setModel(FriendReqElementModel model){
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
        this.model.getFriendRequestController().loadFriendRequests();
    }

    public void blockFriend(javafx.scene.input.MouseEvent mouseEvent) {
        RelationshipHandler.blockUser(model.getUser().getUid(), model.getRelationship().getRelationshipId());
        this.model.getFriendRequestController().loadFriendRequests();
    }

    public void acceptFriend(javafx.scene.input.MouseEvent mouseEvent) {
        RelationshipHandler.acceptRequest(model.getUser().getUid(), model.getRelationship().getRelationshipId());
        this.model.getFriendRequestController().loadFriendRequests();
    }
}