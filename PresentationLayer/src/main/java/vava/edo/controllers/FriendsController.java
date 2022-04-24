package vava.edo.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vava.edo.Handlers.RelationshipHandler;
import vava.edo.Handlers.SearchHandler;
import vava.edo.models.FriendElementModel;
import vava.edo.models.Relationship;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class FriendsController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField search_field_new_friend;

    @FXML
    private TextField search_friends;

    @FXML
    private VBox users_vbox;

    private List<Relationship> friends = null;

    public FriendsController() {

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

        this.friends = RelationshipHandler.getAllFriends(9);

        reloadFriends();

    }

    public void reloadFriends() {

        users_vbox.getChildren().clear();

        @SuppressWarnings("unchecked")
        List<Relationship> searchedFriends = (List<Relationship>)(List)SearchHandler.searchInList(friends, "userName", search_friends.getText());

        for (Integer i = 0; i < friends.size(); i++){
            try {
                FriendElementModel element = new FriendElementModel(searchedFriends.get(i).getUserName());
                HBox hbox = element.getFriendElement();
                users_vbox.getChildren().add(hbox);
            } catch (Exception e) {

            }

        }
    }

    /*
    public HBox getOneElement(){
        VBox parent = new VBox();
        parent.getChildren().addAll(users_vbox.getChildren());
        HBox element = (HBox)parent.getChildren().get(0);

        return element;
    }

    public HBox editElement(HBox element, String name){
        HBox hBox = new HBox();
        //hBox.clone(element);
        hBox.getChildren().addAll(element.getChildren());
        ((Label)((HBox)hBox.getChildren().get(0)).getChildren().get(0)).setText(name);

        return hBox;
    }
    */









    /*
    public HBox createElement(String name){
        Button buttonDelete = new Button();
        buttonDelete = editButton(buttonDelete);
        Button buttonReport = new Button();
        buttonReport = editButton(buttonReport);
        HBox delete = buttonWrapInHBox(buttonDelete);
        HBox report = buttonWrapInHBox(buttonReport);

        HBox rightSide = wrapTwoButtons(delete, report);
        HBox leftSide = createLabelInHBox(name);


        HBox element = new HBox();
        element = wrapElement(leftSide, rightSide);

        return element;
    }



    public Button editButton(Button button){
        button.setText("X");
        button.setStyle("-fx-font-family: Arial");
        button.setStyle("-fx-font-weight: bold");
        button.setStyle("-fx-background-color: transparent");
        button.setStyle("-fx-font-size: 40");
        button.setStyle("-fx-text-fill: white");

        button.setOnMouseClicked(mouseEvent -> {
            deleteFriend(mouseEvent);
        });
        return button;
    }

    public HBox buttonWrapInHBox(Button button){
        HBox hbox = new HBox();
        hbox.setPrefWidth(100);
        hbox.setMinHeight(100);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setStyle("-fx-background-color: transparent");

        return hbox;
    }

    public HBox wrapTwoButtons(HBox removeButton, HBox reportButton){
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_LEFT);
        hbox.setPrefWidth(200);
        hbox.setMinHeight(100);
        hbox.setStyle("-fx-background-color: transparent");

        hbox.getChildren().add(removeButton);
        hbox.getChildren().add(reportButton);

        return hbox;
    }

    public HBox createLabelInHBox(String username){
        HBox hbox = new HBox();
        Label label = new Label();
        label.setText(username);
        label.setStyle("-fx-font-family: Arial");
        label.setStyle("-fx-text-fill: white");
        label.setStyle("-fx-font-size: 40");


        hbox.setPadding(new Insets(0, 0, 0, 40));
        hbox.setPrefWidth(840);
        hbox.setPrefHeight(100);
        hbox.setStyle("-fx-background-color: transparent");

        return hbox;
    }

    public HBox wrapElement(HBox hboxLeft, HBox hboxRight){
        HBox element = new HBox();
        element.setStyle("-fx-border-color: #c4c4c4");
        element.setPrefWidth(1040);
        element.setPrefHeight(100);

        element.getChildren().add(hboxLeft);
        element.getChildren().add(hboxRight);

        return element;
    }
 */

    public void handleSearchNewFriend(KeyEvent keyEvent) throws IOException {
        System.out.println(search_field_new_friend.getText());
    }

    public void handleSearchFriend(KeyEvent keyEvent) throws IOException {
        reloadFriends();
    }

    public void deleteFriend(MouseEvent mouseEvent){
        Button button = (Button)mouseEvent.getSource();

        System.out.println("Zavolane");
    }

    public void reportFriend(MouseEvent mouseEvent){
        Button button = (Button)mouseEvent.getSource();

        System.out.println("Zavolane");
    }
}