package vava.edo.Handlers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vava.edo.controllers.models.CalendarDayModel;
import vava.edo.models.Todo;
import vava.edo.models.User;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;

public class RefreshCalendarScreen {
    private User user;
    private LocalDate currentDate;
    private Month selectedMonth;
    private int selectedYear;

    private ArrayList<CalendarDayModel> dayModels = new ArrayList<>();
    private Todo editingTodo;  // needed to edit in CalendarScreenController for TodoHandler.editTodo()

    private VBox vBoxNewTaskScreen;
    private VBox vBoxWeeks;
    private Label labelTitleWindow;
    private TextField textFieldTaskName;
    private TextField textFieldTaskGroup;
    private TextArea textAreaTaskDescription;
    private Button buttonAcceptTodo;
    private Button buttonEditTodo;


    public RefreshCalendarScreen(User user, VBox vBoxNewTaskScreen, VBox vBoxWeeks, Label labelTitleWindow,
                                 TextField textFieldTaskName, TextField textFieldTaskGroup,
                                 TextArea textAreaTaskDescription, Button buttonAcceptTodo, Button buttonEditTodo) {
        this.user = user;
        this.vBoxNewTaskScreen = vBoxNewTaskScreen;
        this.vBoxWeeks = vBoxWeeks;
        this.labelTitleWindow = labelTitleWindow;
        this.textFieldTaskName = textFieldTaskName;
        this.textAreaTaskDescription = textAreaTaskDescription;
        this.textFieldTaskGroup = textFieldTaskGroup;
        this.buttonAcceptTodo = buttonAcceptTodo;
        this.buttonEditTodo = buttonEditTodo;

        this.currentDate = LocalDate.now();
        this.selectedMonth = currentDate.getMonth();
        this.selectedYear = currentDate.getYear();

        refreshCalendar();
    }

    public void minusMonth() {
        Calendar cal = Calendar.getInstance();

        if(selectedMonth.getValue() == 1) {
            selectedYear--;
            cal.set(Calendar.YEAR, selectedYear);
            cal.set(Calendar.MONTH, 11);
        }
        else
            cal.set(Calendar.MONTH, selectedMonth.getValue() - 2);

        selectedMonth = Month.of(cal.get(Calendar.MONTH) + 1);
    }

    public void plusMonth() {
        Calendar cal = Calendar.getInstance();

        if(selectedMonth.getValue() == 12) {
            selectedYear++;
            cal.set(Calendar.YEAR, selectedYear);
            cal.set(Calendar.MONTH, 0);
        }
        else
            cal.set(Calendar.MONTH, selectedMonth.getValue());

        selectedMonth = Month.of(cal.get(Calendar.MONTH) + 1);
    }

