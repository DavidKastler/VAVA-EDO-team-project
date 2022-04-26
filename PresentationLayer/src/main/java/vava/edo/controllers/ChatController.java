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
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import vava.edo.Handlers.*;
import vava.edo.controllers.models.ChatScreenModel;
import vava.edo.models.*;
import vava.edo.models.ChatGrayElementModel;
import vava.edo.models.ChatPinkElementModel;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ChatController implements Initializable {
    public MouseEvent getEvent() {
        return event;
    }

    public void setEvent(MouseEvent event) {
        this.event = event;
    }

    private MouseEvent event;

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
    private TextArea text_area1;


    @FXML
    private Label chat_name_error;

    private ChatScreenModel model;

    public void setModel(ChatScreenModel model) {
        this.model = model;
    }

    List<Group> currentlyShownGroups = null;

    private int messagesToLoad;

    private Integer currentGroup;


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
                this.currentGroup = groupId;
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
        messages_list.heightProperty().addListener(observable -> chat_pane.setVvalue(1D));
    }

    public void loadAllGroups() {
        this.currentlyShownGroups = GroupHandler.getAllGroups(this.model.getUser().getUid());
        reloadAllGroups();
    }

    public void reloadAllGroups() {

        try {
            List<Group> searchedGroups = (List<Group>) (List) SearchHandler.searchInList(currentlyShownGroups, "groupName", search_field.getText());
            writeChatList(searchedGroups);
        } catch (Exception e) {};

    }

    public void writeChatList(List<Group> usernames){
        this.chat_list_pane.getChildren().clear();
        for (int i = 0; i < usernames.size(); i++){
            Button button = new Button();
            Boolean color;
            String username;
            if (i % 2 == 0){
                color = false;
            } else {
                color = true;
            }
            editButton(button, usernames.get(i).getGroupName(), color, usernames.get(i).getGroupId());

            this.chat_list_pane.getChildren().add(button);
        }
    }

    @FXML
    public void handleViewChatButton(MouseEvent mouseEvent, Integer groupId) throws IOException {

        this.messagesToLoad = 5;
        messages_list.getChildren().clear();
        refreshColorsOfChats(this.chat_list_pane.getChildren());

        Button actualButton = ((Button)mouseEvent.getSource());

        for (Integer i = 0; i < this.chat_list_pane.getChildren().size(); i++){
            if (actualButton.equals(this.chat_list_pane.getChildren().get(i))){
                this.chat_list_pane.getChildren().get(i).setStyle("-fx-background-color:  #ff9797");
            }
        }

        chat_name.setStyle("-fx-text-fill: #000000");
        chat_name.setText(actualButton.getText());

        loadMessages(messagesToLoad, groupId);

    }

    public void loadMessages(int amount, Integer groupId) {
        messages_list.getChildren().clear();
        List<Message> allChatMessages = MessageHandler.getAllMessagesInGroup(this.model.getUser().getUid(), groupId, 0, amount);
        Collections.reverse(allChatMessages);

        for (Integer i = 0; i < allChatMessages.size(); i++){
            if (allChatMessages.get(i).getSender().getUid() == this.model.getUser().getUid()){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
                Date messageDate = Date.from(Instant.ofEpochSecond(allChatMessages.get(i).getTimeSent()));
                String currentDate = sdf.format(messageDate);
                ChatGrayElementModel element = new ChatGrayElementModel(allChatMessages.get(i).getMessage(), allChatMessages.get(i).getSender().getUsername(), currentDate);
                HBox hbox = element.getMessageBox();


                messages_list.getChildren().add(hbox);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
                Date messageDate = Date.from(Instant.ofEpochSecond(allChatMessages.get(i).getTimeSent()));
                String currentDate = sdf.format(messageDate);
                ChatPinkElementModel element = new ChatPinkElementModel(allChatMessages.get(i).getMessage(), allChatMessages.get(i).getSender().getUsername(), currentDate);
                HBox hbox = element.getMessageBox();
                messages_list.getChildren().add(hbox);
            }


        }
    }

    @FXML
    public void handleSearchChatButton(KeyEvent keyEvent) throws IOException {
        reloadAllGroups();
    }

    @FXML
    public void handleNewChatButton(MouseEvent mouseEvent) throws IOException {
        rootPane11.setVisible(true);
        rootPane11.setDisable(false);
        chat_screen_box.setDisable(true);
        //chat_screen_box.setVisible(false);
        loadFriends();

    }

    @FXML
    public void handleReportUserButton(MouseEvent mouseEvent) throws IOException {

        loadMessages(messagesToLoad, currentGroup);
        chat_pane.setVvalue(1D);
        loadAllGroups();

    }




    @FXML
    public void handleSendMessageButton(MouseEvent mouseEvent) throws IOException {
        MessageHandler.sendMessage(this.model.getUser().getUid(), currentGroup, chat_message.getText());
        loadMessages(messagesToLoad, currentGroup);
        chat_message.clear();
        loadAllGroups();
    }


    public void handleSendReportButton(MouseEvent mouseEvent) {

    }

    public void handleSendChatNameButton(MouseEvent mouseEvent) {
        List<Integer> selectedFriends = returnSelectedFriends();
        Integer[] friendsInt = new Integer[selectedFriends.size()+1];

        for (int i = 0; i < selectedFriends.size(); i++) {
            friendsInt[i] = selectedFriends.get(i);
        }
        friendsInt[friendsInt.length-1] = this.model.getUser().getUid();

        GroupHandler.createGroup(this.model.getUser().getUid(), friendsInt, text_area1.getText());

        rootPane11.setVisible(false);
        rootPane11.setDisable(true);
        chat_screen_box.setDisable(false);
        chat_screen_box.setVisible(true);

        loadAllGroups();

    }

    private List<Integer> returnSelectedFriends() {
        List<Integer> friends = new ArrayList<>();
        List<HBoxWithProperty> hboxes = new ArrayList<>();
        hboxes = (List)friends_vbox.getChildren();

        for (Integer i = 0; i < hboxes.size(); i++){
            if (((CheckBox)(hboxes.get(i)).getChildren().get(1)).isSelected()){
                try {
                    friends.add(hboxes.get(i).getProperty().getUserId());
                } catch (Exception e) {};
            }

        }

        return friends;
    }


    @FXML
    private VBox friends_vbox;

    public void loadFriends(){
        friends_vbox.getChildren().clear();
        List <Relationship> friends = RelationshipHandler.getAllFriends(this.model.getUser().getUid());

        for (Integer i = 0; i < friends.size(); i++){
            HBoxWithProperty hBox = createFriendBox(friends.get(i));
            friends_vbox.getChildren().add(hBox);
        }

    }

    public HBoxWithProperty createFriendBox(Relationship relationship){

        HBoxWithProperty hbox = new HBoxWithProperty();
        Label label = new Label();
        CheckBox checkBox = new CheckBox();
        label.setText(relationship.getUserName());

        label.setMinWidth(419);
        label.prefWidth(419);
        label.prefHeight(46);
        label.setPadding(new Insets(10, 0, 0, 30));

        label.setStyle("-fx-font-weight: bold");
        label.setAlignment(Pos.CENTER_LEFT);
        label.setFont(new Font("Arial", 30));
        label.setStyle("-fx-text-fill: white");


        checkBox.setStyle("-fx-font-family: Arial");
        checkBox.setStyle("-fx-font-size: 22");
        checkBox.setAlignment(Pos.CENTER_LEFT);
        checkBox.setPadding(new Insets(10, 0, 0, 0));
        hbox.getChildren().add(label);
        hbox.getChildren().add(checkBox);
        hbox.setProperty(relationship);

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


    public void checkIfOnTop(ScrollEvent scrollEvent) {

        if (chat_pane.getVvalue() == chat_pane.getVmin()){
            messagesToLoad += 5;
            loadMessages(messagesToLoad, currentGroup);
            chat_pane.setVvalue(1D);
        }

    }
}
