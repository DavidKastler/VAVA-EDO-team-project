package vava.edo.controllers.CalendarScreen;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vava.edo.models.Todo;
import vava.edo.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CalendarDayModel {
    private VBox dayVBox;
    private RefreshCalendarScreen refresher;
    private User user;

    private HBox rootHBoxWeek;

    // TODO pridať ktory je to den pre daný cell
    private int dayNumber;
    private ArrayList<Todo> dayTodos;

    public CalendarDayModel(RefreshCalendarScreen refresher, HBox rootHBoxWeek, int dayNumber, ArrayList<Todo> dayTodos) {
        this.refresher = refresher;
        this.rootHBoxWeek = rootHBoxWeek;
        this.dayNumber = dayNumber;
        this.dayTodos = dayTodos;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/CalendarDayCell.fxml"),
                    ResourceBundle.getBundle("Localization Bundle"));
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
}
