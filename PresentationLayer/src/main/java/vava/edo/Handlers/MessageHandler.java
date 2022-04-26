package vava.edo.Handlers;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import vava.edo.Exepctions.HttpStatusExceptions.UnexpectedHttpStatusException;
import vava.edo.models.Group;
import vava.edo.models.Message;


import java.time.Instant;
import java.util.ArrayList;

public class MessageHandler {

    /**
     * Method used to fetch all messages in given group
     * @param userId    id of user who sent this request
     * @param groupId   id of group containing messages we wish to return
     * @param fromIndex     index of first message we want to return, usually 0 at the beginning
     * @param size      maximum number of messages we want to return
     * @return      ArrayList of message objects containing time sent, message and sender name
     */
    public static ArrayList<Message> getAllMessagesInGroup(Integer userId, Integer groupId, Integer fromIndex, Integer size) {
        ArrayList<Message> messages = new ArrayList<>();

        try {
            HttpResponse<JsonNode> messagesJson = Unirest.get("http://localhost:8080/chats/get/{group_id}?token={token}&from={from}&size={size}")
                    .routeParam("token", String.valueOf(userId))
                    .routeParam("group_id", String.valueOf(groupId))
                    .routeParam("from", String.valueOf(fromIndex))
                    .routeParam("size", String.valueOf(size))
                    .asJson();

            if (messagesJson.getStatus() != 200) throw new UnexpectedHttpStatusException(messagesJson.getStatus(), 200, messagesJson.getStatusText());

            for (Object message: messagesJson.getBody().getArray()){
                messages.add(new Gson().fromJson(message.toString(), Message.class));
            }

        } catch (UnirestException | UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }

        return messages;
    }

    //TODO DAT PREC 1000

    /**
     * Method used to create a new message in group
     * @param userId    id of message sender
     * @param groupId   id of group where we want to send message
     * @param message   string containing our message
     */
    public static void sendMessage(Integer userId, Integer groupId, String message) {
        JSONObject newMessage = new JSONObject();
        newMessage.put("senderId", userId);
        newMessage.put("groupId", groupId);
        newMessage.put("message", message);

        try {
            HttpResponse<JsonNode> messageJson = Unirest.post("http://localhost:8080/chats/send?token={token}")
                    .header("Content-type", "application/json")
                    .routeParam("token", String.valueOf(userId))
                    .body(newMessage)
                    .asJson();

            if (messageJson.getStatus() != 201) throw new UnexpectedHttpStatusException(messageJson.getStatus(), 201, messageJson.getStatusText());

        }catch (UnirestException e){
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }

    }



}
