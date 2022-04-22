package vava.edo.controllers.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import vava.edo.controllers.TodoController;
import vava.edo.models.Todo;

import java.io.IOException;

public class TodoHBoxModel {
    private HBox todoHBox;

    private Todo todo;
    private boolean todoCompleted;
    private String todoName;
    private String todoGroup;
    private String toTime;

    private CheckBox checkBoxTodoInfo;
    private Label labelTodoInfoDueTIme;
    private Label labelTodoInfoName;
    private Label labelTodoInfoDescription;
    private Label labelTodoInfoGroup;

    public TodoHBoxModel(Todo todo, CheckBox checkBoxTodoInfo, Label labelTodoInfoDueTIme,
                         Label labelTodoInfoName, Label labelTodoInfoDescription,
                         Label labelTodoInfoGroup) {
        // taskName.setValue(todo.getTaskName());
        this.todoCompleted = todo.isCompleted();
        this.todoName = todo.getTodoName();
        this.todoGroup = todo.getGroupName() == null ? "": todo.getGroupName();
        this.toTime = todo.getToTime();

        this.todo = todo;
        this.checkBoxTodoInfo = checkBoxTodoInfo;
        this.labelTodoInfoDueTIme = labelTodoInfoDueTIme;
        this.labelTodoInfoName = labelTodoInfoName;
        this.labelTodoInfoDescription = labelTodoInfoDescription;
        this.labelTodoInfoGroup = labelTodoInfoGroup;

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

    public Boolean getTodoCompleted() {
        return todoCompleted;
    }

    public String getTodoName() {
        return todoName;
    }

    public String getTodoGroup() {
        return todoGroup;
    }

    public String getToTime() {
        return toTime;
    }

    public Todo getTodo() {
        return todo;
    }

    public CheckBox getCheckBoxTodoInfo() {
        return checkBoxTodoInfo;
    }

    public Label getLabelTodoInfoDueTIme() {
        return labelTodoInfoDueTIme;
    }

    public Label getLabelTodoInfoName() {
        return labelTodoInfoName;
    }

    public Label getLabelTodoInfoDescription() {
        return labelTodoInfoDescription;
    }

    public Label getLabelTodoInfoGroup() {
        return labelTodoInfoGroup;
    }
}
