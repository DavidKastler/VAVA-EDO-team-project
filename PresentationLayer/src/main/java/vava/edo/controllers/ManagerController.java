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
import vava.edo.controllers.models.ManagerScreenModel;
import vava.edo.models.ManagerViewElementModel;

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

    public ManagerController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<String> usernames = new ArrayList<>();
        usernames.add("Jano");
        usernames.add("Fero");
        usernames.add("Kubo");
        usernames.add("Lubo");
        usernames.add("Eva");
        usernames.add("Katka");
        usernames.add("Hana");
        usernames.add("Karol");
        usernames.add("Adam");
        usernames.add("Erzika");

        List<String> type = new ArrayList<>();
        type.add("pleb");
        type.add("Guest");
        type.add("Guest");
        type.add("pleb");
        type.add("pleb");
        type.add("pleb");
        type.add("Admin");
        type.add("Admin");
        type.add("pleb");
        type.add("Admin");


        List<String> status = new ArrayList<>();
        status.add("pending");
        status.add("accepted");
        status.add("rejected");
        status.add("rejected");
        status.add("pending");
        status.add("rejected");
        status.add("accepted");
        status.add("accepted");
        status.add("rejected");
        status.add("pending");

        for (Integer i = 0; i < usernames.size(); i++){
            ManagerViewElementModel element = new ManagerViewElementModel(usernames.get(i), type.get(i), status.get(i));
            HBox hbox = element.getElement();
            users_vbox.getChildren().add(hbox);
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