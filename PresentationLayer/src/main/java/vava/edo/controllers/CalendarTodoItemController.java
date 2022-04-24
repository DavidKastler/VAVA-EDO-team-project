package vava.edo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vava.edo.Handlers.RefreshCalendarScreen;
import vava.edo.controllers.models.CalendarTodoItemModel;

public class CalendarTodoItemController {
    private CalendarTodoItemModel model;
    private RefreshCalendarScreen refresher;

    @FXML
    private Label labelDayNumber;

    public void setModel(CalendarTodoItemModel model, RefreshCalendarScreen refresher) {
        this.model = model;
        this.refresher = refresher;
    }
}
