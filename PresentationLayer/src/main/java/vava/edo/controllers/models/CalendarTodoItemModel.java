package vava.edo.controllers.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import vava.edo.Handlers.RefreshCalendarScreen;
import vava.edo.controllers.CalendarTodoItemController;
import vava.edo.models.Todo;
import vava.edo.models.User;

import java.io.IOException;

public class CalendarTodoItemModel {
    private Button button;
    private RefreshCalendarScreen refresher;
    private User user;
    private Todo todo;

    private VBox rootVBoxDayCell;

    public CalendarTodoItemModel(RefreshCalendarScreen refresher, VBox rootVBoxDayCell) {
        this.refresher = refresher;
        this.rootVBoxDayCell = rootVBoxDayCell;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/CalendarDayCell.fxml"));
            button = loader.load();
            CalendarTodoItemController controller = loader.getController();
            controller.setModel(this, refresher);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Button getButton() {
        return button;
    }
}
