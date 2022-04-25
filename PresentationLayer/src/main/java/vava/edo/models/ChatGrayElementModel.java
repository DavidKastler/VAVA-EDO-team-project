package vava.edo.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import vava.edo.controllers.AdminViewElementController;
import vava.edo.controllers.ChatGrayElementController;

import java.io.IOException;

public class ChatGrayElementModel {
    private HBox messageBox;
    private String messageText;
    private String messageAuthor;
    private String timeSent;

    public HBox getMessageBox() {
        return messageBox;
    }
    public void setMessageBox(HBox messageBox) {
        this.messageBox = messageBox;
    }

    public String getMessageText() {
        return messageText;
    }
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageAuthor() {
        return messageAuthor;
    }
    public void setMessageAuthor(String messageAuthor) {
        this.messageAuthor = messageAuthor;
    }

    public String getTimeSent() {
        return timeSent;
    }
    public void setTimeSent(String timeSent) {
        this.timeSent = timeSent;
    }

    public ChatGrayElementModel(String message, String author, String time){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/ChatGrayElement.fxml"));
            this.messageBox = loader.load();
            ChatGrayElementController controller = loader.getController();
            setMessageText(message);
            setMessageAuthor(author);
            setTimeSent(time);
            controller.setModel(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}