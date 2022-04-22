package vava.edo.controllers.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import vava.edo.controllers.CalendarScreenController;
import vava.edo.models.User;

import java.io.IOException;

public class CalendarScreenModel {
    private AnchorPane calendarScreen;
    private User user;

    public CalendarScreenModel(User user) {
        this.user = user;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/Calendar.fxml"));
            this.calendarScreen = loader.load();
            CalendarScreenController controller = loader.getController();
            // controller.setModel(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AnchorPane getCalendarScreen() {
        return calendarScreen;
    }

    public User getUser() {
        return user;
    }
}
