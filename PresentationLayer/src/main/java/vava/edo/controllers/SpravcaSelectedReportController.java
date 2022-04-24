package vava.edo.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;


public class SpravcaSelectedReportController implements Initializable {

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


    public SpravcaSelectedReportController() {

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


    public void rejectReport(javafx.scene.input.MouseEvent mouseEvent) {
        System.out.println("REJECTED\n" + "From: " + sender.getText() + "\n" + "To: " + receiver.getText() + "\n" + "text: " + text_area.getText());
    }

    public void acceptReport(javafx.scene.input.MouseEvent mouseEvent) {
        System.out.println("ACCEPTED\n" + "From: " + sender.getText() + "\n" + "To: " + receiver.getText() + "\n" + "text: " + text_area.getText());
    }
}