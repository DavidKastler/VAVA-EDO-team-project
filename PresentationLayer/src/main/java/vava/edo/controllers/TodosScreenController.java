package vava.edo.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import vava.edo.Handlers.TodoHandler;
import vava.edo.controllers.models.TodoHBoxModel;
import vava.edo.models.Todo;
import vava.edo.models.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TodosScreenController implements Initializable {
    private User user;

    public void setUser(User user){this.user = user;}

    @FXML
    private Label labelLeftBarAll;

    @FXML
    private Button buttonAddNewTodo;

    @FXML
    private VBox vBoxTodos;

    @FXML
    private VBox vBoxTodoInfo;

    // Okno pre vytvorenie noveho tasku
    @FXML
    private VBox vBoxNewTaskScreen;

    @FXML
    private VBox vBoxNewTaskWindow;

    @FXML
    private TextField textFieldTaskName;

    @FXML
    private TextArea textAreaTaskDescription;

    @FXML
    private DatePicker datePickerTaskFrom;

    @FXML
    private DatePicker datePickerTaskTo;

    @FXML
    private Button buttonCancelTodo;

    @FXML
    private Button buttonAcceptTodo;



    // elementy pre todo info
    @FXML
    private CheckBox checkBoxTodoInfo;

    @FXML
    private Label labelTodoInfoDueTIme;

    @FXML
    private Label labelTodoInfoName;

    @FXML
    private Label labelTodoInfoDescription;

    @FXML
    private Label labelTodoInfoGroup;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    /**
     * Overloaded initialize method which serves as constructor in LoginController to pass the logged user
     *
     * @param user Object of user which has logged in to the system
     */
    public void initialize(User user)  {
        setUser(user);
        TodoHandler.startUp(this.user);
        labelLeftBarAll.setText(this.user.getUsername());

        for(Todo todo : user.getTasks()) {
            vBoxTodos.getChildren().add(new TodoHBoxModel(todo, checkBoxTodoInfo,
                    labelTodoInfoDueTIme, labelTodoInfoName, labelTodoInfoDescription,
                    labelTodoInfoGroup).getTodoHBOx());
            System.out.println("Loaded: " + todo.toString());
        }
    }

    @FXML
    protected void handleAddNewTodo() throws IOException {

    }

    @FXML
    protected void handleCancelTodo() throws IOException {

    }

    @FXML
    protected void handleAcceptTodo() throws IOException {

    }

    public void handleEditTodo(MouseEvent mouseEvent) {
    }

    public void handleDeleteTodo(MouseEvent mouseEvent) {
    }
}
