package vava.edo.Handlers;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.JSONObject;
import vava.edo.Exepctions.HttpStatusExceptions.UnexpectedHttpStatusException;
import vava.edo.Exepctions.LoginScreen.EmptyLoginFields;
import vava.edo.Exepctions.LoginScreen.IncorrectCredentials;
import vava.edo.Exepctions.MenuScreen.FailedToUpdateUser;
import vava.edo.models.User;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;

public class UserHandler {

    /**
     * Method which is responsible for validation and logging of the user into the system
     *
     * @param username Username entered via textField in the frontend
     * @param password Password entered via passwordField in the frontend
     * @param wrongCredentials Object of label class to show display wrong credentials error
     *
     * @return returning a object of logged in user
     */
    public static User loginUser(TextField username, PasswordField password, Label wrongCredentials) throws EmptyLoginFields, IncorrectCredentials {

        User user;

        if(!Objects.equals(username.getText(), "") && !Objects.equals(password.getText(), "")){

            JSONObject jo = new JSONObject();
            jo.put("username", username.getText());
            jo.put("password", password.getText());
            System.out.println(jo);

            try {
                HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/users/login")
                        .header("Content-Type", "application/json")
                        .body(jo)
                        .asJson();
                user = new Gson().fromJson(apiResponse.getBody().toString(), User.class);

                if(user.getUsername() != null){
                    user.setLogged(true);
                    user.setLastActivity(Instant.now().getEpochSecond());
                    System.out.println("Logged in\t->\t" + user);
                    wrongCredentials.setVisible(false);

                    return user;
                }
                else {
                    throw new IncorrectCredentials("Incorrect credentials", wrongCredentials);
                }

            }
            catch (UnirestException e){
                System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
            }
        }
        else {
            throw new EmptyLoginFields("Login fields are left empty", wrongCredentials);
        }

        return null;
    }

    public static void deleteUser(Integer userId, Integer deletedUserId) {
        try {
            HttpResponse<JsonNode> deleteUserJson = Unirest.delete("http://localhost:8080/users/delete/{user_id}?token={token}")
                    .header("Content-type", "application/json")
                    .routeParam("token", String.valueOf(userId))
                    .routeParam("user_id", String.valueOf(deletedUserId))
                    .asJson();

            if (deleteUserJson.getStatus() != 204) throw new UnexpectedHttpStatusException(deleteUserJson.getStatus(), 204, deleteUserJson.getStatusText());

        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<User> getAllUsers(Integer userId) {
        ArrayList<User> users = new ArrayList<>();

        try {
            HttpResponse<JsonNode> usersJson = Unirest.get("http://localhost:8080/users/all?token={token}")
                    .header("Content-type", "application/json")
                    .routeParam("token", String.valueOf(userId))
                    .asJson();

            if (usersJson.getStatus() != 200)
                throw new UnexpectedHttpStatusException(usersJson.getStatus(), 200, usersJson.getStatusText());

            for (Object message: usersJson.getBody().getArray()){
                users.add(new Gson().fromJson(message.toString(), User.class));
            }

        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }


    /**
     * Method which updates the user password and username
     *
     * @param user User object with the updated parameters
     */
    public static void editUser(User user) throws FailedToUpdateUser {

        JSONObject newCred = new JSONObject();
        newCred.put("username", user.getUsername());
        newCred.put("password", user.getPassword());
        System.out.println(newCred);

        try {
            HttpResponse<JsonNode> apiResponse = Unirest.put("http://localhost:8080/" +
                            "users/update/{userId}/?token={token}")
                    .routeParam("userId", String.valueOf(user.getUid()))
                    .routeParam("token", String.valueOf(user.getUid()))
                    .header("Content-Type", "application/json")
                    .body(newCred)
                    .asJson();

            User respondedUser = new Gson().fromJson(apiResponse.getBody().toString(), User.class);

            if(respondedUser.getUsername() == null){
                throw new FailedToUpdateUser("Failed to update the user");
            }

        }catch (UnirestException e){
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        }

    }
}
