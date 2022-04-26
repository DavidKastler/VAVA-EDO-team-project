package vava.edo.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vava.edo.Exepctions.AdminWindow.FailedToAcquireRole;
import vava.edo.Exepctions.MenuScreen.FailedToUpdateUser;
import vava.edo.Exepctions.TodoScreen.MandatoryFieldNotInputted;
import vava.edo.Exepctions.TodoScreen.TodoDatabaseFail;
import vava.edo.Handlers.RefreshUserScreen;
import vava.edo.Handlers.TodoHandler;
import vava.edo.Handlers.UserHandler;
import vava.edo.controllers.models.SelectedUserModel;

public class SelectedUserController {
    private SelectedUserModel model;
    private RefreshUserScreen refresher;
    ObservableList<String> typesOfUsers = FXCollections.observableArrayList("admin", "account_manager", "team leader", "pleb", "guest");

    @FXML
    private HBox hBoxUserScreen;

    @FXML
    private Label labelNameTitle;

    @FXML
    private Label labelName;

    @FXML
    private Label labelPassword;

    @FXML
    private Label labelType;

    @FXML
    private Button buttonDeleteProfile;

    @FXML
    private VBox vBoxEditProfileScreen;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private ChoiceBox<String> choiceBoxTypes;

    @FXML
    private VBox vBoxTodos;

    @FXML VBox vBoxFriends;

    @FXML
    private VBox vBoxEditTask;

    @FXML
    private TextField textFieldTaskName;

    @FXML
    private TextField textFieldTaskGroup;

    @FXML
    private TextArea textAreaTaskDescription;

    @FXML
    private DatePicker datePickerTaskFrom;

    @FXML
    private DatePicker datePickerTaskTo;

    public void setModel(SelectedUserModel model) {
        this.model = model;
        this.refresher = new RefreshUserScreen(model.getSelectedUser(), vBoxTodos, vBoxFriends, vBoxEditTask,
                textFieldTaskName, textAreaTaskDescription, textFieldTaskGroup, datePickerTaskFrom, datePickerTaskTo);
        labelNameTitle.setText(model.getSelectedUser().getUsername());
        labelName.setText(model.getSelectedUser().getUsername());
        labelPassword.setText(model.getSelectedUser().getPassword());
        labelType.setText(model.getSelectedUser().getUserRole().getRoleName());

        if(model.getCurrentUser().getUid().intValue() == model.getSelectedUser().getUid().intValue()) {
            buttonDeleteProfile.setVisible(false);
            buttonDeleteProfile.setDisable(true);
        }

        refresher.initLoader();
    }

    public void handleBackScreen() {
        model.getMenuScreenController().handleAdminButton();
    }

    // TODO spraviť to isté čo má profile window v MenuScreenControlleri (David zrobí)
    public void handleShowPassword(MouseEvent mouseEvent) {
    }

    public void handleEditProfile() {
        hBoxUserScreen.setDisable(true);

        textFieldUsername.setText(model.getSelectedUser().getUsername());
        textFieldPassword.setText(model.getSelectedUser().getPassword());
        choiceBoxTypes.setStyle("-fx-font-family: 'Arial'");
        choiceBoxTypes.setValue(model.getSelectedUser().getUserRole().getRoleName());
        choiceBoxTypes.setItems(typesOfUsers);

        vBoxEditProfileScreen.setDisable(false);
        vBoxEditProfileScreen.setVisible(true);
    }

    public void handleDeleteProfile() {
        UserHandler.deleteUser(model.getCurrentUser().getUid(), model.getSelectedUser().getUid());
        handleBackScreen();
    }

    public void handleCancelProfileEdit(MouseEvent mouseEvent) {
        vBoxEditProfileScreen.setDisable(true);
        vBoxEditProfileScreen.setVisible(false);

        hBoxUserScreen.setDisable(false);
    }

    public void handleSaveProfile() {
        int roleId = 0;
        for(int i = 0; i < typesOfUsers.size(); i++) {
            if(choiceBoxTypes.getValue().equals(typesOfUsers.get(i))) {
                roleId = i + 1;
                break;
            }
        }

        try {
            model.getSelectedUser().editUserCred(String.valueOf(roleId), textFieldUsername.getText(), textFieldPassword.getText());
            UserHandler.editUser(model.getSelectedUser(), model.getCurrentUser().getUid());
        }catch (FailedToUpdateUser | MandatoryFieldNotInputted | FailedToAcquireRole e){
            e.printStackTrace();
        }

        vBoxEditProfileScreen.setDisable(true);
        vBoxEditProfileScreen.setVisible(false);
        hBoxUserScreen.setDisable(false);
        refresher.refresh();
        labelNameTitle.setText(model.getSelectedUser().getUsername());
        labelName.setText(model.getSelectedUser().getUsername());
        labelPassword.setText(model.getSelectedUser().getPassword());
        labelType.setText(model.getSelectedUser().getUserRole().getRoleName());
    }

    public void handleCancelTodo() {
        vBoxEditTask.setDisable(true);
        vBoxEditTask.setVisible(false);

        hBoxUserScreen.setDisable(false);
    }

    public void handleEditTodo() {
        try {
            TodoHandler.editTodo(refresher.getSelectedTodo().getTodoId(), model.getSelectedUser(), textFieldTaskName, textAreaTaskDescription,
                    datePickerTaskFrom, datePickerTaskTo, textFieldTaskGroup);
        }catch (MandatoryFieldNotInputted | TodoDatabaseFail e){
            e.printStackTrace();
        }

        vBoxEditTask.setDisable(true);
        vBoxEditTask.setVisible(false);

        refresher.refresh();
    }
}
