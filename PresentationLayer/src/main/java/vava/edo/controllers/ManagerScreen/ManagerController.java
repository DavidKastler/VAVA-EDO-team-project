package vava.edo.controllers.ManagerScreen;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vava.edo.Handlers.ReportHandler;
import vava.edo.Handlers.SearchHandler;
import vava.edo.models.Report;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class ManagerController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField search_users;

    @FXML
    private VBox users_vbox;

    @FXML
    private CheckBox pending;

    @FXML
    private CheckBox accepted;

    @FXML
    private CheckBox rejected;

    private ManagerScreenModel model;

    public void setModel(ManagerScreenModel model) {
        this.model = model;
    }

    private List<Report> reports = null;

    public ManagerController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loadReports(boolean pending) {
        if (pending) {
            this.reports = ReportHandler.getPendingReports(this.model.getUser().getUid());
        } else {
            this.reports = ReportHandler.getAllReports(this.model.getUser().getUid());
        }

        for (Report report : this.reports) {
            report.setViolatorName();
            report.getViolator().getUserRole().normalizeRoleName();
        }

        reloadReports();
    }

    public void reloadReports() {

        users_vbox.getChildren().clear();

        @SuppressWarnings("unchecked")
        List<Report> searchedReports = (List<Report>)(List) SearchHandler.searchInList(this.reports , "violatorName", search_users.getText());

        for (Integer i = 0; i < searchedReports.size(); i++){
            try {
                searchedReports.get(i).getViolator().getUserRole().normalizeRoleName();
                ManagerViewElementModel element = new ManagerViewElementModel(this.model.getUser(), searchedReports.get(i), this.model.getMenuScreenController());
                HBox hbox = element.getElement();
                users_vbox.getChildren().add(hbox);
            } catch (Exception e) {

            }

        }

    }

    public void handleSearchUser(KeyEvent keyEvent) throws IOException {
        reloadReports();
    }


    public void refreshReportList(Event event) {
        loadReports(pending.isSelected());
    }
}