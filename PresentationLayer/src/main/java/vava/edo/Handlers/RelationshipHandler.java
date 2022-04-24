package vava.edo.Handlers;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import vava.edo.Exepctions.HttpStatusExceptions.UnexpectedHttpStatusException;
import vava.edo.models.Group;
import vava.edo.models.Relationship;
import vava.edo.models.User;

import java.util.ArrayList;

public class RelationshipHandler {

    /**
     * Method used to return all users friends
     * @param userId    id of user whose friends we want to return
     * @return  ArrayList of Relationship objects containing usernames and ids of friends
     */
    public static ArrayList<Relationship> getAllFriends(Integer userId) {
        ArrayList<Relationship> friends = new ArrayList<>();

        try {
            HttpResponse<JsonNode> friendsJson = Unirest.get("http://localhost:8080/relationships/friends?token={token}")
                    .routeParam("token", String.valueOf(userId))
                    .asJson();
            for (Object group : friendsJson.getBody().getArray()) {
                friends.add(new Gson().fromJson(group.toString(), Relationship.class));
            }

            if (friendsJson.getStatus() != 200) throw new UnexpectedHttpStatusException(friendsJson.getStatus(), 200, friendsJson.getStatusText());

        } catch (UnirestException e) {
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }

        return friends;
    }

    /**
     * Method used to return all pending friend requests for user
     * @param userId    id of user whose friend requests we are searching for
     * @return  ArrayList of relationship objects containing usernames and ids of friends
     */
    public static ArrayList<Relationship> getAllRequests(Integer userId) {
        ArrayList<Relationship> requests = new ArrayList<>();

        try {
            HttpResponse<JsonNode> friendsJson = Unirest.get("http://localhost:8080/relationships/requests?token={token}")
                    .routeParam("token", String.valueOf(userId))
                    .asJson();
            for (Object group : friendsJson.getBody().getArray()) {
                requests.add(new Gson().fromJson(group.toString(), Relationship.class));
            }

            if (friendsJson.getStatus() != 200) throw new UnexpectedHttpStatusException(friendsJson.getStatus(), 200, friendsJson.getStatusText());

        } catch (UnirestException e) {
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }

        return requests;
    }

    /**
     * Method used to create and send a new friend request
     * @param userId    id of user who wants to add a friend
     * @param friendUsername    username of friend who we want to add
     */
    public static void createFriendRequest(Integer userId, String friendUsername) {

        JSONObject newFriendRequest = new JSONObject();
        newFriendRequest.put("senderId", userId);
        newFriendRequest.put("receiverName", friendUsername);

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

    //DONE
    public static void rejectRequest(Integer userId, Integer requestId) {

        try {
            HttpResponse<JsonNode> rejectRequestJson = Unirest.delete("http://localhost:8080/relationships/delete/{request_id}?token={token}")
                    .header("Content-type", "application/json")
                    .routeParam("token", String.valueOf(userId))
                    .routeParam("request_id", String.valueOf(requestId))
                    .asJson();

            if (rejectRequestJson.getStatus() != 200) throw new UnexpectedHttpStatusException(rejectRequestJson.getStatus(), 200, rejectRequestJson.getStatusText());

        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }

    }

    //DONE
    public static void blockUser(Integer userId, Integer requestId) {

        try {
            HttpResponse<JsonNode> blockUserJson = Unirest.put("http://localhost:8080/relationships/block/{request_id}?token={token}")
                    .header("Content-type", "application/json")
                    .routeParam("token", String.valueOf(userId))
                    .routeParam("request_id", String.valueOf(requestId))
                    .asJson();

            if (blockUserJson.getStatus() != 200) throw new UnexpectedHttpStatusException(blockUserJson.getStatus(), 200, blockUserJson.getStatusText());

        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }
    }

}
