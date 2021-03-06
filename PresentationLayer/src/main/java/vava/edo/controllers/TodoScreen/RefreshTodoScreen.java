package vava.edo.controllers.TodoScreen;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import vava.edo.Handlers.TodoHandler;
import vava.edo.models.Todo;
import vava.edo.models.User;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class RefreshTodoScreen {
    private User user;
    private VBox vBoxTodos;
    private Todo selectedTodo;
    private ArrayList<Todo> actualGroupTodos;

    private CheckBox checkBoxTodoInfo;
    private Label labelTodoInfoDueTIme;
    private Label labelTodoInfoName;
    private Label labelTodoInfoDescription;
    private Label labelTodoInfoGroup;
    private Button buttonEdit;
    private Button buttonDelete;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("Localization Bundle", Locale.getDefault());

    public RefreshTodoScreen(User user, VBox vBoxTodos, CheckBox checkBoxTodoInfo, Label labelTodoInfoDueTIme,
                             Label labelTodoInfoName, Label labelTodoInfoDescription, Label labelTodoInfoGroup,
                             Button buttonEdit, Button buttonDelete) {
        this.user = user;
        this.vBoxTodos = vBoxTodos;
        this.checkBoxTodoInfo = checkBoxTodoInfo;
        this.labelTodoInfoDueTIme = labelTodoInfoDueTIme;
        this.labelTodoInfoName = labelTodoInfoName;
        this.labelTodoInfoDescription = labelTodoInfoDescription;
        this.labelTodoInfoGroup = labelTodoInfoGroup;
        this.buttonEdit = buttonEdit;
        this.buttonDelete = buttonDelete;
    }

    public void initLoader() {
        actualGroupTodos = user.getTodos();

        for(Todo todo : actualGroupTodos) {
            vBoxTodos.getChildren().add(new TodoHBoxModel(todo, this).getTodoHBOx());
        }

        if(!user.getTodos().isEmpty()) {
            this.selectedTodo = user.getTodos().get(0);
            setInfoSelectedTodo(this.selectedTodo);
        }
        else
            clearTodoInfo();
    }

    public void refreshTodos(int selectedGroup) {
        if(!user.getTodos().isEmpty()) {
            switch (selectedGroup) {
                case 1:
                    actualGroupTodos = user.getTodos();
                    break;
                case 2:
                    actualGroupTodos = TodoHandler.getTodayTodos(user);
                    break;
                case 3:
                    actualGroupTodos = TodoHandler.getTomorrowTodos(user);
                    break;
                case 4:
                    actualGroupTodos = TodoHandler.getCompletedTodos(user);
                    break;
            }

            vBoxTodos.getChildren().clear();

            for(Todo todo : actualGroupTodos) {
                vBoxTodos.getChildren().add(new TodoHBoxModel(todo, this).getTodoHBOx());
            }

            if(actualGroupTodos.size() == 1) {
                this.checkBoxTodoInfo.setVisible(true);
                this.selectedTodo = actualGroupTodos.get(0);
                this.buttonEdit.setVisible(true);
                this.buttonDelete.setVisible(true);
            }

            setInfoSelectedTodo(this.selectedTodo);
        }
    }

    /**
     * Showing first (another) to-do (if exist) from user in info bar, when is to-do deleted
     */
    public void setFirstTodoInfo() {
        if(actualGroupTodos.size() > 0) {
            selectedTodo = actualGroupTodos.get(0);
            setInfoSelectedTodo(selectedTodo);
        }
        else
            clearTodoInfo();
    }

    public void clearTodoInfo() {
        this.checkBoxTodoInfo.setVisible(false);
        this.selectedTodo = null;
        this.checkBoxTodoInfo.setSelected(false);
        this.labelTodoInfoDueTIme.setText("");
        this.labelTodoInfoName.setText(resourceBundle.getString("Todos.emptyList"));
        this.labelTodoInfoDescription.setText("");
        this.labelTodoInfoGroup.setText("");
        this.buttonEdit.setVisible(false);
        this.buttonDelete.setVisible(false);
    }

    public boolean isTodoSelected(Todo todo) {
        return this.selectedTodo == todo;
    }

    public void setInfoSelectedTodo(Todo todo) {
        this.selectedTodo = todo;
        this.checkBoxTodoInfo.setSelected(todo.isCompleted());
        this.labelTodoInfoDueTIme.setText(todo.getToTime());
        this.labelTodoInfoName.setText(todo.getTodoName());
        this.labelTodoInfoDescription.setText(todo.getTodoDescription());
        this.labelTodoInfoGroup.setText(todo.getGroupName());
    }

    public Todo getSelectedTodo() {
        return selectedTodo;
    }

    public void setSelectedTodo(Todo todo) {
        this.selectedTodo = todo;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Todo> getActualGroupTodos() {
        return actualGroupTodos;
    }

    public void setActualGroupTodos(ArrayList<Todo> actualGroupTodos) {
        this.actualGroupTodos = actualGroupTodos;
    }

    @Override
    public String toString() {
        return "Refresh{" +
                "user=" + user +
                ", vBoxTodos=" + vBoxTodos +
                ", selectedTodo=" + selectedTodo +
                ", checkBoxTodoInfo=" + checkBoxTodoInfo +
                ", labelTodoInfoDueTIme=" + labelTodoInfoDueTIme +
                ", labelTodoInfoName=" + labelTodoInfoName +
                ", labelTodoInfoDescription=" + labelTodoInfoDescription +
                ", labelTodoInfoGroup=" + labelTodoInfoGroup +
                '}';
    }
}
