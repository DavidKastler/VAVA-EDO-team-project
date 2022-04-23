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
import vava.edo.schema.MessageDto;


import java.util.ArrayList;

public class MessageHandler {

    //DONE
    public static ArrayList<Group> getAllGroups(Integer userId)
    {
        ArrayList<Group> groups = new ArrayList<>();

        try {
            HttpResponse<JsonNode> groupsJson = Unirest.get("http://localhost:8080/groupMembers/groups?token={token}")
                    .routeParam("token", String.valueOf(userId))
                    .asJson();

            if (groupsJson.getStatus() != 200) throw new UnexpectedHttpStatusException(groupsJson.getStatus());

            for(Object group: groupsJson.getBody().getArray()){
                groups.add(new Gson().fromJson(group.toString(), Group.class));
            }

        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }

        return groups;
    }

    //DONE
    public static ArrayList<Message> getAllMessagesInGroup(Integer userId, Integer groupId)
    {
        ArrayList<Message> messages = new ArrayList<>();

        try {
            HttpResponse<JsonNode> messagesJson = Unirest.get("http://localhost:8080/chats/get/{group_id}?token={token}")
                    .routeParam("token", String.valueOf(userId))
                    .routeParam("group_id", String.valueOf(groupId))
                    .asJson();

            if (messagesJson.getStatus() != 200) throw new UnexpectedHttpStatusException(messagesJson.getStatus());

            for(Object message: messagesJson.getBody().getArray()){
                messages.add(new Gson().fromJson(message.toString(), Message.class));
            }

        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }

        return messages;
    }


    public static void sendMessage(Integer userId, Integer groupId, String message)
    {
        JSONObject newMessage = new JSONObject();

        try {
            HttpResponse<JsonNode> messageJson = Unirest.post("http://localhost:8080/chats/send?token={token}").header("Content-type", "application/json")
                    .routeParam("token", String.valueOf(userId))
                    .body(newMessage.toString())
                    .asJson();
        }catch (UnirestException e){
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        }

    }



}
