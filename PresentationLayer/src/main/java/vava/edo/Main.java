package vava.edo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vava.edo.Handlers.GroupHandler;
import vava.edo.Handlers.MessageHandler;
import vava.edo.Handlers.RelationshipHandler;
import vava.edo.Handlers.ReportHandler;
import vava.edo.models.Message;
import vava.edo.schema.MessageDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Chat.fxml"));

        Scene scene = new Scene(root, 1100, 750);
        stage.setTitle("Everyday organizer");
        stage.setScene(scene);
        stage.show();

        Integer[] memberIds = {1,2,3,4};

        RelationshipHandler.getAllFriends(9);
    }

    public static void main(String[] args) {
        launch();
    }
}