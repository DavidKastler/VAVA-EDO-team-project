package vava.edo.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import vava.edo.controllers.models.ManagerViewReportModel;
import vava.edo.models.ManagerViewElementModel;
import vava.edo.models.Report;

import java.net.URL;
import java.util.ResourceBundle;


public class ManagerViewElementController implements Initializable {
    public String getUsername() {
        return username.getText();
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    public Label getType() {
        return type;
    }

    public void setType(String type) {
        this.type.setText(type);
    }

    public Label getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status.setText(status);
    }





    @FXML
    private Button username;
    @FXML
    private Label type;
    @FXML
    private Label status;



    private ManagerViewElementModel model;

    public ManagerViewElementModel getModel() {
        return model;
    }





    public void setModel(ManagerViewElementModel model) {
        this.model = model;

        setUsername(model.getReport().getViolatorName());
        this.model.getUser().getUserRole().normalizeRoleName();
        setType(model.getUser().getUserRole().getRoleName());
        setStatus(model.getReport().getStatus());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void openReport(MouseEvent mouseEvent) {
        this.model.getMenuScreenController().gethBoxChangingScreen().getChildren().clear();
        this.model.getMenuScreenController().gethBoxChangingScreen().getChildren().add(new ManagerViewReportModel(this.model.getUser(), this.model.getMenuScreenController() , this.model.getReport()).getReportScreen());
    }
}
