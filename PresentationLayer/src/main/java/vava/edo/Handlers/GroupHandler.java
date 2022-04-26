package vava.edo.Handlers;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import vava.edo.Exepctions.HttpStatusExceptions.UnexpectedHttpStatusException;
import vava.edo.models.Group;
import vava.edo.models.ResponseMessage;

import java.util.ArrayList;
import java.util.Arrays;

public class GroupHandler {

    /**
     * Method that returns all users groups
     * @param userId    id of user whose groups we want to return
     * @return  ArrayList of group objects containing group names and ids
     */
    public static ArrayList<Group> getAllGroups(Integer userId) {
        ArrayList<Group> groups = new ArrayList<>();

        try {
            HttpResponse<JsonNode> groupsJson = Unirest.get("http://localhost:8080/chats/get/recent?token={token}")
                    .routeParam("token", String.valueOf(userId))
                    .asJson();

            ResponseMessage responseMessage = null;
            if (groupsJson.getBody().getObject() != null) {
            responseMessage = new Gson().fromJson(groupsJson.getBody().getObject().toString(), ResponseMessage.class); }
            if (groupsJson.getStatus() != 200) throw new UnexpectedHttpStatusException(groupsJson.getStatus(), 200, responseMessage.getMessage());

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

    /**
     * Method used to create a new group from users in Friendlist
     * @param userId    is of group creator
     * @param memberIds     ids o new group members, must also contain creators id
     * @param groupName     name of newly created group
     */
    public static void createGroup(Integer userId, Integer[] memberIds, String groupName) {
        JSONObject newGroup = new JSONObject();
        newGroup.put("creatorId", userId);
        newGroup.put("groupName", groupName);

        try {
            HttpResponse<JsonNode> groupJson = Unirest.post("http://localhost:8080/groups/create?token={token}")
                    .header("Content-type", "application/json")
                    .routeParam("token", String.valueOf(userId))
                    .body(newGroup)
                    .asJson();

            if (groupJson.getStatus() != 201) throw new UnexpectedHttpStatusException(groupJson.getStatus(), 201, groupJson.getStatusText());

            Group createdGroup = new Gson().fromJson(groupJson.getBody().toString(), Group.class);

            HttpResponse<JsonNode> membersJson = Unirest.post("http://localhost:8080/groupMembers/members/add/{group_id}?token={token}")
                    .header("Content-type", "application/json")
                    .routeParam("token", String.valueOf(userId))
                    .routeParam("group_id", String.valueOf(createdGroup.getGroupId()))
                    .body(Arrays.toString(memberIds))
                    .asJson();

            if (membersJson.getStatus() != 201) throw new UnexpectedHttpStatusException(membersJson.getStatus(), 201, membersJson.getStatusText());

            MessageHandler.sendMessage(userId, createdGroup.getGroupId(), "Created new group with name " + createdGroup.getGroupName());

        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }

    }
}
