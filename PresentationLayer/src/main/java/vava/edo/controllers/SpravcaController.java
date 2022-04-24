package vava.edo.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vava.edo.models.AdminViewElementModel;
import vava.edo.models.SpravcaViewElementModel;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class SpravcaController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField search_users;

    @FXML
    private VBox users_vbox;


    public SpravcaController() {

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

        List<String> registration = new ArrayList<>();
        registration.add("1");
        registration.add("2");
        registration.add("3");
        registration.add("4");
        registration.add("3");
        registration.add("5");
        registration.add("2");
        registration.add("5");
        registration.add("6");
        registration.add("4");

        List<String> reported = new ArrayList<>();
        reported.add("123");
        reported.add("456");
        reported.add("456");
        reported.add("123");
        reported.add("789");
        reported.add("789");
        reported.add("456");
        reported.add("123");
        reported.add("456");
        reported.add("789");

        List<String> status = new ArrayList<>();
        status.add("true");
        status.add("false");
        status.add("true");
        status.add("false");
        status.add("false");
        status.add("true");
        status.add("false");
        status.add("false");
        status.add("true");
        status.add("true");

        for (Integer i = 0; i < usernames.size(); i++){
            SpravcaViewElementModel element = new SpravcaViewElementModel(usernames.get(i), type.get(i), registration.get(i), reported.get(i), status.get(i));
            HBox hbox = element.getElement();
            users_vbox.getChildren().add(hbox);
        }

    }

    public void handleSearchUser(KeyEvent keyEvent) throws IOException {
        System.out.println(search_users.getText());
    }


}