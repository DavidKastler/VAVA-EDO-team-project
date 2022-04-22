package vava.edo.controllers.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import vava.edo.controllers.TodoController;
import vava.edo.models.Todo;

import java.io.IOException;

public class TodoHBoxModel {
    private HBox todoHBox;
    private Todo todo;
    // public StringProperty taskName = new SimpleStringProperty();
    private String taskName;
    private String dueTime;

    public TodoHBoxModel(Todo todo) {
        // taskName.setValue(todo.getTaskName());
        taskName = todo.getTaskName();
        dueTime = todo.getDueTime();

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

    public void setTodoHBox(HBox todo) {
        this.todoHBox = todo;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }
}
