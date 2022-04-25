package vava.edo.controllers;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vava.edo.Handlers.ReportHandler;
import vava.edo.Handlers.SearchHandler;
import vava.edo.controllers.models.ManagerScreenModel;
import vava.edo.models.FriendElementModel;
import vava.edo.models.ManagerViewElementModel;
import vava.edo.models.Relationship;
import vava.edo.models.Report;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
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
        try {
            URL checkConnectionURL = new URL("http://www.google.com");
            URLConnection checkConnection = checkConnectionURL.openConnection();
            checkConnection.connect();
            System.out.println("Internet is connected");
        } catch (IOException e) {
            System.out.println("Internet is not connected");
        }

    }

    public void loadReports(boolean all) {
        if (all) {
            this.reports = ReportHandler.getAllReports(this.model.getUser().getUid());
        } else {
            this.reports = ReportHandler.getPendingReports(this.model.getUser().getUid());
        }

        for (Report report : this.reports) {
            report.setViolatorName();
        }

        reloadReports();
    }

    public void reloadReports() {

        users_vbox.getChildren().clear();

        @SuppressWarnings("unchecked")
        List<Report> searchedReports = (List<Report>)(List) SearchHandler.searchInList(this.reports , "violatorName", search_users.getText());

        for (Integer i = 0; i < searchedReports.size(); i++){
            try {
                ManagerViewElementModel element = new ManagerViewElementModel(searchedReports.get(i).getViolatorName(), searchedReports.get(i).getViolator().getUserRole().getRoleName(), searchedReports.get(i).getStatus());
                HBox hbox = element.getElement();
                users_vbox.getChildren().add(hbox);
            } catch (Exception e) {

            }

        }

    }

    public void handleSearchUser(KeyEvent keyEvent) throws IOException {
        System.out.println(search_users.getText());
    }


    public void refreshReportList(Event event) {
        System.out.println(accepted.isSelected());
        System.out.println(rejected.isSelected());
        System.out.println(pending.isSelected());


    }
}