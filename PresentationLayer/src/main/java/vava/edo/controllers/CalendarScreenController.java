package vava.edo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import vava.edo.Handlers.RefreshCalendarScreen;
import vava.edo.controllers.models.CalendarScreenModel;

public class CalendarScreenController {
    private CalendarScreenModel model;
    private RefreshCalendarScreen refresher;

    @FXML
    private Label labelSelectedMonth;

    @FXML
    private VBox vBoxWeeks;

    @FXML
    private ScrollPane scrollPaneCalendar;

    public void setModel(CalendarScreenModel model) {
        this.model = model;
        this.refresher = new RefreshCalendarScreen(model.getUser(), vBoxWeeks);
        labelSelectedMonth.setText(refresher.getSelectedMonthandYear());

        scrollPaneCalendar.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPaneCalendar.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    public void handleMonthBackward() {
        refresher.minusMonth();
        labelSelectedMonth.setText(refresher.getSelectedMonthandYear());
        refresher.refreshCalendar();
    }

    public void handleMonthForward() {
        refresher.plusMonth();
        labelSelectedMonth.setText(refresher.getSelectedMonthandYear());
        refresher.refreshCalendar();
    }

    public void handleShowToday() {
        refresher.setTodayMonth();
        labelSelectedMonth.setText(refresher.getSelectedMonthandYear());
    }
}
