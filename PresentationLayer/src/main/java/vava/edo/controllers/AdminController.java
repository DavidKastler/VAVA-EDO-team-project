package vava.edo.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vava.edo.models.AdminViewElementModel;
import vava.edo.models.FriendElementModel;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class AdminController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private VBox users_vbox;

    @FXML
    private TextField search_users;

    public AdminController() {

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
        usernames.add("Gizela");
        usernames.add("Gustav");
        usernames.add("Adolf");
        List<String> types = new ArrayList<>();
        types.add("Admin");
        types.add("Pleb");
        types.add("Admin");
        types.add("Pleb");
        types.add("Guest");
        types.add("Pleb");
        types.add("Pleb");
        types.add("Pleb");
        types.add("Pleb");
        types.add("Admin");
        types.add("Guest");
        types.add("Pleb");
        types.add("Pleb");
        List<String> registrations = new ArrayList<>();
        registrations.add("12.3.2021");
        registrations.add("10.8.3222");
        registrations.add("210.212.3");
        registrations.add(".301.1..230");
        registrations.add("21.32..32");
        registrations.add("12.3.2021");
        registrations.add("10.8.3222");
        registrations.add("210.212.3");
        registrations.add(".301.1..230");
        registrations.add("21.32..32");
        registrations.add(".301.1..230");
        registrations.add("21.32..32");
        registrations.add("12.3.2021");

        for (Integer i = 0; i < usernames.size(); i++){
            AdminViewElementModel element = new AdminViewElementModel(usernames.get(i), types.get(i), registrations.get(i));
            HBox hbox = element.getAdminViewElement();
            users_vbox.getChildren().add(hbox);
        }

    }


    public void handleSearchUsers(KeyEvent keyEvent) throws IOException {
        System.out.println(search_users.getText());
    }
}