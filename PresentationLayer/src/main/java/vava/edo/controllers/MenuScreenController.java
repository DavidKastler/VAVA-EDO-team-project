package vava.edo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vava.edo.Exepctions.MenuScreen.FailedToUpdateUser;
import vava.edo.Exepctions.TodoScreen.MandatoryFieldNotInputted;
import vava.edo.Handlers.TodoHandler;
import vava.edo.Handlers.UserHandler;
import vava.edo.controllers.models.CalendarScreenModel;
import vava.edo.controllers.models.TodoScreenModel;
import vava.edo.models.User;

public class MenuScreenController {
    private User user;
    private boolean showPassword = false;

    @FXML
    private HBox hBoxChangingScreen;

    @FXML
    private VBox vBoxProfileScreen;

    @FXML
    private VBox vBoxEditProfileScreen;

    @FXML
    private VBox vBoxAboutScreen;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private VBox vBoxSettingsScreen;

    @FXML
    private Label labelUsername;

    @FXML
    private Label labelPassword;

    public void setUser(User user){this.user = user;}

    /**
     * Overloaded initialize method which serves as constructor in LoginController to pass the logged user
     *
     * @param user Object of user which has logged in to the system
     */
    public void initialize(User user)  {
        setUser(user);
        TodoHandler.startUp(this.user);

        // Preloaded information for edit info
        textFieldUsername.setText(this.user.getUsername());
        textFieldPassword.setText(this.user.getPassword());

        // Preloaded information for user profile labels
        labelUsername.setText(this.user.getUsername());
        labelPassword.setText("********");

        hBoxChangingScreen.getChildren().add(new TodoScreenModel(user).getTodoScreen());
    }

    public void handleUserButton() {
        hBoxChangingScreen.setDisable(true);

        vBoxProfileScreen.setDisable(false);
        vBoxProfileScreen.setVisible(true);
    }

    public void handleShowPassword() {
        if (!this.showPassword){
            labelPassword.setText(this.user.getPassword());
        }else {
            labelPassword.setText("********");
        }
        this.showPassword = !this.showPassword;
    }

    public void handleCancelProfile() {
        vBoxProfileScreen.setDisable(true);
        vBoxProfileScreen.setVisible(false);

        hBoxChangingScreen.setDisable(false);
    }

    public void handleEditProfile() {
        vBoxProfileScreen.setDisable(true);

        vBoxEditProfileScreen.setDisable(false);
        vBoxEditProfileScreen.setVisible(true);
    }

    public void handleCancelProfileEdit() {
        vBoxEditProfileScreen.setDisable(true);
        vBoxEditProfileScreen.setVisible(false);

        vBoxProfileScreen.setDisable(false);
    }

    /**
     * This method is called upon the save edit profile button and on press will update the profile setting
     */
    public void handleSaveProfile() {

        try {
            user.updateUserCred(textFieldUsername.getText(), textFieldPassword.getText());
            UserHandler.editUser(user);
        }catch (FailedToUpdateUser | MandatoryFieldNotInputted e){
            e.printStackTrace();
        }

        vBoxEditProfileScreen.setDisable(true);
        vBoxEditProfileScreen.setVisible(false);

        vBoxProfileScreen.setDisable(false);
    }

    public void handleTodosButton() {
        hBoxChangingScreen.getChildren().clear();
        hBoxChangingScreen.getChildren().add(new TodoScreenModel(user).getTodoScreen());
    }

    public void handleCalendarButton() {
        hBoxChangingScreen.getChildren().clear();
        hBoxChangingScreen.getChildren().add(new CalendarScreenModel(user).getCalendarScreen());
    }

    public void handleFriendsButton() {
    }

    public void handleAdminButton() {
    }

    public void handleSettingsButton() {
        hBoxChangingScreen.setDisable(true);

        vBoxSettingsScreen.setDisable(false);
        vBoxSettingsScreen.setVisible(true);
    }

    public void handleCancelSettings() {
        vBoxSettingsScreen.setDisable(true);
        vBoxSettingsScreen.setVisible(false);

        hBoxChangingScreen.setDisable(false);
    }

    public void handleAbout() {
        vBoxSettingsScreen.setDisable(true);
        vBoxSettingsScreen.setVisible(false);

        vBoxAboutScreen.setDisable(false);
        vBoxAboutScreen.setVisible(true);
    }

    public void handleFeedback() {
    }

    public void handleLogout() {
    }

    public void handleCancelAbout() {
        vBoxAboutScreen.setDisable(true);
        vBoxAboutScreen.setVisible(false);

        vBoxSettingsScreen.setDisable(false);
        vBoxSettingsScreen.setVisible(true);
    }
}
