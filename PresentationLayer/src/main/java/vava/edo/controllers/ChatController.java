package vava.edo.controllers;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import vava.edo.Handlers.GroupHandler;
import vava.edo.Handlers.MessageHandler;
import vava.edo.controllers.models.ChatScreenModel;
import vava.edo.models.*;
import vava.edo.models.ChatGrayElementModel;
import vava.edo.models.ChatPinkElementModel;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.util.*;


public class ChatController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField search_field;

    @FXML
    private Button send_message_button;

    @FXML
    private Button block_user;

    @FXML
    private Label chat_name;

    @FXML
    private TextField chat_message;

    @FXML
    private VBox chat_list_pane;

    @FXML
    private ScrollPane chat_pane;

    @FXML
    private VBox messages_list;

    @FXML
    private AnchorPane rootPane1;

    @FXML
    private AnchorPane rootPane11;

    @FXML
    private HBox report_hbox;

    @FXML
    private HBox chat_screen_box;

    @FXML
    private TextArea text_area;

    @FXML
    private Label chat_name_error;

    private ChatScreenModel model;

    public void setModel(ChatScreenModel model) {
        this.model = model;
    }


    public ChatController() {

    }


    public Button editButton(Button button, String groupName, Boolean color, Integer groupId){
        button.setText(groupName);
        button.setWrapText(true);

        Font font = Font.font("Arial", FontWeight.BOLD, 24);
        button.setFont(font);
        //button.setStyle("-fx-background-insets: 0 0 -1 0");

        if (color == true){
            button.setStyle("-fx-background-color: #c4c4c4");
        } else {
            button.setStyle("-fx-background-color:  #808080");
        }

        //button.setStyle("-fx-font-weight: bold");
        button.setPrefHeight(50);
        button.setPrefWidth(240);
        button.setOnMouseClicked(event -> {
            try {
                handleViewChatButton(event, groupId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return button;
    }

    public HBox editLabel(Label label, String messageText, Boolean color){

        label.setWrapText(true);
        label.setText(messageText);

        Font font = Font.font("Arial", 34);
        label.setFont(font);
        label.setMaxWidth(350);
        HBox messageBox = new HBox();


        label.setStyle("-fx-background-radius: 20");

        HBox box = new HBox();


        box.setPrefWidth(800);


        if (color == true){
            label.setStyle("-fx-background-color: #ff9797");
            label.setAlignment(Pos.CENTER_LEFT);
            box.setAlignment(Pos.BOTTOM_LEFT);
            box.setMargin(label, new Insets(5, 5, 5, 5));
        } else {
            label.setStyle("-fx-background-color:  #c4c4c4");
            label.setAlignment(Pos.CENTER_RIGHT);
            box.setAlignment(Pos.BOTTOM_RIGHT);
            box.setMargin(label, new Insets(5, 10, 5, 5));
        }
        label.setPadding(new Insets(10,30,10,30));



        box.getChildren().add(label);

        return box;
    }

    public void writeChatList(List<Group> usernames){
        for (int i = 0; i < usernames.size(); i++){
            //System.out.println(id1);
            Button button = new Button();
            Boolean color;
            if (i % 2 == 0){
                color = false;
            } else {
                color = true;
            }
            editButton(button, usernames.get(i).getGroupName(), color, usernames.get(i).getGroupId());

            this.chat_list_pane.getChildren().add(button);
        }
    }

    public void refreshColorsOfChats(ObservableList<Node> chats){
        for (Integer i = 0; i < chats.size(); i++){
            if ((i % 2) == 1){
                chats.get(i).setStyle("-fx-background-color: #c4c4c4");
            } else {
                chats.get(i).setStyle("-fx-background-color:  #808080");
            }
        }
    }

    public void writeChatMessages(List<String> userIds, List<String> messages){
        messages_list.getChildren().clear();
        HBox box = new HBox();


        for (int i = 0; i < messages.size(); i++){
            //System.out.println(id1);
            //HBox box = new HBox();
            Label message = new Label();
            Boolean color;
            if (userIds.get(i).equals("13")){
                color = false;
            } else {
                color = true;
            }
            box = editLabel(message, messages.get(i), color);


            this.messages_list.getChildren().add(box);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loadAllGroups() {
        List<Group> allUsersGroups = GroupHandler.getAllGroups(this.model.getUser().getUid());
        writeChatList(allUsersGroups);
    }

    @FXML
    public void handleViewChatButton(MouseEvent mouseEvent, Integer groupId) throws IOException {

        messages_list.getChildren().clear();
        refreshColorsOfChats(this.chat_list_pane.getChildren());

        //metoda na vratenie userov v danych chatoch/groupach
        Button actualButton = ((Button)mouseEvent.getSource());
        //System.out.println("Skuska");
        for (Integer i = 0; i < this.chat_list_pane.getChildren().size(); i++){
            if (actualButton.equals(this.chat_list_pane.getChildren().get(i))){
                this.chat_list_pane.getChildren().get(i).setStyle("-fx-background-color:  #ff9797");
            }
        }

        chat_name.setStyle("-fx-text-fill: #000000");
        chat_name.setText(actualButton.getText());

        List<Message> allChatMessages = MessageHandler.getAllMessagesInGroup(this.model.getUser().getUid(), groupId, 0, 200);
        Collections.reverse(allChatMessages);

        for (Integer i = 0; i < allChatMessages.size(); i++){
            if (allChatMessages.get(i).getSender().getUid() == this.model.getUser().getUid()){
                ChatGrayElementModel element = new ChatGrayElementModel(allChatMessages.get(i).getMessage(), allChatMessages.get(i).getSender().getUsername(), Instant.ofEpochSecond(allChatMessages.get(i).getTimeSent()).toString());
                HBox hbox = element.getMessageBox();


                messages_list.getChildren().add(hbox);
            } else {
                ChatPinkElementModel element = new ChatPinkElementModel(allChatMessages.get(i).getMessage(), allChatMessages.get(i).getSender().getUsername(), Instant.ofEpochSecond(allChatMessages.get(i).getTimeSent()).toString());
                HBox hbox = element.getMessageBox();
                messages_list.getChildren().add(hbox);
            }

        }

        chat_pane.vvalueProperty().bind(messages_list.heightProperty());

        }

    @FXML
    public void handleSearchChatButton(KeyEvent keyEvent) throws IOException {
        //tu bude zavolana metoda writeChatList(argument); a ako argument bude pouzita metoda na vratenie vyhovujucich vzoriek do ktorej pojde argument search_field.getText()
        System.out.println(search_field.getText());
    }

    @FXML
    public void handleNewChatButton(MouseEvent mouseEvent) throws IOException {
        //ak je dobry nazov chatu
        //chat_name.setText(search_field.getText());
        //chat_name.setVisible(true);
        //chat_name_error.setStyle("-fx-text-fill: transparent");

        //ak neni dobry nazov chatu
        //chat_name_error.setStyle("-fx-text-fill: red");

        rootPane11.setVisible(true);
        rootPane11.setDisable(false);
        chat_screen_box.setDisable(true);
        //chat_screen_box.setVisible(false);
        loadFriends();

    }

    @FXML
    public void handleReportUserButton(MouseEvent mouseEvent) throws IOException {

        rootPane1.setVisible(true);
        rootPane1.setDisable(false);
        chat_screen_box.setDisable(true);
        //chat_screen_box.setVisible(false);
    }




    @FXML
    public void handleSendMessageButton(MouseEvent mouseEvent) throws IOException {
        System.out.println(chat_message.getText());
        //zavolanie metody ktora dostane ako argument id usera a text spravy
    }


    public void handleSendReportButton(MouseEvent mouseEvent) {
        System.out.println(text_area.getText());
        rootPane1.setVisible(false);
        rootPane1.setDisable(true);
        chat_screen_box.setDisable(false);
        chat_screen_box.setVisible(true);
    }

    public void handleSendChatNameButton(MouseEvent mouseEvent) {
        System.out.println(text_area.getText());
        List<String> friends = new ArrayList<>();
        friends = checkedFriends();
        System.out.println(friends);
        rootPane11.setVisible(false);
        rootPane11.setDisable(true);
        chat_screen_box.setDisable(false);
        chat_screen_box.setVisible(true);

    }

    private List<String> checkedFriends() {
        List<String> friends = new ArrayList<>();
        List<Node> hboxes = new ArrayList<>();
        hboxes = friends_vbox.getChildren();

        for (Integer i = 0; i < hboxes.size(); i++){
            if (((CheckBox)((HBox)hboxes.get(i)).getChildren().get(1)).isSelected()){
                friends.add(((Label)((HBox)hboxes.get(i)).getChildren().get(0)).getText());
            }

        }

        return friends;
    }


    @FXML
    private VBox friends_vbox;
    public void loadFriends(){
        List<String> usernames = new ArrayList<>();
        usernames.add("Jano");
        usernames.add("Fero");
        usernames.add("Jano");
        usernames.add("Fero");
        usernames.add("Jano");

        for (Integer i = 0; i < usernames.size(); i++){
            HBox hBox = createFriendBox(usernames.get(i));
            friends_vbox.getChildren().add(hBox);
        }

    }

    public HBox createFriendBox(String username){
        HBox hbox = new HBox();
        Label label = new Label();
        CheckBox checkhbox = new CheckBox();
        label.setText(username);

        label.setMinWidth(419);
        label.prefWidth(419);
        label.prefHeight(46);
        label.setPadding(new Insets(10, 0, 0, 30));



        label.setStyle("-fx-font-weight: bold");
        label.setAlignment(Pos.CENTER_LEFT);
        label.setFont(new Font("Arial", 30));
        label.setStyle("-fx-text-fill: white");


        checkhbox.setStyle("-fx-font-family: Arial");
        checkhbox.setStyle("-fx-font-size: 22");
        checkhbox.setAlignment(Pos.CENTER_LEFT);
        checkhbox.setPadding(new Insets(10, 0, 0, 0));
        hbox.getChildren().add(label);
        hbox.getChildren().add(checkhbox);

        return hbox;
    }

    public void handleCancelReport(MouseEvent mouseEvent){
        rootPane1.setVisible(false);
        rootPane1.setDisable(true);
        chat_screen_box.setDisable(false);
        chat_screen_box.setVisible(true);
    }

    public void handleCancelNewChat(MouseEvent mouseEvent){
        rootPane11.setVisible(false);
        rootPane11.setDisable(true);
        chat_screen_box.setDisable(false);
        chat_screen_box.setVisible(true);
    }


}
