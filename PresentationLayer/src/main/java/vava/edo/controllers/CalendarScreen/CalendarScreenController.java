package vava.edo.controllers.CalendarScreen;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import vava.edo.Exepctions.TodoScreen.MandatoryFieldNotInputted;
import vava.edo.Exepctions.TodoScreen.TodoDatabaseFail;
import vava.edo.Handlers.TodoHandler;

import java.util.ResourceBundle;

public class CalendarScreenController {
    private CalendarScreenModel model;
    private RefreshCalendarScreen refresher;

    @FXML
    private Label labelSelectedMonth;

    // elements for add/edit to-dos
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

    @FXML
    private Button buttonEditTodo;


    // elements for calendar view
    @FXML
    private VBox vBoxWeeks;

    @FXML
    private ScrollPane scrollPaneCalendar;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("Localization Bundle");

    public void setModel(CalendarScreenModel model) {
        this.model = model;
        this.refresher = new RefreshCalendarScreen(model.getUser(), vBoxNewTaskScreen, vBoxWeeks, labelTitleWindow,
                textFieldTaskName, textFieldTaskGroup, textAreaTaskDescription, datePickerTaskFrom, datePickerTaskTo, buttonAcceptTodo, buttonEditTodo);
        labelSelectedMonth.setText(refresher.getSelectedMonthandYear());

        scrollPaneCalendar.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPaneCalendar.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    public void handleMonthBackward() {
        refresher.minusMonth();
        labelSelectedMonth.setText(refresher.getSelectedMonthandYear());
        refresher.refreshCalendar();
    }

    public void handleMonthForward() {
        refresher.plusMonth();
        labelSelectedMonth.setText(refresher.getSelectedMonthandYear());
        refresher.refreshCalendar();
    }

    public void handleShowToday() {
        refresher.setTodayMonth();
        labelSelectedMonth.setText(refresher.getSelectedMonthandYear());
        refresher.refreshCalendar();
    }

    @FXML
    protected void handleAddNewTodo() {
        System.out.println("Clicked add new task button (Calendar screen)");

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

    // TODO spraviť handlery pre upravu a pridavanie to-dos
    public void handleCancelTodo() {
        vBoxNewTaskScreen.setVisible(false);
        vBoxNewTaskScreen.setDisable(true);
    }

    public void handleAcceptTodo() {
        try{
            TodoHandler.addTodoToUser(model.getUser(), textFieldTaskName, textAreaTaskDescription,
                    datePickerTaskFrom, datePickerTaskTo, textFieldTaskGroup);
        }
        catch (MandatoryFieldNotInputted | TodoDatabaseFail e) {
            e.printStackTrace();
        }

        vBoxNewTaskScreen.setVisible(false);
        vBoxNewTaskScreen.setDisable(true);

        refresher.refreshCalendar();
    }

    public void handleEditTodo() {
        try {
            TodoHandler.editTodo(refresher.getEditingTodo().getTodoId(), model.getUser(), textFieldTaskName, textAreaTaskDescription,
                    datePickerTaskFrom, datePickerTaskTo, textFieldTaskGroup);
        }catch (MandatoryFieldNotInputted | TodoDatabaseFail e){
            e.printStackTrace();
        }

        vBoxNewTaskScreen.setVisible(false);
        vBoxNewTaskScreen.setDisable(true);

        refresher.refreshCalendar();
    }
}
