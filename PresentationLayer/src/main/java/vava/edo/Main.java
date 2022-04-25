package vava.edo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Locale.setDefault(new Locale("sk", "SK"));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Localization Bundle");
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"), resourceBundle);

        Scene scene = new Scene(root, 1100, 750);
        stage.setTitle("Everyday organizer");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        // Window is closing here
        stage.setOnCloseRequest(windowEvent -> {
            System.out.println("window is closing");
        });
    }

    public static void main(String[] args) {
        launch();
    }
}