package vava.edo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vava.edo.Handlers.MessageHandler;
import vava.edo.models.Message;
import vava.edo.schema.MessageDto;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Chat.fxml"));

        Scene scene = new Scene(root, 1100, 750);
        stage.setTitle("Everyday organizer");
        stage.setScene(scene);
        stage.show();

        MessageHandler.getAllGroups(9);
        MessageHandler.getAllMessagesInGroup(9,4);
        MessageHandler.sendMessage(9, 4, "abc");
    }

    public static void main(String[] args) {
        launch();
    }
}