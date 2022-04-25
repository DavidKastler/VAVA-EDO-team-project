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
import vava.edo.Exepctions.LoginScreen.EmptyLoginFields;
import vava.edo.Exepctions.LoginScreen.FailedToRegister;
import vava.edo.Exepctions.LoginScreen.IncorrectCredentials;
import vava.edo.Exepctions.MenuScreen.FailedToUpdateUser;
import vava.edo.models.User;

import java.time.Instant;
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