    public int getDaysCountInMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, selectedYear);
        cal.set(Calendar.MONTH, selectedMonth.getValue() - 1);
        int numDays = cal.getActualMaximum(Calendar.DATE);
        // System.out.println("Days number: " + numDays);
        return numDays;
    }

    public int getWeeksCountInMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, selectedYear);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, selectedMonth.getValue() - 1);
        SimpleDateFormat df = new SimpleDateFormat("u");
        int startOffset = Integer.parseInt(df.format(cal.getTime())) - 1;

        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        int endOffset = 7 - Integer.parseInt(df.format(cal.getTime()));

        return (int) Math.ceil((double)(getDaysCountInMonth() + startOffset + endOffset) / 7);
    }

    private int getFirstDateOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, selectedYear);
        cal.set(Calendar.MONTH, selectedMonth.getValue() - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat df = new SimpleDateFormat("u");
        return Integer.parseInt(df.format(cal.getTime()));
    }

    public void setTodayMonth() {
        selectedMonth = currentDate.getMonth();
        selectedYear = currentDate.getYear();
    }

    public void refreshCalendar() {
        vBoxWeeks.getChildren().clear();
        dayModels.clear();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        HBox weekBox = null;
        int nextMonthDays = 1;
        int thisMonthDays = 1;
        for(int dayCount = 1; dayCount <= (getWeeksCountInMonth() * 7); dayCount++) {
            if(dayCount % 7 == 1) {
                weekBox = new HBox();
                vBoxWeeks.getChildren().add(weekBox);
            }

            cal.set(Calendar.YEAR, selectedYear);
            cal.set(Calendar.MONTH, (selectedMonth.getValue() - 1));

            if(dayCount < getFirstDateOfMonth()) {
                int tempYear = selectedYear;
                if(selectedMonth.getValue() == 1) {
                    tempYear--;
                    cal.set(Calendar.YEAR, tempYear);
                    cal.set(Calendar.MONTH, 11);
                }
                else
                    cal.set(Calendar.MONTH, selectedMonth.getValue() - 2);

                int previousMonthDays = cal.getActualMaximum(Calendar.DATE) - (getFirstDateOfMonth() - dayCount) + 1;
                cal.set(Calendar.DAY_OF_MONTH, previousMonthDays);
                String dayOfTodosString = dateFormat.format(cal.getTime());
                LocalDate dayOfTodos = LocalDate.parse(dayOfTodosString);
                CalendarDayModel dayModel = new CalendarDayModel(this, weekBox, previousMonthDays, TodoHandler.getTodosByDate(user, dayOfTodos, dayOfTodos));
                dayModels.add(dayModel);
                weekBox.getChildren().add(dayModel.getDayVBox());
            }
            else if(dayCount > (getDaysCountInMonth() + getFirstDateOfMonth() - 1)) {
                int tempYear = selectedYear;
                if(selectedMonth.getValue() == 11) {
                    tempYear++;
                    cal.set(Calendar.YEAR, tempYear);
                    cal.set(Calendar.MONTH, 0);
                }
                else
                    cal.set(Calendar.MONTH, selectedMonth.getValue());


                cal.set(Calendar.DAY_OF_MONTH, nextMonthDays);
                String dayOfTodosString = dateFormat.format(cal.getTime());
                LocalDate dayOfTodos = LocalDate.parse(dayOfTodosString);
                CalendarDayModel dayModel = new CalendarDayModel(this, weekBox, nextMonthDays, TodoHandler.getTodosByDate(user, dayOfTodos, dayOfTodos));
                dayModels.add(dayModel);
                weekBox.getChildren().add(dayModel.getDayVBox());
                nextMonthDays++;
            }
            else {
                cal.set(Calendar.DAY_OF_MONTH, thisMonthDays);
                String dayOfTodosString = dateFormat.format(cal.getTime());
                LocalDate dayOfTodos = LocalDate.parse(dayOfTodosString);
                CalendarDayModel dayModel = new CalendarDayModel(this, weekBox, thisMonthDays, TodoHandler.getTodosByDate(user, dayOfTodos, dayOfTodos));
                dayModels.add(dayModel);
                weekBox.getChildren().add(dayModel.getDayVBox());
                thisMonthDays++;
            }
        }
    }

    public String getSelectedMonthandYear() {
        return "" + selectedMonth + " " + selectedYear;
    }

    /**
     * Function which display edit task window, when task in calendar is clicked
     * @param todo
     */
    public void setEditWindow(Todo todo) {
        editingTodo = todo;
        labelTitleWindow.setText("Edit Todo");
        textFieldTaskName.setText(todo.getTodoName());
        textAreaTaskDescription.setText(todo.getTodoDescription());
        textFieldTaskGroup.setText(todo.getGroupName());
        // datePickerTaskFrom.
        // datePickerTaskFrom.
        // TODO datum to datepickrvo sa naplna cez konstruktor tak sa na to pozri potom Mario
        vBoxNewTaskScreen.setVisible(true);
        vBoxNewTaskScreen.setDisable(false);
        buttonEditTodo.setVisible(true);
        buttonAcceptTodo.setVisible(false);
    }

    public Todo getEditingTodo() {
        return editingTodo;
    }
}
