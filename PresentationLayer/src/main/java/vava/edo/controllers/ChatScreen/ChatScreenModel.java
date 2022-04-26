package vava.edo.controllers.ChatScreen;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import vava.edo.models.User;

import java.io.IOException;
import java.util.ResourceBundle;

public class ChatScreenModel {
    private AnchorPane chatScreen;
    private final User user;

    public ChatScreenModel(User user) {
        this.user = user;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/Chat.fxml"),
                    ResourceBundle.getBundle("Localization Bundle"));
            this.chatScreen= loader.load();
            ChatController controller = loader.getController();
            controller.setModel(this);
            controller.loadAllGroups();
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
