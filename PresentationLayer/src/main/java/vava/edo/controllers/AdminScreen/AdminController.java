package vava.edo.controllers.AdminScreen;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vava.edo.Handlers.SearchHandler;
import vava.edo.Handlers.UserHandler;
import vava.edo.models.User;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class AdminController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private VBox users_vbox;

    @FXML
    private TextField search_users;

    private AdminScreenModel model;

    public void setModel(AdminScreenModel model) {
        this.model = model;
    }

    public AdminScreenModel getModel() {return this.model; }

    List<User> allRegisteredUsers = null;

    public AdminController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loadAllUsers() {
        this.allRegisteredUsers = UserHandler.getAllUsers(this.model.getUser().getUid());
        reloadAllUsers();
    }

    public void reloadAllUsers() {

        users_vbox.getChildren().clear();
        @SuppressWarnings("unchecked")
        List<User> searchedUsers = (List<User>)(List) SearchHandler.searchInList(allRegisteredUsers, "username", search_users.getText());

        for (Integer i = 0; i < searchedUsers.size(); i++){
            try {
                searchedUsers.get(i).getUserRole().normalizeRoleName();
                AdminViewElementModel element = new AdminViewElementModel(this.model.getUser(), searchedUsers.get(i), this);
                HBox hbox = element.getAdminViewElement();
                users_vbox.getChildren().add(hbox);
            } catch (Exception e) {

            }
        }
    }


    public void handleSearchUsers(KeyEvent keyEvent) throws IOException {
        reloadAllUsers();
    }
}