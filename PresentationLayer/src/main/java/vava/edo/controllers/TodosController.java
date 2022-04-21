package vava.edo.controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import vava.edo.Handlers.TaskHandler;
import vava.edo.models.User;

import java.net.URL;
import java.util.ResourceBundle;

public class TodosController implements Initializable {
    private User user;

    public void setUser(User user){this.user = user;}

    @FXML
    private Label labelLeftBarAll;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Overloaded initialize method which serves as constructor in LoginController to pass the logged user
     *
     * @param user Object of user which has logged in to the system
     */
    public void initialize(User user)  {
        setUser(user);
        TaskHandler.startUp(this.user);
        labelLeftBarAll.setText(this.user.getUsername());
    }
}
