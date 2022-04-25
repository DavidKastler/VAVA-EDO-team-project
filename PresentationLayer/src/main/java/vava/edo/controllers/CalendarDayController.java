package vava.edo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import vava.edo.Handlers.RefreshCalendarScreen;
import vava.edo.controllers.models.CalendarDayModel;
import vava.edo.controllers.models.CalendarTodoItemModel;

public class CalendarDayController {
    private CalendarDayModel model;
    private RefreshCalendarScreen refresher;

    @FXML
    private Label labelDayNumber;

    @FXML
    private VBox vBoxTodos;

    @FXML
    private AnchorPane anchorMoreTodos;

    @FXML
    private Label labelMoreTodos;

    public void setModel(CalendarDayModel model) {
        this.model = model;

        // TODO toto je zle ale je to v priebehu vyrabania
        //vBoxTodos.getChildren().add(new CalendarTodoItemModel(model.getRefresher(), vBoxTodos).getButton());

        labelDayNumber.setText("" + model.getDayNumber());
    }
}
