package vava.edo.controllers;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ReportScreenController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private VBox report_messages;

    @FXML
    private Button report_button;



    public ReportScreenController() {

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

        //List<Node> listOfMessages = new ArrayList<>();
        //listOfMessages = messages_list.getChildren();

        List<String> receivedMessages = new ArrayList<>();

        Integer j = 10;

        for (Integer i = messageList.size() - 1; i >= 0; i--){
            if (!(userIdList.get(i).equals("13"))){
                if (j < 1){
                    break;
                }
                receivedMessages.add(messageList.get(i));
                j--;


            }
        }

        for (Integer i = 0; i < receivedMessages.size(); i++){
            report_messages.getChildren().get(i).setVisible(true);
            ((CheckBox)report_messages.getChildren().get(i)).setText(receivedMessages.get(i));
        }

    }

    public void handleSendReportButton(javafx.scene.input.MouseEvent mouseEvent) {
        List<Node> allMessagesCheckBox = report_messages.getChildren();
        List<String> reportedMessages = new ArrayList<>();
        for (Integer i = 0; i < allMessagesCheckBox.size(); i++){
            if (((CheckBox)allMessagesCheckBox.get(i)).isSelected()){
                reportedMessages.add(((CheckBox)allMessagesCheckBox.get(i)).getText());
            }
        }
        System.out.println(reportedMessages);

        Stage stage = (Stage) report_button.getScene().getWindow();
        stage.close();
    }
}
