package vava.edo.models;

import lombok.Getter;

@Getter
public class Group {

    private int groupId;
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", groupName=" + groupName + '\'' +
                '}';
    }
}
