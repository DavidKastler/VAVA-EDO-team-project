package vava.edo.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vava.edo.Handlers.TodoHandler;
import vava.edo.controllers.models.CalendarScreenModel;
import vava.edo.controllers.models.TodoScreenModel;
import vava.edo.models.User;

public class MenuScreenController {
    private User user;

    @FXML
    private HBox hBoxChangingScreen;

    @FXML
    private VBox vBoxProfileScreen;

    @FXML
    private VBox vBoxEditProfileScreen;

    @FXML
    private VBox vBoxSettingsScreen;

    public void setUser(User user){this.user = user;}

    /**
     * Overloaded initialize method which serves as constructor in LoginController to pass the logged user
     *
     * @param user Object of user which has logged in to the system
     */
    public void initialize(User user)  {
        setUser(user);
        TodoHandler.startUp(this.user);

        hBoxChangingScreen.getChildren().add(new TodoScreenModel(user).getTodoScreen());
    }

    public void handleUserButton(MouseEvent mouseEvent) {
        hBoxChangingScreen.setDisable(true);

        vBoxProfileScreen.setDisable(false);
        vBoxProfileScreen.setVisible(true);
    }

    public void handleShowPassword(MouseEvent mouseEvent) {
    }

    public void handleCancelProfile(MouseEvent mouseEvent) {
        vBoxProfileScreen.setDisable(true);
        vBoxProfileScreen.setVisible(false);

        hBoxChangingScreen.setDisable(false);
    }

    public void handleEditProfile(MouseEvent mouseEvent) {
        vBoxProfileScreen.setDisable(true);

        vBoxEditProfileScreen.setDisable(false);
        vBoxEditProfileScreen.setVisible(true);
    }

    public void handleCancelProfileEdit(MouseEvent mouseEvent) {
        vBoxEditProfileScreen.setDisable(true);
        vBoxEditProfileScreen.setVisible(false);

        vBoxProfileScreen.setDisable(false);
    }

    public void handleSaveProfile(MouseEvent mouseEvent) {
    }

    public void handleTodosButton(MouseEvent mouseEvent) {
        hBoxChangingScreen.getChildren().clear();
        hBoxChangingScreen.getChildren().add(new TodoScreenModel(user).getTodoScreen());
    }

    public void handleCalendarButton(MouseEvent mouseEvent) {
        hBoxChangingScreen.getChildren().clear();
        hBoxChangingScreen.getChildren().add(new CalendarScreenModel(user).getCalendarScreen());
    }

    public void handleFriendsButton(MouseEvent mouseEvent) {
    }

    public void handleAdminButton(MouseEvent mouseEvent) {
    }

    public void handleSettingsButton(MouseEvent mouseEvent) {
        hBoxChangingScreen.setDisable(true);

        vBoxSettingsScreen.setDisable(false);
        vBoxSettingsScreen.setVisible(true);
    }

    public void handleCancelSettings(MouseEvent mouseEvent) {
        vBoxSettingsScreen.setDisable(true);
        vBoxSettingsScreen.setVisible(false);

        hBoxChangingScreen.setDisable(false);
    }

    public void handleAbout(MouseEvent mouseEvent) {
    }

    public void handleFeedback(MouseEvent mouseEvent) {
    }

    public void handleLogout(MouseEvent mouseEvent) {
    }
}
