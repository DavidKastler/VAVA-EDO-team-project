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
import vava.edo.Exepctions.EmptyLoginFields;
import vava.edo.Exepctions.IncorrectCredentials;
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
}
