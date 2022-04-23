package vava.edo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import vava.edo.Exepctions.TodoScreen.MandatoryFieldNotInputted;
import vava.edo.Exepctions.TodoScreen.TodoDatabaseFail;
import vava.edo.Handlers.Refresh;
import vava.edo.Handlers.TodoHandler;
import vava.edo.controllers.models.TodoScreenModel;


public class TodosScreenController {

    private TodoScreenModel model;
    private Refresh refresher;

    // FXML elements for to-dos sort handeling
    @FXML
    private Button buttonAllTodos;

    @FXML
    private Button buttonTodayTodos;

    @FXML
    private Button buttonTomorrowTodos;

    @FXML
    private Button buttonCompletedTodos;



    @FXML
    private Label labelTodoGroupName;

    @FXML
    private VBox vBoxTodos;

    // FXML elements for new to_do window
    @FXML
    private VBox vBoxNewTaskScreen;

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


    // elements for to-do info
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
    private Button buttonEditTodoInfo;

    @FXML
    private Button buttonDeleteTodo;

    @FXML
    private Button buttonEditTodo;

    public void setModel(TodoScreenModel model) {
        this.model = model;
        this.refresher = new Refresh(model.getUser(), vBoxTodos, checkBoxTodoInfo, labelTodoInfoDueTIme,
                labelTodoInfoName, labelTodoInfoDescription, labelTodoInfoGroup, buttonEditTodoInfo, buttonDeleteTodo);

        refresher.initLoader();
    }

    public void handleAllTodos() {
        labelTodoGroupName.setText("All");
        refresher.setActualGroupTodos(model.getUser().getTodos());
        refresher.refreshTodos();
    }

    public void handleTodayTodos() {
        labelTodoGroupName.setText("Today");
        refresher.setActualGroupTodos(TodoHandler.getTodayTodos(model.getUser()));
        refresher.refreshTodos();
    }

    public void handleTomorrowTodos() {
        labelTodoGroupName.setText("Tomorrow");
        refresher.setActualGroupTodos(TodoHandler.getTomorrowTodos(model.getUser()));
        refresher.refreshTodos();
    }

    public void handleCompletedTodos() {
        labelTodoGroupName.setText("Completed");
        refresher.setActualGroupTodos(TodoHandler.getCompletedTodos(model.getUser()));
        refresher.refreshTodos();
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

        refresher.refreshTodos();
    }

    @FXML
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
    @FXML
    public void handleDeleteTodo() {

       try {
           TodoHandler.deleteTodo(this.refresher.getSelectedTodo().getTodoId(), model.getUser());
       }
       catch (TodoDatabaseFail e){
           e.printStackTrace();
       }

       refresher.refreshTodos();
       refresher.setFirstTodoInfo();
    }


    /**
     * Handles the edit button, calls a method which creates a PUT request for
     * editing the selected to_do
     */
    @FXML
    public void handleEditTodo() {
        try {
            refresher.setSelectedTodo(TodoHandler.editTodo(refresher.getSelectedTodo().getTodoId(), model.getUser(), textFieldTaskName, textAreaTaskDescription,
                    datePickerTaskFrom, datePickerTaskTo, textFieldTaskGroup));
        }catch (MandatoryFieldNotInputted | TodoDatabaseFail e){
            e.printStackTrace();
        }

        vBoxNewTaskScreen.setVisible(false);
        vBoxNewTaskScreen.setDisable(true);

        refresher.refreshTodos();
    }

    /**
     * Method which handles the statusChange of to_do
     */
    @FXML
    public void handleStatusChange() {
        try {
            TodoHandler.changeTodoStatus(refresher.getSelectedTodo(), model.getUser().getUid());
        }catch (TodoDatabaseFail e){
            e.printStackTrace();
        }

        refresher.refreshTodos();
    }
}
