package vava.edo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import vava.edo.Exepctions.TodoScreen.MandatoryFieldNotInputted;
import vava.edo.Exepctions.TodoScreen.TodoDatabaseFail;
import vava.edo.Handlers.RefreshTodoScreen;
import vava.edo.Handlers.TodoHandler;
import vava.edo.controllers.models.TodoScreenModel;


public class TodosScreenController {
    private TodoScreenModel model;
    private RefreshTodoScreen refresher;
    private int actualSelectedGroup;  // variable to handle correct refresh after changes with todos

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

    @FXML
    private ScrollPane scrollPaneTodos;


    // FXML elements for new to_do window
    @FXML
    private VBox vBoxNewTaskScreen;

    @FXML
    private Label labelTitleWindow;

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
        this.refresher = new RefreshTodoScreen(model.getUser(), vBoxTodos, checkBoxTodoInfo, labelTodoInfoDueTIme,
                labelTodoInfoName, labelTodoInfoDescription, labelTodoInfoGroup, buttonEditTodoInfo, buttonDeleteTodo);

        refresher.initLoader();
        actualSelectedGroup = 1;
        buttonAllTodos.setStyle("-fx-background-color:  #8D8D8D");
        buttonTodayTodos.setStyle("-fx-background-color:  transparent");
        buttonTomorrowTodos.setStyle("-fx-background-color:  transparent");
        buttonCompletedTodos.setStyle("-fx-background-color:  transparent");

        scrollPaneTodos.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPaneTodos.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    public void handleAllTodos() {
        labelTodoGroupName.setText("All");
        actualSelectedGroup = 1;
        buttonAllTodos.setStyle("-fx-background-color:  #8D8D8D");
        buttonTodayTodos.setStyle("-fx-background-color:  transparent");
        buttonTomorrowTodos.setStyle("-fx-background-color:  transparent");
        buttonCompletedTodos.setStyle("-fx-background-color:  transparent");
        refresher.setActualGroupTodos(model.getUser().getTodos());
        refresher.refreshTodos(actualSelectedGroup);
    }

    public void handleTodayTodos() {
        labelTodoGroupName.setText("Today");
        actualSelectedGroup = 2;
        buttonAllTodos.setStyle("-fx-background-color:  transparent");
        buttonTodayTodos.setStyle("-fx-background-color:  #8D8D8D");
        buttonTomorrowTodos.setStyle("-fx-background-color:  transparent");
        buttonCompletedTodos.setStyle("-fx-background-color:  transparent");
        refresher.setActualGroupTodos(TodoHandler.getTodayTodos(model.getUser()));
        refresher.refreshTodos(actualSelectedGroup);
    }

    public void handleTomorrowTodos() {
        labelTodoGroupName.setText("Tomorrow");
        actualSelectedGroup = 3;
        buttonAllTodos.setStyle("-fx-background-color:  transparent");
        buttonTodayTodos.setStyle("-fx-background-color:  transparent");
        buttonTomorrowTodos.setStyle("-fx-background-color:  #8D8D8D");
        buttonCompletedTodos.setStyle("-fx-background-color:  transparent");
        refresher.setActualGroupTodos(TodoHandler.getTomorrowTodos(model.getUser()));
        refresher.refreshTodos(actualSelectedGroup);
    }

    public void handleCompletedTodos() {
        labelTodoGroupName.setText("Completed");
        actualSelectedGroup = 4;
        buttonAllTodos.setStyle("-fx-background-color:  transparent");
        buttonTodayTodos.setStyle("-fx-background-color:  transparent");
        buttonTomorrowTodos.setStyle("-fx-background-color:  transparent");
        buttonCompletedTodos.setStyle("-fx-background-color:  #8D8D8D");
        refresher.setActualGroupTodos(TodoHandler.getCompletedTodos(model.getUser()));
        refresher.refreshTodos(actualSelectedGroup);
    }

    @FXML
    protected void handleAddNewTodo() {
        System.out.println("Clicked add new task button (Todo screen)");

        // Have to be emptied before use
        labelTitleWindow.setText("New Todo");
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

        refresher.refreshTodos(actualSelectedGroup);
    }

    @FXML
    public void handleEditTodoInfo() {
        labelTitleWindow.setText("Edit Todo");
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

       refresher.refreshTodos(actualSelectedGroup);
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

        refresher.refreshTodos(actualSelectedGroup);
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

        refresher.refreshTodos(actualSelectedGroup);
    }
}
