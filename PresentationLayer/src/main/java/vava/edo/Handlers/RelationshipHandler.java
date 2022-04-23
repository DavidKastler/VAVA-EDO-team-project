package vava.edo.Handlers;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import vava.edo.Exepctions.HttpStatusExceptions.UnexpectedHttpStatusException;
import vava.edo.models.Group;
import vava.edo.models.User;

import java.util.ArrayList;

public class RelationshipHandler {

    //DONE
    public static ArrayList<User> getAllFriends(Integer userId) {
        ArrayList<User> friends = new ArrayList<>();

        try {
            HttpResponse<JsonNode> friendsJson = Unirest.get("http://localhost:8080/relationships/friends?token={token}")
                    .routeParam("token", String.valueOf(userId))
                    .asJson();
            for (Object group : friendsJson.getBody().getArray()) {
                friends.add(new Gson().fromJson(group.toString(), User.class));
            }

            if (friendsJson.getStatus() != 200) throw new UnexpectedHttpStatusException(friendsJson.getStatus(), 200, friendsJson.getStatusText());

        } catch (UnirestException e) {
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }

        return friends;
    }

    public static void createFriendRequest(Integer userId, Integer friendId) {

        JSONObject newFriendRequest = new JSONObject();
        newFriendRequest.put("senderId", userId);
        newFriendRequest.put("receiverId", friendId);

        try {
            HttpResponse<JsonNode> friendJson = Unirest.post("http://localhost:8080/relationships/create?token={token}")
                    .header("Content-type", "application/json")
                    .routeParam("token", String.valueOf(userId))
                    .body(newFriendRequest)
                    .asJson();

            if (friendJson.getStatus() != 201) throw new UnexpectedHttpStatusException(friendJson.getStatus(), 201, friendJson.getStatusText());

        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }

    }

    //DONE
    public static void acceptRequest(Integer userId, Integer requestId) {

        try {
            HttpResponse<JsonNode> acceptRequestJson = Unirest.put("http://localhost:8080/relationships/accept/{request_id}?token={token}")
                    .header("Content-type", "application/json")
                    .routeParam("token", String.valueOf(userId))
                    .routeParam("request_id", String.valueOf(requestId))
                    .asJson();

            if (acceptRequestJson.getStatus() != 200) throw new UnexpectedHttpStatusException(acceptRequestJson.getStatus(), 200, acceptRequestJson.getStatusText());

        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void rejectRequest(Integer userId) {

    }

    public static void blockUser(Integer userId) {

    }

    public static void deleteFriend(Integer userId) {

    }



}
