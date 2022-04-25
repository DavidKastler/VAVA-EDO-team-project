package vava.edo.models;

import com.mashape.unirest.http.JsonNode;

import java.util.List;


public class Group {


    private int grId;
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public int getGrId() {
        return grId;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + grId +
                ", groupName=" + groupName + '\'' +
                '}';
    }
}
