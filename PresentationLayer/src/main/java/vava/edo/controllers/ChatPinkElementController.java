package vava.edo.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import vava.edo.models.ChatGrayElementModel;
import vava.edo.models.ChatPinkElementModel;

import java.net.URL;
import java.util.ResourceBundle;


public class ChatPinkElementController implements Initializable {
    private ChatPinkElementModel model;

    @FXML
    private HBox user;

    @FXML
    private Label message;

    @FXML
    private Label username;

    @FXML
    private Label time;

    public ChatPinkElementModel getModel() {
        return model;
    }

    public void setModel(ChatPinkElementModel model) {
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
