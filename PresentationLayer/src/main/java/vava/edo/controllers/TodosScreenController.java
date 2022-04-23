package vava.edo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import vava.edo.Exepctions.TodoScreen.MandatoryFieldNotInputted;
import vava.edo.Exepctions.TodoScreen.TodoDatabaseFail;
import vava.edo.Handlers.TodoHandler;
import vava.edo.controllers.models.TodoHBoxModel;
import vava.edo.controllers.models.TodoScreenModel;
import vava.edo.models.Todo;


public class TodosScreenController {

    private TodoScreenModel model;
    private Todo selectedTodo;

    @FXML
    private Label labelLeftBarAll;

    @FXML
    private VBox vBoxTodos;

    @FXML
    private VBox vBoxTodoInfo;

    // FXML elements for new to_do window
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
    private Button buttonAcceptTodo;

    // elements for to_do info
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
            System.out.println("Loaded: " + todo);
        }
        this.model = model;
    }

    @FXML
    protected void handleAddNewTodo() {
        System.out.println("Clicked add new task button");

        // Have to be emptied before use
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
    protected void handleCancelTodo() {
        vBoxNewTaskScreen.setVisible(false);
        vBoxNewTaskScreen.setDisable(true);
    }

    @FXML
    protected void handleAcceptTodo() {
        try{
            TodoHandler.addTodoToUser(model.getUser(), textFieldTaskName, textAreaTaskDescription,
                    datePickerTaskFrom, datePickerTaskTo, textFieldTaskGroup);
        }
        catch (MandatoryFieldNotInputted | TodoDatabaseFail e) {
            e.printStackTrace();
        }

        vBoxNewTaskScreen.setVisible(false);
        vBoxNewTaskScreen.setDisable(true);
    }

    public void handleEditTodoInfo() {
        // TODO zmenit nazov new task na edit todo
        textFieldTaskName.setText(labelTodoInfoName.getText());
        textAreaTaskDescription.setText(labelTodoInfoDescription.getText());
        textFieldTaskGroup.setText(labelTodoInfoGroup.getText());
        // datePickerTaskFrom.
        // datePickerTaskFrom.
        // TODO datum to datepickrvo sa naplna cez konstruktor tak sa na to pozri potom Mario
        vBoxNewTaskScreen.setVisible(true);
        vBoxNewTaskScreen.setDisable(false);
        buttonEditTodo.setVisible(true);
        buttonAcceptTodo.setVisible(false);
    }


    /**
     * Handles the delete button, calls a method deleteTodo which deletes the selected to_do
     */
    public void handleDeleteTodo() {

       try {
           TodoHandler.deleteTodo(this.selectedTodo.getTodoId(), model.getUser());
       }
       catch (TodoDatabaseFail e){
           e.printStackTrace();
       }
    }


    /**
     * Handles the edit button, calls a method which creates a PUT request for
     * editing the selected to_do
     */
    public void handleEditTodo() {

        try {
            TodoHandler.editTodo(selectedTodo.getTodoId(), model.getUser(), textFieldTaskName, textAreaTaskDescription,
                    datePickerTaskFrom, datePickerTaskTo, textFieldTaskGroup);
            TodoHandler.getTomorrowTodos(model.getUser());
        }catch (MandatoryFieldNotInputted | TodoDatabaseFail e){
            e.printStackTrace();
        }

        vBoxNewTaskScreen.setVisible(false);
        vBoxNewTaskScreen.setDisable(true);
    }


    /**
     * Setter for Selected to-do
     * @param selectedTodo clicked to-do
     */
    public void setSelectedTodo(Todo selectedTodo) {
        this.selectedTodo = selectedTodo;
    }
}
