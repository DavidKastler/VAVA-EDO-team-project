package vava.edo.controllers.TodoScreen;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import vava.edo.models.User;

import java.io.IOException;
import java.util.ResourceBundle;

public class TodoScreenModel {
    private AnchorPane todoScreen;
    private final User user;

    public TodoScreenModel(User user) {
        this.user = user;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/Todos.fxml"),
                    ResourceBundle.getBundle("Localization Bundle"));
            this.todoScreen = loader.load();
            TodosScreenController controller = loader.getController();
            controller.setModel(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AnchorPane getTodoScreen() {
        return todoScreen;
    }

    public User getUser() {
        return user;
    }
}
