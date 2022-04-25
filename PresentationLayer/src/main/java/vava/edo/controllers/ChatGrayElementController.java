package vava.edo.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import vava.edo.models.AdminViewElementModel;
import vava.edo.models.ChatGrayElementModel;

import java.net.URL;
import java.util.ResourceBundle;


public class ChatGrayElementController implements Initializable {
    private ChatGrayElementModel model;

    @FXML
    private HBox user;

    @FXML
    private Label message;

    @FXML
    private Label username;

    @FXML
    private Label time;

    public ChatGrayElementModel getModel() {
        return model;
    }

    public void setModel(ChatGrayElementModel model) {
        this.model = model;
        this.message.setText(model.getMessageText());
        this.username.setText(model.getMessageAuthor());
        this.time.setText(model.getTimeSent());

    }

    public HBox getUser() {
        return user;
    }
    public void setUser(HBox user) {
        this.user = user;
    }

    public Label getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username.setText(username);
    }

    public Label getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time.setText(time);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
