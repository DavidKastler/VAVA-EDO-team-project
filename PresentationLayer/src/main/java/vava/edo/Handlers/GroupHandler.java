package vava.edo.Handlers;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import vava.edo.Exepctions.HttpStatusExceptions.UnexpectedHttpStatusException;
import vava.edo.models.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupHandler {

    //DONE
    public static ArrayList<Group> getAllGroups(Integer userId) {
        ArrayList<Group> groups = new ArrayList<>();

        try {
            HttpResponse<JsonNode> groupsJson = Unirest.get("http://localhost:8080/groupMembers/groups?token={token}")
                    .routeParam("token", String.valueOf(userId))
                    .asJson();

            if (groupsJson.getStatus() != 200) throw new UnexpectedHttpStatusException(groupsJson.getStatus(), 200);

            for (Object group: groupsJson.getBody().getArray()){
                groups.add(new Gson().fromJson(group.toString(), Group.class));
            }

        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }

        return groups;
    }

    public static void createGroup(Integer userId, List<Integer> memberIds, String groupName) {
        JSONObject newGroup = new JSONObject();
        newGroup.put("creatorId", userId);
        newGroup.put("groupName", groupName);

        try {
            HttpResponse<JsonNode> groupJson = Unirest.post("http://localhost:8080/groups/create?token={token}")
                    .header("Content-type", "application/json")
                    .routeParam("token", String.valueOf(userId))
                    .body(newGroup)
                    .asJson();

            if (groupJson.getStatus() != 201) throw new UnexpectedHttpStatusException(groupJson.getStatus(), 201);

        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }

    }
}
