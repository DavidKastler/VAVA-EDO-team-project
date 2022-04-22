package vava.edo.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import vava.edo.Handlers.TodoHandler;
import vava.edo.controllers.models.TodoScreenModel;
import vava.edo.models.User;

public class MenuScreenController {
    private User user;

    @FXML
    private HBox hBoxChangingScreen;

    public void setUser(User user){this.user = user;}

    /**
     * Overloaded initialize method which serves as constructor in LoginController to pass the logged user
     *
     * @param user Object of user which has logged in to the system
     */
    public void initialize(User user)  {
        setUser(user);
        TodoHandler.startUp(this.user);

        hBoxChangingScreen.getChildren().add(new TodoScreenModel(user).getTodoScreenHBox());
    }
}
