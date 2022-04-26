package vava.edo.controllers.TodoScreen;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import vava.edo.Exepctions.TodoScreen.MandatoryFieldNotInputted;
import vava.edo.Exepctions.TodoScreen.TodoDatabaseFail;
import vava.edo.Handlers.TodoHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


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

    ResourceBundle resourceBundle = ResourceBundle.getBundle("Localization Bundle");
    public void setModel(TodoScreenModel model) {
        this.model = model;
        this.refresher = new RefreshTodoScreen(model.getUser(), vBoxTodos, checkBoxTodoInfo, labelTodoInfoDueTIme,
                labelTodoInfoName, labelTodoInfoDescription, labelTodoInfoGroup, buttonEditTodoInfo, buttonDeleteTodo);

        refresher.initLoader();
        actualSelectedGroup = 1;

        scrollPaneTodos.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPaneTodos.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        buttonHighlight("AllTodos");
    }

    public void handleAllTodos() {
        labelTodoGroupName.setText(resourceBundle.getString("Todos.all"));
        actualSelectedGroup = 1;

        refresher.setActualGroupTodos(model.getUser().getTodos());
        refresher.refreshTodos(actualSelectedGroup);

        buttonHighlight("AllTodos");
    }

    public void handleTodayTodos() {
        labelTodoGroupName.setText(resourceBundle.getString("Todos.today"));
        actualSelectedGroup = 2;
        refresher.setActualGroupTodos(TodoHandler.getTodayTodos(model.getUser()));
        refresher.refreshTodos(actualSelectedGroup);

        buttonHighlight("TodayTodos");
    }

    public void handleTomorrowTodos() {
        labelTodoGroupName.setText(resourceBundle.getString("Todos.tomorrow"));
        actualSelectedGroup = 3;
        refresher.setActualGroupTodos(TodoHandler.getTomorrowTodos(model.getUser()));
        refresher.refreshTodos(actualSelectedGroup);

        buttonHighlight("TomorrowTodos");

    }

    public void handleCompletedTodos() {
        labelTodoGroupName.setText(resourceBundle.getString("Todos.completed"));
        actualSelectedGroup = 4;
        refresher.setActualGroupTodos(TodoHandler.getCompletedTodos(model.getUser()));
        refresher.refreshTodos(actualSelectedGroup);

        buttonHighlight("CompletedTodos");
    }

    @FXML
    protected void handleAddNewTodo() {
        System.out.println("Clicked add new task button (Todo screen)");

        // Have to be emptied before use
        labelTitleWindow.setText(resourceBundle.getString("Todo.newTodo"));
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
        labelTitleWindow.setText(resourceBundle.getString("Todo.editTodo"));
        textFieldTaskName.setText(labelTodoInfoName.getText());
        textAreaTaskDescription.setText(labelTodoInfoDescription.getText());
        textFieldTaskGroup.setText(labelTodoInfoGroup.getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fromDate = LocalDate.parse(refresher.getSelectedTodo().getFromTime(), formatter);
        LocalDate toDate = LocalDate.parse(refresher.getSelectedTodo().getToTime(), formatter);
        datePickerTaskFrom.setValue(fromDate);
        datePickerTaskTo.setValue(toDate);
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

    /**
     * Method which highlights the selected button
     *
     * @param buttonName name of the button which is going to be highlighted
     */
    private void buttonHighlight(String buttonName) {
        buttonAllTodos.setStyle(buttonName.equals("AllTodos") ? "-fx-background-color:  #8D8D8D" : "-fx-background-color:  transparent");
        buttonTodayTodos.setStyle(buttonName.equals("TodayTodos") ? "-fx-background-color:  #8D8D8D" : "-fx-background-color:  transparent");
        buttonTomorrowTodos.setStyle(buttonName.equals("TomorrowTodos") ? "-fx-background-color:  #8D8D8D" : "-fx-background-color:  transparent");
        buttonCompletedTodos.setStyle(buttonName.equals("CompletedTodos") ? "-fx-background-color:  #8D8D8D" : "-fx-background-color:  transparent");

    }
}
