package vava.edo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import vava.edo.Handlers.RefreshCalendarScreen;
import vava.edo.controllers.models.CalendarDayModel;
import vava.edo.controllers.models.CalendarTodoItemModel;
import vava.edo.models.Todo;

public class CalendarDayController {
    private CalendarDayModel model;
    private RefreshCalendarScreen refresher;

    @FXML
    private Label labelDayNumber;

    @FXML
    private VBox vBoxTodos;

    @FXML
    private ScrollPane scrollPaneDay;

    public void setModel(CalendarDayModel model) {
        this.model = model;

        labelDayNumber.setText("" + model.getDayNumber());
        for(Todo todo : model.getDayTodos()) {
            vBoxTodos.getChildren().add(new CalendarTodoItemModel(model.getRefresher(), todo).getButton());
        }

        if(model.getDayTodos().size() <= 5) {
            scrollPaneDay.setFitToHeight(true);
        }
        scrollPaneDay.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPaneDay.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }
}
