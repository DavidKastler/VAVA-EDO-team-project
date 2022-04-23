package vava.edo.Handlers;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import vava.edo.Exepctions.HttpStatusExceptions.UnexpectedHttpStatusException;
import vava.edo.models.Group;
import vava.edo.models.User;

import java.util.ArrayList;

public class RelationshipHandler {

    public static ArrayList<User> getAllFriends(Integer userId) {
        ArrayList<User> friends = new ArrayList<>();

        try {
            HttpResponse<JsonNode> friendsJson = Unirest.get("http://localhost:8080/relationships/friends?token={token}")
                    .routeParam("token", String.valueOf(userId))
                    .asJson();
            for (Object group : friendsJson.getBody().getArray()) {
                friends.add(new Gson().fromJson(group.toString(), User.class));
            }

            if (friendsJson.getStatus() != 200) throw new UnexpectedHttpStatusException(friendsJson.getStatus(), 200);

        } catch (UnirestException e) {
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }

        return friends;
    }

    /*public static void createRequest(Integer userId) {

        try {
            HttpResponse<JsonNode> friendsJson = Unirest.get("http://localhost:8080/relationships/friends?token={token}")
                    .routeParam("token", String.valueOf(userId))
                    .asJson();
            for (Object group : friendsJson.getBody().getArray()) {
                friends.add(new Gson().fromJson(group.toString(), User.class));
            }

            if (friendsJson.getStatus() != 200) throw new UnexpectedHttpStatusException(friendsJson.getStatus(), 200);

        } catch (UnirestException e) {
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void acceptRequest(Integer userId) {

        try {
            HttpResponse<JsonNode> friendsJson = Unirest.get("http://localhost:8080/relationships/friends?token={token}")
                    .routeParam("token", String.valueOf(userId))
                    .asJson();
            for (Object group : friendsJson.getBody().getArray()) {
                friends.add(new Gson().fromJson(group.toString(), User.class));
            }

            if (friendsJson.getStatus() != 200) throw new UnexpectedHttpStatusException(friendsJson.getStatus(), 200);

        } catch (UnirestException e) {
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void rejectRequest(Integer userId) {

        try {
            HttpResponse<JsonNode> friendsJson = Unirest.get("http://localhost:8080/relationships/friends?token={token}")
                    .routeParam("token", String.valueOf(userId))
                    .asJson();
            for (Object group : friendsJson.getBody().getArray()) {
                friends.add(new Gson().fromJson(group.toString(), User.class));
            }

            if (friendsJson.getStatus() != 200) throw new UnexpectedHttpStatusException(friendsJson.getStatus(), 200);

        } catch (UnirestException e) {
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void blockUser(Integer userId) {

        try {
            HttpResponse<JsonNode> friendsJson = Unirest.get("http://localhost:8080/relationships/friends?token={token}")
                    .routeParam("token", String.valueOf(userId))
                    .asJson();
            for (Object group : friendsJson.getBody().getArray()) {
                friends.add(new Gson().fromJson(group.toString(), User.class));
            }

            if (friendsJson.getStatus() != 200) throw new UnexpectedHttpStatusException(friendsJson.getStatus(), 200);

        } catch (UnirestException e) {
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void deleteFriend(Integer userId) {

        try {
            HttpResponse<JsonNode> friendsJson = Unirest.get("http://localhost:8080/relationships/friends?token={token}")
                    .routeParam("token", String.valueOf(userId))
                    .asJson();
            for (Object group : friendsJson.getBody().getArray()) {
                friends.add(new Gson().fromJson(group.toString(), User.class));
            }

            if (friendsJson.getStatus() != 200) throw new UnexpectedHttpStatusException(friendsJson.getStatus(), 200);

        } catch (UnirestException e) {
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }


    }

     */

}
