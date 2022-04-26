package vava.edo.Handlers;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import vava.edo.controllers.models.FriendElementAdminModel;
import vava.edo.controllers.models.TodoHBoxModel;
import vava.edo.models.Relationship;
import vava.edo.models.Todo;
import vava.edo.models.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RefreshUserScreen {
    private User user;
    private VBox vBoxTodos;
    private VBox vBoxFriends;
    private VBox vBoxEditTask;
    private Todo selectedTodo;


    private TextField textFieldTaskName;
    private TextField textFieldTaskGroup;
    private TextArea textAreaTaskDescription;
    private DatePicker datePickerTaskFrom;
    private DatePicker datePickerTaskTo;

    public RefreshUserScreen(User user, VBox vBoxTodos, VBox vBoxFriends, VBox vBoxEditTask, TextField textFieldTaskName,
                             TextArea textAreaTaskDescription, TextField textFieldTaskGroup,
                             DatePicker datePickerTaskFrom, DatePicker datePickerTaskTo) {
        this.user = user;
        this.vBoxTodos = vBoxTodos;
        this.vBoxFriends = vBoxFriends;
        this.vBoxEditTask = vBoxEditTask;
        this.textFieldTaskName = textFieldTaskName;
        this.textFieldTaskGroup = textFieldTaskGroup;
        this.textAreaTaskDescription = textAreaTaskDescription;
        this.datePickerTaskFrom = datePickerTaskFrom;
        this.datePickerTaskTo = datePickerTaskTo;
    }

    public void initLoader() {
        refresh();
    }

    public void refresh() {
        vBoxTodos.getChildren().clear();
        vBoxFriends.getChildren().clear();

        for(Todo todo : user.getTodos()) {
            vBoxTodos.getChildren().add(new TodoHBoxModel(todo, this).getTodoHBOx());
        }

        for(Relationship relationship : RelationshipHandler.getAllFriends(user.getUid())) {
            vBoxFriends.getChildren().add(new FriendElementAdminModel(this, user, relationship).getFriendHBox());
        }
    }

    public User getUser() {
        return user;
    }

    public void showEditTaskWindow(Todo todo) {
        this.selectedTodo = todo;
        vBoxEditTask.setVisible(true);
        vBoxEditTask.setDisable(false);

        textFieldTaskName.setText(todo.getTodoName());
        textAreaTaskDescription.setText(todo.getTodoDescription());
        textFieldTaskGroup.setText(todo.getGroupName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fromDate = LocalDate.parse(todo.getFromTime(), formatter);
        LocalDate toDate = LocalDate.parse(todo.getToTime(), formatter);
        datePickerTaskFrom.setValue(fromDate);
        datePickerTaskTo.setValue(toDate);
    }

    public Todo getSelectedTodo() {
        return selectedTodo;
    }
}
