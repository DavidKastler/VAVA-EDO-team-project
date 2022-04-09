package vava.edo.models;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class User {
    private Integer uId = null;
    private String username = null;
    private String password = null;
    private Role userRol = null;

    public static void getUserDataFromAPI() {

    }

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRol=" + userRol +
                '}';
    }
}
