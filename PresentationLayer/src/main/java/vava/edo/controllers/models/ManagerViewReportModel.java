package vava.edo.controllers.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import vava.edo.controllers.ManagerSelectedReportController;
import vava.edo.controllers.MenuScreenController;
import vava.edo.models.Report;
import vava.edo.models.User;

import java.io.IOException;
import java.util.ResourceBundle;

@Getter
public class ManagerViewReportModel {
    private AnchorPane viewReportScreen;
    private final User user;
    private final Report report;
    private final MenuScreenController menuScreenController;

    public ManagerViewReportModel(User user, MenuScreenController menuScreenController, Report report) {
        this.user = user;
        this.menuScreenController = menuScreenController;
        this.report = report;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/ManagerSelectedReport.fxml"), ResourceBundle.getBundle("Localization Bundle"));
            this.viewReportScreen = loader.load();
            ManagerSelectedReportController controller = loader.getController();
            controller.setModel(this);
            controller.loadReport();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AnchorPane getReportScreen() {
        return viewReportScreen;
    }

    public User getUser() {
        return user;
    }

    public MenuScreenController getMenuScreenController() { return this.menuScreenController; }
}
