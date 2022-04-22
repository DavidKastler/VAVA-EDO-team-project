package vava.edo.schema;


import com.sun.istack.NotNull;
import lombok.Data;
import vava.edo.model.Relationship;
import vava.edo.model.User;
import vava.edo.model.enums.RelationshipStatus;

/**
 * Class used to update relations in re
 */
@Data
public class RelationshipEditStatus {
    @NotNull
    private User firstUserId;
    @NotNull
    private User secondUserId;
    @NotNull
    private RelationshipStatus status;

    /**
     * Create new relationship, status is "pending" by default
     *
     * @param firstUserId  user who send request
     * @param secondUserId user who received request
     */
    public RelationshipEditStatus(User firstUserId, User secondUserId, RelationshipStatus status) {
        this.firstUserId = firstUserId;
        this.secondUserId = secondUserId;
        this.status = status;
    }
}
