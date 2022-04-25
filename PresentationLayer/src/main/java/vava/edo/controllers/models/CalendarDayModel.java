package vava.edo.controllers.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vava.edo.Handlers.RefreshCalendarScreen;
import vava.edo.controllers.CalendarDayController;
import vava.edo.models.Todo;
import vava.edo.models.User;

import java.io.IOException;
import java.util.ArrayList;

public class CalendarDayModel {
    private VBox dayVBox;
    private RefreshCalendarScreen refresher;
    private User user;

    private HBox rootHBoxWeek;

    // TODO pridať ktory je to den pre daný cell
    private int dayNumber;
    private ArrayList<Todo> dayTodos = new ArrayList<>();

    public CalendarDayModel(RefreshCalendarScreen refresher, HBox rootHBoxWeek, int dayNumber, ArrayList<Todo> dayTodos) {
        this.refresher = refresher;
        this.rootHBoxWeek = rootHBoxWeek;
        this.dayNumber = dayNumber;
        this.dayTodos = dayTodos;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/CalendarDayCell.fxml"));
            dayVBox = loader.load();
            CalendarDayController controller = loader.getController();
            controller.setModel(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public VBox getDayVBox() {
        return dayVBox;
    }

    public RefreshCalendarScreen getRefresher() {
        return refresher;
    }

    public ArrayList<Todo> getDayTodos() {
        return dayTodos;
    }

    public void setDayTodos(ArrayList<Todo> dayTodos) {
        this.dayTodos = dayTodos;
    }
}
