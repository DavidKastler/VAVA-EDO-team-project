package vava.edo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import vava.edo.Handlers.RefreshUserScreen;
import vava.edo.controllers.models.SelectedUserModel;

public class SelectedUserController {
    private SelectedUserModel model;
    private RefreshUserScreen refresher;

    @FXML
    private Label labelNameTitle;

    @FXML
    private Label labelName;

    @FXML
    private Label labelPassword;

    @FXML
    private Label labelType;

    @FXML
    private VBox vBoxTodos;

    @FXML VBox vBoxFriends;

    public void setModel(SelectedUserModel model) {
        this.model = model;
        this.refresher = new RefreshUserScreen(model.getSelectedUser(), vBoxTodos, vBoxFriends);
        labelNameTitle.setText(model.getSelectedUser().getUsername());
        labelName.setText(model.getSelectedUser().getUsername());
        labelPassword.setText(model.getSelectedUser().getPassword());
        labelType.setText(model.getSelectedUser().getUserRole().getRoleName());

        refresher.initLoader();
    }

    public void handleBackScreen() {
        model.getMenuScreenController().handleAdminButton();
    }

    // TODO spraviť to isté čo má profile window v MenuScreenControlleri
    public void handleShowPassword(MouseEvent mouseEvent) {
    }

}
