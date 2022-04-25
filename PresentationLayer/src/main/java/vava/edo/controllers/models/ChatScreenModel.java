package vava.edo.controllers.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import vava.edo.controllers.AdminController;
import vava.edo.controllers.ChatController;
import vava.edo.models.User;

import java.io.IOException;

public class ChatScreenModel {
    private AnchorPane chatScreen;
    private final User user;

    public ChatScreenModel(User user) {
        this.user = user;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/Chat.fxml"));
            this.chatScreen= loader.load();
            ChatController controller = loader.getController();
            controller.setModel(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AnchorPane getChatScreen() {
        return chatScreen;
    }

    public User getUser() {
        return user;
    }
}
