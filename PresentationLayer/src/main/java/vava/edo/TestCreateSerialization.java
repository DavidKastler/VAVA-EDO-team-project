package vava.edo;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import vava.edo.models.User;
import vava.edo.models.UserHolder;

import java.time.LocalDateTime;

public class TestCreateSerialization {

    public static void main(String[] args) throws UnirestException {
        User user;
        JSONObject jo = new JSONObject();
        jo.put("username", "Clare");
        jo.put("password", "QBB5sLeIE");

        HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/users/login")
                .header("Content-Type", "application/json").body(jo).asJson();
        user = new Gson().fromJson(apiResponse.getBody().toString(), User.class);

        user.setLogged(false);
        user.setLastActivity(LocalDateTime.now());


        UserHolder userHolder = UserHolder.getInstance();
        userHolder.setUser(user);
        userHolder.serializeUser();
    }
}
