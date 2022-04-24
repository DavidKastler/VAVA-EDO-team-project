package vava.edo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import vava.edo.Exepctions.TodoScreen.TodoDatabaseFail;
import vava.edo.Handlers.TodoHandler;
import vava.edo.controllers.models.TodoHBoxModel;

import java.io.IOException;

public class TodoController {
    private TodoHBoxModel model;

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
        checkBoxTodo.setSelected(model.getTodo().isCompleted());
        labelTodoName.setText(model.getTodo().getTodoName());
        labelTodoGroup.setText(model.getTodo().getGroupName());
        labelTodoTime.setText(model.getTodo().getToTime());
        this.model = model;
    }

    @FXML
    protected void handleTodoClicked() throws IOException {
        System.out.print("Pressed: " + model.getTodo().toString() + "\t->\t");
        model.getRefresher().setInfoSelectedTodo(model.getTodo());
        System.out.println("Todo info was printed");
    }

    /**
     * Method which handles the statusChange of to_do
     */
    @FXML
    public void handleStatusChange() {
        try {
            TodoHandler.changeTodoStatus(model.getTodo(), model.getRefresher().getUser().getUserId());
        }catch (TodoDatabaseFail e){
            e.printStackTrace();
        }

        checkBoxTodo.setSelected(model.getTodo().isCompleted());
        if(model.getRefresher().isTodoSelected(model.getTodo())) {
            model.getRefresher().setInfoSelectedTodo(model.getTodo());
        }
    }
}
