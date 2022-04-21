package vava.edo.models;

import com.mashape.unirest.http.JsonNode;

import java.util.List;


public class Group {

    private int grId;
    private String groupName;

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + grId +
                ", groupName=" + groupName +
                '}';
    }
}
