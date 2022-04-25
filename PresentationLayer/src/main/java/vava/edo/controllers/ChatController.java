package vava.edo.controllers;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import vava.edo.models.ChatGrayElementModel;
import vava.edo.models.ChatPinkElementModel;
import vava.edo.models.ManagerViewElementModel;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


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


    public ChatController() {

    }


    public Button editButton(Button button, String username, Boolean color){
        button.setText(username);
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
                handleViewChatButton(event);
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

    public void writeChatList(List<String> usernames){
        for (int i = 0; i < usernames.size(); i++){
            //System.out.println(id1);
            Button button = new Button();
            Boolean color;
            String username;
            if (i % 2 == 0){
                color = false;
            } else {
                color = true;
            }
            editButton(button, usernames.get(i), color);


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
        try {
            URL checkConnectionURL = new URL("http://www.google.com");
            URLConnection checkConnection = checkConnectionURL.openConnection();
            checkConnection.connect();
            System.out.println("Internet is connected");
        } catch (IOException e) {
            System.out.println("Internet is not connected");
        }

        /*
        //**** user 1 ***
        String id1 = "1234";
        String username1 = "Jano";
        String password1 = "";
        String role1 = "";
        String rememberMe1 = "";
        String isLogged1 = "";
        String lastActivity1 = "";

        //**** user 2 ***
        String id2 = "1235";
        String username2 = "Fero";
        String password2 = "";
        String role2 = "";
        String rememberMe2 = "";
        String isLogged2 = "";
        String lastActivity2 = "";
        */
        List<String> usernames = new ArrayList<>();
        usernames.add("Jano");
        usernames.add("Fero");
        usernames.add("Jano");
        usernames.add("Fero");
        usernames.add("Jano");
        usernames.add("Jano");
        usernames.add("Jano");
        usernames.add("Fero");
        usernames.add("Jano");
        usernames.add("Fero");
        usernames.add("Fero");
        usernames.add("Jano");
        usernames.add("Fero");

        writeChatList(usernames);





    }

    @FXML
    public void handleViewChatButton(MouseEvent mouseEvent) throws IOException {
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
        /*
        List<String> userIdList = new ArrayList<>();
        List<String> messageList = new ArrayList<>();

        userIdList.add("12");
        userIdList.add("13");
        userIdList.add("12");
        userIdList.add("13");
        userIdList.add("13");
        userIdList.add("12");
        userIdList.add("13");
        userIdList.add("12");
        userIdList.add("12");
        userIdList.add("13");
        userIdList.add("12");

        messageList.add("ivbiedv widbbs jodncsd  djnsdb b" );
        messageList.add("Fero b");
        messageList.add("sdlibdb i dbhb  kdjn j");
        messageList.add("dlkhbvdk; jdkcjkdbc dbcd");
        messageList.add("dk;jbjcks jcdbskh sbjdchkdb hdbchdbc hbdchdbcd ");
        messageList.add("adlcbsdl ascbiasb aiusbc sbclb acslkhhbc");
        messageList.add("lkcsbjdsahbcl sncbbak aksbcaslb bcsailcb");
        messageList.add("acsjhbs aksjcba ajsbcc kajhsbcc");
        messageList.add("kjbjccakl");
        messageList.add("ljkachbsdc aihsc akshcbh aisbccc");
        messageList.add("jkch asausbcli asbcb");

        writeChatMessages(userIdList, messageList);
        */

        List<String> usernames = new ArrayList<>();
        usernames.add("Jano");
        usernames.add("Fero");
        usernames.add("Jano");
        usernames.add("Fero");
        usernames.add("Jano");
        usernames.add("Jano");
        usernames.add("Jano");
        usernames.add("Fero");
        usernames.add("Jano");
        usernames.add("Fero");
        usernames.add("Fero");
        usernames.add("Jano");
        usernames.add("Fero");

        List<String> time = new ArrayList<>();
        time.add("12");
        time.add("13");
        time.add("14");
        time.add("15");
        time.add("16");
        time.add("17896");
        time.add("1234");
        time.add("1264");
        time.add("12346");
        time.add("12");
        time.add("16");
        time.add("17896");
        time.add("32");

        List<String> messages = new ArrayList<>();
        messages.add("aa aa aa aa aa aa aa aa aa aa aa aa aa a  a a a a");
        messages.add("ascljhauco ubABX aksjcba aksjcb c");
        messages.add("ASCLIUL alicsb askcb sabc ablciscbasl lsciablcsi ");
        messages.add("askcsb  alisc lsbca clacbk avzlcacbasilcb");
        messages.add("akcs aslibalc acsalibusc ailscascv cas");
        messages.add("acskhjhavsc ac vkau cvavkc");
        messages.add("sjdvb asocuaocbaj cabc");
        messages.add("avdascsc ascasca asdvdvsd sdvasdvd sdvdsv avvd");
        messages.add("asau baicb isc b a aisbc");
        messages.add("asckba liscb abscbl acsbscl");
        messages.add("alkcb acbla c vascia cs");
        messages.add("as,chal cbjcva scv  ua acscacca asc kavc");
        messages.add(",ajsvc avc a lcsivkausvca vavsc  acsn");

        for (Integer i = 0; i < usernames.size(); i++){
            if (usernames.get(i).equals("Jano")){
                ChatGrayElementModel element = new ChatGrayElementModel(messages.get(i), usernames.get(i), time.get(i));
                HBox hbox = element.getMessageBox();


                messages_list.getChildren().add(hbox);
            } else {
                ChatPinkElementModel element = new ChatPinkElementModel(messages.get(i), usernames.get(i), time.get(i));
                HBox hbox = element.getMessageBox();
                messages_list.getChildren().add(hbox);
            }


        }


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
        rootPane11.setVisible(false);
        rootPane11.setDisable(true);
        chat_screen_box.setDisable(false);
        chat_screen_box.setVisible(true);
    }


}
