package vava.edo.controllers.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import vava.edo.Handlers.RefreshCalendarScreen;
import vava.edo.controllers.CalendarTodoItemController;
import vava.edo.models.Todo;

import java.io.IOException;
import java.util.ResourceBundle;

public class CalendarTodoItemModel {
    private Button button;
    private RefreshCalendarScreen refresher;
    private Todo todo;

    public CalendarTodoItemModel(RefreshCalendarScreen refresher, Todo todo) {
        this.refresher = refresher;
        this.todo = todo;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/CalendarTodoItem.fxml"),
                    ResourceBundle.getBundle("Localization Bundle"));
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

    public RefreshCalendarScreen getRefresher() {
        return refresher;
    }

    public Todo getTodo() {
        return todo;
    }
}
