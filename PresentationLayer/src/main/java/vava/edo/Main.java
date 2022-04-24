package vava.edo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Scene scene = new Scene(root, 1100, 750);
        stage.setTitle("Everyday organizer");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        // Serialization of the user object instance is invoked here and only when the remember me is checked
        stage.setOnCloseRequest(windowEvent -> {
            System.out.println("window is closing"); // TODO serialization will be implemented here
        });
    }

    public static void main(String[] args) {
        launch();
    }
}