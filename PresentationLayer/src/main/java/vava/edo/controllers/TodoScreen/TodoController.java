package vava.edo.controllers.TodoScreen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import vava.edo.Exepctions.TodoScreen.TodoDatabaseFail;
import vava.edo.Handlers.TodoHandler;

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
    protected void handleTodoClicked() {
        if(model.getRefresherTodoScreen() != null) {
            model.getRefresherTodoScreen().setInfoSelectedTodo(model.getTodo());
        }
        else {
            model.getRefresherUserScreen().showEditTaskWindow(model.getTodo());
        }
    }

    /**
     * Method which handles the statusChange of to_do
     */
    @FXML
    public void handleStatusChange() {
        if(model.getRefresherTodoScreen() != null) {
            try {
                TodoHandler.changeTodoStatus(model.getTodo(), model.getRefresherTodoScreen().getUser().getUid());
            }catch (TodoDatabaseFail e){
                e.printStackTrace();
            }

            checkBoxTodo.setSelected(model.getTodo().isCompleted());
            if(model.getRefresherTodoScreen().isTodoSelected(model.getTodo())) {
                model.getRefresherTodoScreen().setInfoSelectedTodo(model.getTodo());
            }
        }
        else {
            try {
                TodoHandler.changeTodoStatus(model.getTodo(), model.getRefresherUserScreen().getUser().getUid());
            }catch (TodoDatabaseFail e){
                e.printStackTrace();
            }
        }
    }
}
