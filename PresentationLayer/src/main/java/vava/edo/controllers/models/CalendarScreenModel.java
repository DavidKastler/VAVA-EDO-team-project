package vava.edo.controllers.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import vava.edo.controllers.CalendarScreenController;
import vava.edo.models.User;

import java.io.IOException;
import java.util.ResourceBundle;

public class CalendarScreenModel {
    private AnchorPane calendarScreen;
    private User user;

    public CalendarScreenModel(User user) {
        this.user = user;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/Calendar.fxml"),
                    ResourceBundle.getBundle("Localization Bundle"));
            this.calendarScreen = loader.load();
            CalendarScreenController controller = loader.getController();
            controller.setModel(this);
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
