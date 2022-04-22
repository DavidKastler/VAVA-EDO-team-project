package vava.edo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import vava.edo.controllers.models.TodoHBoxModel;

import java.io.IOException;

public class TodoController {
    private TodoHBoxModel model ;

    @FXML
    private CheckBox checkBoxTodo;

    @FXML
    private Label labelTodoName;

    @FXML
    private Label labelTodoGroup;

    @FXML
    private Label labelTodoTime;

    @FXML
    private Button buttonTodo;

    public void setModel(TodoHBoxModel model) {
        checkBoxTodo.setSelected(model.getTodoCompleted());
        labelTodoName.setText(model.getTodoName());
        labelTodoGroup.setText(model.getTodoGroup());
        labelTodoTime.setText(model.getToTime());
        this.model = model;
    }

    public TodoHBoxModel getModel() {
        return model;
    }

    @FXML
    protected void handleTodoClicked() throws IOException {
        System.out.print("Pressed: " + model.getTodo().toString() + "\t->\t");
        model.getCheckBoxTodoInfo().setSelected(model.getTodo().isCompleted());
        model.getLabelTodoInfoDueTIme().setText(model.getTodo().getToTime());
        model.getLabelTodoInfoName().setText(model.getTodo().getTodoName());
        model.getLabelTodoInfoDescription().setText(model.getTodo().getToTime());
        model.getLabelTodoInfoGroup().setText(model.getTodo().getGroupName());
        System.out.println("Todo info was printed");
    }
}
