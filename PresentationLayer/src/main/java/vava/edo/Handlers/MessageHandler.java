package vava.edo.Handlers;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import vava.edo.models.Group;
import vava.edo.models.Message;
import vava.edo.schema.MessageDto;


import java.util.ArrayList;

public class MessageHandler {

    public static ArrayList<Group> getAllGroups(Integer userId)
    {
        ArrayList<Group> groups = new ArrayList<>();

        try {
            HttpResponse<JsonNode> groupsJson = Unirest.get("http://localhost:8080/groupMembers/groups?token={token}")
                    .routeParam("token", String.valueOf(userId))
                    .asJson();
            for(Object group: groupsJson.getBody().getArray()){
                groups.add(new Gson().fromJson(group.toString(), Group.class));
            }

        }catch (UnirestException e){
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        }

        return groups;
    }

    public static ArrayList<Message> getAllMessagesInGroup(Integer userId, Integer groupId)
    {
        ArrayList<Message> messages = new ArrayList<>();

        try {
            HttpResponse<JsonNode> groupsJson = Unirest.get("http://localhost:8080/chats/get/{group_id}?token={token}")
                    .routeParam("token", String.valueOf(userId))
                    .routeParam("group_id", String.valueOf(groupId))
                    .asJson();
            for(Object message: groupsJson.getBody().getArray()){
                messages.add(new Gson().fromJson(message.toString(), Message.class));
            }

        }catch (UnirestException e){
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        }

        return messages;
    }

    public static void sendMessage(Integer userId, Integer groupId, String message)
    {
        MessageDto newMessage = new MessageDto(userId, groupId, message);

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
