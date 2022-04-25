package vava.edo.schema.relationships;

import lombok.Data;
import vava.edo.model.Relationship;

import java.util.Objects;

/**
 * Data transfer object for Relationship class
 * Used for sending relationship requests
 */
@Data
public class RelationshipRequest {
    private Integer relationshipId;
    private Integer userId;
    private String userName;

    /**
     * Casting method that creates RelationshipRequest from Relationship
     *
     * @param relationship relationship you want to cast
     * @param userId       user id of user that requests relationships
     * @return created RelationshipRequest
     */
    public static RelationshipRequest from(Relationship relationship, Integer userId) {
        RelationshipRequest relationshipRequest = new RelationshipRequest();

        relationshipRequest.setRelationshipId(relationship.getRelationshipId());
        if (Objects.equals(relationship.getFirstUser().getUId(), userId)) {
            relationshipRequest.setUserId(relationship.getSecondUser().getUId());
            relationshipRequest.setUserName(relationship.getSecondUser().getUsername());
        } else {
            relationshipRequest.setUserId(relationship.getFirstUser().getUId());
            relationshipRequest.setUserName(relationship.getFirstUser().getUsername());
        }
        return relationshipRequest;
    }
}
