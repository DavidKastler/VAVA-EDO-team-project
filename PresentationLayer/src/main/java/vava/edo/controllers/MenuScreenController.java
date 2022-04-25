package vava.edo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vava.edo.Exepctions.MenuScreen.FailedToUpdateUser;
import vava.edo.Exepctions.TodoScreen.MandatoryFieldNotInputted;
import vava.edo.Handlers.TodoHandler;
import vava.edo.Handlers.UserHandler;
import vava.edo.controllers.models.*;
import vava.edo.models.User;

import java.io.IOException;

import java.util.SimpleTimeZone;

public class MenuScreenController {
    private User user;
    private boolean showPassword = false;
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button buttonUser;

    @FXML
    private Button buttonTodos;

    @FXML
    private Button buttonCalendar;

    @FXML
    private Button buttonChat;

    @FXML
    private Button buttonFriends;

    @FXML
    private Button buttonAdmin;

    @FXML
    private Button buttonSettings;

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

    public HBox gethBoxChangingScreen() {
        return hBoxChangingScreen;
    }

    /**
     * Overloaded initialize method which serves as constructor in LoginController to pass the logged user
     *
     * @param user Object of user which has logged in to the system
     */
    public void initialize(User user, AnchorPane rootPane)  {
        setUser(user);
        this.rootPane = rootPane;
        TodoHandler.startUp(this.user);

        // Preloaded information for edit info
        textFieldUsername.setText(this.user.getUsername());
        textFieldPassword.setText(this.user.getPassword());

        // Preloaded information for user profile labels
        labelUsername.setText(this.user.getUsername());
        labelPassword.setText("********");

        hBoxChangingScreen.getChildren().add(new TodoScreenModel(user).getTodoScreen());

        this.highlightButton("Todos");
    }

    public void handleUserButton() {
        hBoxChangingScreen.setDisable(true);

        vBoxProfileScreen.setDisable(false);
        vBoxProfileScreen.setVisible(true);

        this.highlightButton("User");
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

        // Changing the label to appropriate username
        labelUsername.setText(this.user.getUsername());

        vBoxEditProfileScreen.setDisable(true);
        vBoxEditProfileScreen.setVisible(false);

        vBoxProfileScreen.setDisable(false);
    }

    public void handleTodosButton() {
        hBoxChangingScreen.getChildren().clear();
        hBoxChangingScreen.getChildren().add(new TodoScreenModel(user).getTodoScreen());

        this.highlightButton("Todos");
    }

    public void handleCalendarButton() {
        hBoxChangingScreen.getChildren().clear();
        hBoxChangingScreen.getChildren().add(new CalendarScreenModel(user).getCalendarScreen());

        this.highlightButton("Calendar");
    }

    public void handleChatButton() {
        hBoxChangingScreen.getChildren().clear();
        hBoxChangingScreen.getChildren().add(new ChatScreenModel(user).getChatScreen());

        this.highlightButton("Chat");

    }

    public void handleFriendsButton() {

        this.highlightButton("Friends");

        hBoxChangingScreen.getChildren().clear();
        hBoxChangingScreen.getChildren().add(new FriendsScreenModel(user, this).getFriendsScreen());
    }

    public void handleAdminButton() {
        if (this.user.getUserRole().getRoleName().equals("admin")) {
            hBoxChangingScreen.getChildren().clear();
            hBoxChangingScreen.getChildren().add(new AdminScreenModel(user, this).getAdminScreen());
            this.highlightButton("Admin");
        }
        else if (this.user.getUserRole().getRoleName().equals("account_manager")) {
            hBoxChangingScreen.getChildren().clear();
            hBoxChangingScreen.getChildren().add(new ManagerScreenModel(user, this).getManagerScreen());
            this.highlightButton("Admin");
        }
    }

    public void handleSettingsButton() {
        hBoxChangingScreen.setDisable(true);

        vBoxSettingsScreen.setDisable(false);
        vBoxSettingsScreen.setVisible(true);

        this.highlightButton("Settings");
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
        try {
            AnchorPane loginScreen = FXMLLoader.load(getClass().getResource("/vava/edo/Login.fxml"));

            rootPane.getChildren().setAll(loginScreen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleCancelAbout() {
        vBoxAboutScreen.setDisable(true);
        vBoxAboutScreen.setVisible(false);

        vBoxSettingsScreen.setDisable(false);
        vBoxSettingsScreen.setVisible(true);
    }

    /**
     * Method which highlights the selected button
     *
     * @param buttonName name of the button which is going to be highlighted
     */
    public void highlightButton(String buttonName){

        buttonUser.setStyle(buttonName.equals("User") ? "-fx-background-color: #006DAB" : "-fx-background-color: transparent");
        buttonTodos.setStyle(buttonName.equals("Todos") ? "-fx-background-color: #006DAB" : "-fx-background-color: transparent");
        buttonCalendar.setStyle(buttonName.equals("Calendar") ? "-fx-background-color: #006DAB" : "-fx-background-color: transparent");
        buttonChat.setStyle(buttonName.equals("Chat") ? "-fx-background-color: #006DAB" : "-fx-background-color: transparent");
        buttonFriends.setStyle(buttonName.equals("Friends") ? "-fx-background-color: #006DAB" : "-fx-background-color: transparent");
        buttonAdmin.setStyle(buttonName.equals("Admin") ? "-fx-background-color: #006DAB" : "-fx-background-color: transparent");
        buttonSettings.setStyle(buttonName.equals("Settings") ? "-fx-background-color: #006DAB" : "-fx-background-color: transparent");
    }
}
