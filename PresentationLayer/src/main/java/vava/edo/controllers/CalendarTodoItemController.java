package vava.edo.controllers;

import javafx.scene.input.MouseEvent;
import vava.edo.Handlers.RefreshCalendarScreen;
import vava.edo.controllers.models.CalendarTodoItemModel;

public class CalendarTodoItemController {
    private CalendarTodoItemModel model;
    private RefreshCalendarScreen refresher;

    public void setModel(CalendarTodoItemModel model, RefreshCalendarScreen refresher) {
        this.model = model;
        this.refresher = refresher;
        model.getButton().setText(model.getTodo().getTodoName());
    }

    public void handleClickTodoItem(MouseEvent mouseEvent) {
    }
}
