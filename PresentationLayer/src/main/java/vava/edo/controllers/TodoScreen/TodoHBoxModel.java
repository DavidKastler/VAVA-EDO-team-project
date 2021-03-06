package vava.edo.controllers.TodoScreen;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import vava.edo.controllers.AdminScreen.RefreshUserScreen;
import vava.edo.models.Todo;

import java.io.IOException;
import java.util.ResourceBundle;

public class TodoHBoxModel {
    private HBox todoHBox;

    private Todo todo;
    private RefreshTodoScreen refresherTodoScreen;
    private RefreshUserScreen refresherUserScreen;


    public TodoHBoxModel(Todo todo, RefreshTodoScreen refresher) {
        this.todo = todo;
        this.refresherTodoScreen = refresher;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/Todo.fxml"),
                    ResourceBundle.getBundle("Localization Bundle"));
            todoHBox = loader.load();
            TodoController controller = loader.getController();
            controller.setModel(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TodoHBoxModel(Todo todo, RefreshUserScreen refresher) {
        this.todo = todo;
        this.refresherUserScreen = refresher;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/Todo.fxml"), ResourceBundle.getBundle("Localization Bundle"));
            todoHBox = loader.load();
            TodoController controller = loader.getController();
            controller.setModel(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HBox getTodoHBOx() {
        return todoHBox;
    }

    public RefreshTodoScreen getRefresherTodoScreen() {
        return refresherTodoScreen;
    }

    public RefreshUserScreen getRefresherUserScreen() {
        return refresherUserScreen;
    }

    public Todo getTodo() {
        return todo;
    }
}
