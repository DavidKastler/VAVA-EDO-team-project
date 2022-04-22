package vava.edo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import vava.edo.Exepctions.TodoScreen.FailedToCreateTodo;
import vava.edo.Handlers.TodoHandler;
import vava.edo.controllers.models.TodoHBoxModel;
import vava.edo.controllers.models.TodoScreenModel;
import vava.edo.models.Todo;

import java.io.IOException;

public class TodosScreenController {
    // private User user;
    // public void setUser(User user){this.user = user;}

    private TodoScreenModel model;
    private Todo selectedTodoId;

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

    public void setModel(TodoScreenModel model) {
        labelLeftBarAll.setText(model.getUser().getUsername());

        for(Todo todo : model.getUser().getTodos()) {
            vBoxTodos.getChildren().add(new TodoHBoxModel(todo, this, checkBoxTodoInfo,
                    labelTodoInfoDueTIme, labelTodoInfoName, labelTodoInfoDescription,
                    labelTodoInfoGroup).getTodoHBOx());
            System.out.println("Loaded: " + todo.toString());
        }
        this.model = model;
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


        buttonEditTodo.setVisible(false);
        buttonAcceptTodo.setVisible(true);
    }

    @FXML
    protected void handleCancelTodo() throws IOException {
        vBoxNewTaskScreen.setVisible(false);
        vBoxNewTaskScreen.setDisable(true);
    }

    @FXML
    protected void handleAcceptTodo() throws IOException {
        try{
            TodoHandler.addTodoToUser(model.getUser(), textFieldTaskName, textAreaTaskDescription,
                    datePickerTaskFrom, datePickerTaskTo, textFieldTaskGroup);
        }
        catch (FailedToCreateTodo e) {
            e.printStackTrace();
        }

        vBoxNewTaskScreen.setVisible(false);
        vBoxNewTaskScreen.setDisable(true);
    }

    public void handleEditTodoInfo() {
        textFieldTaskName.setText(labelTodoInfoName.getText());
        textAreaTaskDescription.setText(labelTodoInfoDescription.getText());
        textFieldTaskGroup.setText(labelTodoInfoGroup.getText());
        // datePickerTaskFrom.
        // datePickerTaskFrom.

        vBoxNewTaskScreen.setVisible(true);
        vBoxNewTaskScreen.setDisable(false);
        buttonEditTodo.setVisible(true);
        buttonAcceptTodo.setVisible(false);
    }

    public void handleDeleteTodo() {
        System.out.println("Selected todo: " + selectedTodoId.toString());
    }

    public void handleEditTodo(MouseEvent mouseEvent) {

    }

    /**
     * Setter for Selected to-do
     * @param selectedTodoId clicked to-do
     */
    public void setSelectedTodoId(Todo selectedTodoId) {
        this.selectedTodoId = selectedTodoId;
    }
}
