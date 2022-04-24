package vava.edo.controllers.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import vava.edo.Handlers.Refresh;
import vava.edo.controllers.TodoController;
import vava.edo.models.Todo;

import java.io.IOException;

public class TodoHBoxModel {
    private HBox todoHBox;

    private Todo todo;
    private Refresh refresher;

    public TodoHBoxModel(Todo todo, Refresh refresher) {
        this.todo = todo;
        this.refresher = refresher;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/Todo.fxml"));
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

    public Refresh getRefresher() {
        return refresher;
    }

    public Todo getTodo() {
        return todo;
    }
}
