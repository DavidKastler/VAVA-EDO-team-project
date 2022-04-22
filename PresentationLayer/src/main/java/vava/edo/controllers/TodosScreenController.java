package vava.edo.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import vava.edo.Exepctions.TodoScreen.FailedToCreateTodo;
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
    private TextField textFieldTaskGroup;

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

    @FXML
    private Button buttonEditTodo;

    @FXML
    private Button buttonDeleteTodo;

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

        for(Todo todo : user.getTodos()) {
            vBoxTodos.getChildren().add(new TodoHBoxModel(todo, checkBoxTodoInfo,
                    labelTodoInfoDueTIme, labelTodoInfoName, labelTodoInfoDescription,
                    labelTodoInfoGroup).getTodoHBOx());
            System.out.println("Loaded: " + todo.toString());
        }
    }

    @FXML
    protected void handleAddNewTodo() throws IOException {
        System.out.println("Clicked add new task button");

        // musi byt premazane, lebo ostane to co bolo posledne
        textFieldTaskName.setText("");
        textAreaTaskDescription.setText("");
        textFieldTaskGroup.setText("");
        datePickerTaskFrom.setValue(null);
        datePickerTaskTo.setValue(null);

        vBoxNewTaskScreen.setVisible(true);
        vBoxNewTaskScreen.setDisable(false);


    }

    @FXML
    protected void handleCancelTodo() throws IOException {
        vBoxNewTaskScreen.setVisible(false);
        vBoxNewTaskScreen.setDisable(true);
    }

    @FXML
    protected void handleAcceptTodo() throws IOException {
        try{
            TodoHandler.addTodoToUser(this.user, textFieldTaskName, textAreaTaskDescription,
                    datePickerTaskFrom, datePickerTaskTo, textFieldTaskGroup);
        }
        catch (FailedToCreateTodo e) {
            e.printStackTrace();
        }

        vBoxNewTaskScreen.setVisible(false);
        vBoxNewTaskScreen.setDisable(true);
    }

    public void handleEditTodo() {
        textFieldTaskName.setText(labelTodoInfoName.getText());
        textAreaTaskDescription.setText(labelTodoInfoDescription.getText());
        textFieldTaskGroup.setText(labelTodoInfoGroup.getText());
        // datePickerTaskFrom.
        // datePickerTaskFrom.

        vBoxNewTaskScreen.setVisible(true);
        vBoxNewTaskScreen.setDisable(false);
    }

    public void handleDeleteTodo() {
    }
}
