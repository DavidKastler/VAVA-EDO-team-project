package vava.edo.controllers.AdminScreen;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import vava.edo.Handlers.TodoHandler;
import vava.edo.Handlers.UserHandler;
import vava.edo.models.User;

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

    public void openSelectedUser() {
        User displayedUser = this.model.getDisplayedUser();

        TodoHandler.startUp(displayedUser);

        this.model.getAdminController().getModel().getMenuScreenController().gethBoxChangingScreen().getChildren().clear();
        this.model.getAdminController().getModel().getMenuScreenController().gethBoxChangingScreen().getChildren().
                add(new SelectedUserModel(this.model.getUser(), displayedUser,
                        this.model.getAdminController().getModel().getMenuScreenController()).getSelectedUserScreen());
    }
}
