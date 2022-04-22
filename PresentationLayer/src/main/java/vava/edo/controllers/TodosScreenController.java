package vava.edo.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import vava.edo.Handlers.TaskHandler;
import vava.edo.controllers.models.TodoHBoxModel;
import vava.edo.models.Todo;
import vava.edo.models.User;

import java.net.URL;
import java.util.ResourceBundle;

public class TodosScreenController implements Initializable {
    private User user;

    public void setUser(User user){this.user = user;}

    @FXML
    private Label labelLeftBarAll;

    @FXML
    private VBox vBoxTodos;

    @FXML
    private VBox vBoxTodoInfo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    /**
     * Overloaded initialize method which serves as constructor in LoginController to pass the logged user
     *
     * @param user Object of user which has logged in to the system
     */
    public void initialize(User user)  {
        setUser(user);
        TaskHandler.startUp(this.user);
        labelLeftBarAll.setText(this.user.getUsername());

        /*for(int i = 0; i < 5; i++) {
            Todo newTodo = new Todo();
            newTodo.setTaskName("Ahoj: " + i);
            vBoxTodos.getChildren().add(new TodoHBoxModel(newTodo).getTodoHBOx());
        }*/

        for(Todo todo : user.getTasks()) {
            System.out.println("Adding: " + todo.toString());
            vBoxTodos.getChildren().add(new TodoHBoxModel(todo).getTodoHBOx());
        }
    }
}
