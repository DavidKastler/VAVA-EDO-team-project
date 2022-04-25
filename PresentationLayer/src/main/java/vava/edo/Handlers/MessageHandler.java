package vava.edo.Handlers;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import vava.edo.models.Group;
import vava.edo.models.Message;


import java.util.ArrayList;

public class MessageHandler {

    public static ArrayList<Group> getAllGroups(Integer userId)
    {
        ArrayList<Group> groups = new ArrayList<>();

        try {
            HttpResponse<JsonNode> tasksJson = Unirest.get("http://localhost:8080/chat/all?token={token}")
                    .routeParam("token", String.valueOf(userId))
                    .asJson();
            for(Object group: tasksJson.getBody().getArray()){
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
            HttpResponse<JsonNode> tasksJson = Unirest.get("http://localhost:8080/chat/all?token={token}")
                    .routeParam("token", String.valueOf(userId))
                    .asJson();
            for(Object message: tasksJson.getBody().getArray()){
                messages.add(new Gson().fromJson(message.toString(), Message.class));
            }

        }catch (UnirestException e){
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        }

        return messages;
    }



}
