package vava.edo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import vava.edo.controllers.models.TodoHBoxModel;

public class TodoController {
    private TodoHBoxModel model ;

    @FXML
    private Label labelTodoName;

    @FXML
    private Label labelTodoTime;

    @FXML
    private Button buttonTodo;

    public void setModel(TodoHBoxModel model) {
        // labelTodoName.textProperty().bind(model.taskName);
        labelTodoName.setText(model.getTaskName());
        labelTodoTime.setText(model.getDueTime());
        this.model = model;
    }

    public TodoHBoxModel getModel() {
        return model;
    }
}
