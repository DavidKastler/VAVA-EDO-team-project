package vava.edo.Handlers;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vava.edo.controllers.models.CalendarDayModel;
import vava.edo.models.User;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;

public class RefreshCalendarScreen {
    private User user;
    private LocalDate currentDate;
    private Month selectedMonth;
    private int selectedYear;

    private ArrayList<HBox> weeks = new ArrayList<>();

    private VBox vBoxWeeks;


    public RefreshCalendarScreen(User user, VBox vBoxWeeks) {
        this.user = user;
        this.vBoxWeeks = vBoxWeeks;

        this.currentDate = LocalDate.now();
        this.selectedMonth = currentDate.getMonth();
        this.selectedYear = currentDate.getYear();

        refreshCalendar();
    }

    public void minusMonth() {
        Calendar cal = Calendar.getInstance();

        if(selectedMonth.getValue() == 1) {
            selectedYear--;
            cal.set(Calendar.YEAR, selectedYear);
            cal.set(Calendar.MONTH, 11);
        }
        else
            cal.set(Calendar.MONTH, selectedMonth.getValue() - 2);

        selectedMonth = Month.of(cal.get(Calendar.MONTH) + 1);
    }

    public void plusMonth() {
        Calendar cal = Calendar.getInstance();

        if(selectedMonth.getValue() == 12) {
            selectedYear++;
            cal.set(Calendar.YEAR, selectedYear);
            cal.set(Calendar.MONTH, 0);
        }
        else
            cal.set(Calendar.MONTH, selectedMonth.getValue());

        selectedMonth = Month.of(cal.get(Calendar.MONTH) + 1);
    }

    public int getDaysCountInMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, selectedYear);
        cal.set(Calendar.MONTH, selectedMonth.getValue() - 1);
        int numDays = cal.getActualMaximum(Calendar.DATE);
        // System.out.println("Days number: " + numDays);
        return numDays;
    }

    public int getWeeksCountInMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, selectedYear);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, selectedMonth.getValue() - 1);
        SimpleDateFormat df = new SimpleDateFormat("u");
        int startDay = Integer.parseInt(df.format(cal.getTime()));
        int maxWeeknumber = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
        if(startDay > 2) {
            maxWeeknumber++;
        }
        return maxWeeknumber;
    }

    private int getFirstDateOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, selectedYear);
        cal.set(Calendar.MONTH, selectedMonth.getValue() - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat df = new SimpleDateFormat("u");
        return Integer.parseInt(df.format(cal.getTime()));
    }

    public void setTodayMonth() {
        selectedMonth = currentDate.getMonth();
        selectedYear = currentDate.getYear();
    }

    // TODO toto uvidíme ako bude
    public void getDayTodos(int dayNumber, int monthNumber, int yearNumber) {

    }

    public void refreshCalendar() {
        weeks.clear();
        vBoxWeeks.getChildren().clear();
        int printedDaysOfMonth = 1;
        int nextMonthDays = 1;
        Calendar cal = Calendar.getInstance();
        System.out.println("Week number: " + getWeeksCountInMonth());
        System.out.println(cal.get(Calendar.MONTH));
        for(int dayCount = 1; dayCount <= (getWeeksCountInMonth() * 7); dayCount++) {
            HBox hBox;
            if(dayCount % 7 == 1) {
                hBox = new HBox();
                weeks.add(hBox);
            }
            else {
                hBox = weeks.get(weeks.size()-1);
            }

            if(dayCount < getFirstDateOfMonth()) {
                if(selectedMonth.getValue() == 1) {
                    int tempYear = selectedYear;
                    tempYear--;
                    cal.set(Calendar.YEAR, tempYear);
                    cal.set(Calendar.MONTH, 11);
                }
                else
                    cal.set(Calendar.MONTH, selectedMonth.getValue() - 2);

                int previousMonthDays = cal.getActualMaximum(Calendar.DATE) - (getFirstDateOfMonth() - dayCount) + 1;
                hBox.getChildren().add(new CalendarDayModel(this, hBox, previousMonthDays).getDayVBox());
            }
            else if(dayCount > (getDaysCountInMonth() + getFirstDateOfMonth() - 1)) {
                if(selectedMonth.getValue() == 11) {
                    int tempYear = selectedYear;
                    tempYear++;
                    cal.set(Calendar.YEAR, tempYear);
                    cal.set(Calendar.MONTH, 0);
                }
                else
                    cal.set(Calendar.MONTH, selectedMonth.getValue());

                hBox.getChildren().add(new CalendarDayModel(this, hBox, nextMonthDays).getDayVBox());
                nextMonthDays++;
            }
            else {
                hBox.getChildren().add(new CalendarDayModel(this, hBox, printedDaysOfMonth).getDayVBox());
                printedDaysOfMonth++;
            }
        }

        for(HBox week : weeks) {
            vBoxWeeks.getChildren().add(week);
        }
    }

    public String getSelectedMonthandYear() {
        return "" + selectedMonth + " " + selectedYear;
    }
}
