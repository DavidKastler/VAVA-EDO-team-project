package vava.edo.controllers;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ReportScreenController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button report_button;

    @FXML
    private TextArea text_area;



    public ReportScreenController() {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void handleSendReportButton(javafx.scene.input.MouseEvent mouseEvent) {
        String reportMessage = text_area.getText();

        Stage stage = (Stage) report_button.getScene().getWindow();
        stage.close();
    }
}
