package vava.edo.controllers.CalendarScreen;

public class CalendarTodoItemController {
    private CalendarTodoItemModel model;
    private RefreshCalendarScreen refresher;

    public void setModel(CalendarTodoItemModel model, RefreshCalendarScreen refresher) {
        this.model = model;
        this.refresher = refresher;
        model.getButton().setText(model.getTodo().getTodoName());
    }

    public void handleClickTodoItem() {
        System.out.println("Clicked todo: " + model.getTodo().getTodoName());
        model.getRefresher().setEditWindow(model.getTodo());
    }
}
