package vava.edo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vava.edo.Handlers.RelationshipHandler;
import vava.edo.controllers.models.FriendElementAdminModel;

public class FriendElementAdminController {
    private FriendElementAdminModel model;

    @FXML
    private Label labelFriendName;

    public void setModel(FriendElementAdminModel model) {
        this.model = model;
        labelFriendName.setText(model.getFriend().getUserName());
    }

    public void handleDeleteFriend() {
        RelationshipHandler.rejectRequest(model.getUser().getUid(), model.getFriend().getRelationshipId());
        model.getRefresher().refresh();

    }
}
