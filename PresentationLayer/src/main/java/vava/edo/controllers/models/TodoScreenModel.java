package vava.edo.controllers.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import vava.edo.controllers.TodosScreenController;
import vava.edo.models.User;

import java.io.IOException;

public class TodoScreenModel {
    private AnchorPane todoScreen;
    private User user;

    public TodoScreenModel(User user) {
        this.user = user;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/Todos.fxml"));
            this.todoScreen = loader.load();
            TodosScreenController controller = loader.getController();
            controller.setModel(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AnchorPane getTodoScreenHBox() {
        return todoScreen;
    }

    public User getUser() {
        return user;
    }
}
