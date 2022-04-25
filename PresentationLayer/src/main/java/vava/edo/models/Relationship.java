package vava.edo.models;

import lombok.Getter;

@Getter
public class Relationship {

    private Integer relationshipId;
    private Integer userId;
    private String userName;

    @Override
    public String toString() {
        return "Relationship{" +
                "relationshipId=" + relationshipId +
                ", secondUserId=" + userId +
                ", secondUsername=" + userName +
                '}';
    }
}
