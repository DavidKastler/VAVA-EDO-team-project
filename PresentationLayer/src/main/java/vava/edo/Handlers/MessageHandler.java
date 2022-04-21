package vava.edo.Handlers;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import vava.edo.models.Group;
import vava.edo.models.Todo;


import java.util.ArrayList;

public class MessageHandler {

    private static ArrayList<Group> getAllGroups(Integer uid)
    {
        ArrayList<Group> tasks = new ArrayList<>();

        try {
            HttpResponse<JsonNode> tasksJson = Unirest.get("http://localhost:8080/chat/{uid}?token={token}")
                    .routeParam("uid", String.valueOf(uid))
                    .routeParam("token", String.valueOf(uid))
                    .asJson();

            for(Object task: tasksJson.getBody().getArray()){
                tasks.add(new Gson().fromJson(task.toString(), Group.class));
            }

        }catch (UnirestException e){
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        }

        return tasks;
    }

}
