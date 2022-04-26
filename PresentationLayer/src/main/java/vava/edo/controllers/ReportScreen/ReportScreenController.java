package vava.edo.controllers.ReportScreen;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
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
        System.out.println(reportMessage);

        Stage stage = (Stage) report_button.getScene().getWindow();
        stage.close();
    }
}
