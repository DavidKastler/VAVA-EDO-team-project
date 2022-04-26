package vava.edo.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import vava.edo.Handlers.ReportHandler;
import vava.edo.controllers.models.ManagerScreenModel;
import vava.edo.controllers.models.ManagerViewReportModel;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;


public class ManagerSelectedReportController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label sender;

    @FXML
    private Label receiver;

    @FXML
    private Button acceptReport;

    @FXML
    private Button rejectButton;

    @FXML
    private TextArea text_area;

    private ManagerViewReportModel model;

    public void setModel(ManagerViewReportModel model) {
        this.model = model;
    }

    public ManagerSelectedReportController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void loadReport() {
        this.sender.setText(this.model.getReport().getReporter().getUsername());
        this.receiver.setText(this.model.getReport().getViolator().getUsername());
        this.text_area.setText(this.model.getReport().getReportMessage());
    }


    public void rejectReport(javafx.scene.input.MouseEvent mouseEvent) {
        ReportHandler.rejectReport(this.model.getUser().getUid(), this.model.getReport().getReportId());
        this.model.getMenuScreenController().gethBoxChangingScreen().getChildren().clear();
        this.model.getMenuScreenController().gethBoxChangingScreen().getChildren().add(new ManagerScreenModel(this.model.getUser(), this.model.getMenuScreenController()).getManagerScreen());
    }

    public void acceptReport(javafx.scene.input.MouseEvent mouseEvent) {
        ReportHandler.acceptReport(this.model.getUser().getUid(), this.model.getReport().getReportId());
        this.model.getMenuScreenController().gethBoxChangingScreen().getChildren().clear();
        this.model.getMenuScreenController().gethBoxChangingScreen().getChildren().add(new ManagerScreenModel(this.model.getUser(), this.model.getMenuScreenController()).getManagerScreen());
    }

    public void returnToPreviousScreen(MouseEvent mouseEvent) {
        this.model.getMenuScreenController().gethBoxChangingScreen().getChildren().clear();
        this.model.getMenuScreenController().gethBoxChangingScreen().getChildren().add(new ManagerScreenModel(this.model.getUser(), this.model.getMenuScreenController()).getManagerScreen());
    }
}