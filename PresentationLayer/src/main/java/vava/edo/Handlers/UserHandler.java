package vava.edo.Handlers;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONObject;
import vava.edo.Exepctions.HttpStatusExceptions.UnexpectedHttpStatusException;
import vava.edo.Exepctions.LoginScreen.EmptyLoginFields;
import vava.edo.Exepctions.LoginScreen.FailedToRegister;
import vava.edo.Exepctions.LoginScreen.IncorrectCredentials;
import vava.edo.Exepctions.MenuScreen.FailedToUpdateUser;
import vava.edo.models.User;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;

public class UserHandler extends UserSerializationHandler {

    /**
     * Method which is responsible for validation and logging of the user into the system
     *
     * @param username Username entered via textField in the frontend
     * @param password Password entered via passwordField in the frontend
     * @param wrongCredentials Object of label class to show display wrong credentials error
     *
     * @return returning a object of logged in user
     */
    public static User loginUser(String username, String password, Label wrongCredentials)
            throws EmptyLoginFields, IncorrectCredentials {

        if(!Objects.equals(username, "") && !Objects.equals(password, "")){

            JSONObject jo = new JSONObject();
            jo.put("username", username);
            jo.put("password", password);
            System.out.println(jo);

            try {
                HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/users/login")
                        .header("Content-Type", "application/json")
                        .body(jo)
                        .asJson();
                User user = new Gson().fromJson(apiResponse.getBody().toString(), User.class);

                if(user.getUsername() != null){
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
        } else {
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

        } catch (UnirestException | UnexpectedHttpStatusException e) {
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

        } catch (UnirestException | UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }


    /**
     * Method which updates the user password and username
     *
     * @param user User object with the updated parameters
     */
    public static void updateUser(User user) throws FailedToUpdateUser {

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


    /**
     * Method which edits user role, password and username
     *
     * @param user User object with the updated parameters
     */
    public static void editUser(User user) throws FailedToUpdateUser {

        JSONObject newCred = new JSONObject();
        newCred.put("username", user.getUsername());
        newCred.put("password", user.getPassword());
        newCred.put("roleId", user.getUserRole().getrId());
        System.out.println(newCred);

        try {
            HttpResponse<JsonNode> apiResponse = Unirest.put("http://localhost:8080/" +
                            "users/edit/{userId}/?token={token}")
                    .routeParam("userId", String.valueOf(user.getUid()))
                    .routeParam("token", String.valueOf(user.getUid()))
                    .header("Content-Type", "application/json")
                    .body(newCred)
                    .asJson();

            User respondedUser = new Gson().fromJson(apiResponse.getBody().toString(), User.class);

            if(respondedUser.getUsername() == null){
                throw new FailedToUpdateUser("Failed to edit the user");
            }

        }catch (UnirestException e){
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        }
    }


    public static void registerUser(TextField textUsername, TextField textPassword1,
                                    TextField textPassword2, Label wrongInput) throws FailedToRegister{
        if (!textPassword1.getText().equals(textPassword2.getText()) || textUsername.getText().isEmpty()){
            System.out.println("Wrong Input!");
            wrongInput.setVisible(true);
            textUsername.clear();
            textPassword1.clear();
            textPassword2.clear();
        }else {
            wrongInput.setVisible(false);

            JSONObject jo = new JSONObject();
            jo.put("username", textUsername.getText());
            jo.put("password", textPassword1.getText());
            System.out.println(jo);

            try {
                HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/users/register")
                        .header("Content-Type", "application/json")
                        .body(jo)
                        .asJson();

                User respondedUser = new Gson().fromJson(apiResponse.getBody().toString(), User.class);

                System.out.println(apiResponse.getBody().toString());

                if(respondedUser.getUsername() == null){
                    throw new FailedToRegister("Failed to register a new user");
                }

            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }
    }
}
