package vava.edo.schema.relationships;

import lombok.Data;
import vava.edo.model.Relationship;

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
     * @return created RelationshipRequest
     */
    public static RelationshipRequest from(Relationship relationship) {
        RelationshipRequest relationshipRequest = new RelationshipRequest();

        relationshipRequest.setRelationshipId(relationship.getRelationshipId());
        relationshipRequest.setUserId(relationship.getFirstUser().getUId());
        relationshipRequest.setUserName(relationship.getFirstUser().getUsername());

        return relationshipRequest;
    }
}
