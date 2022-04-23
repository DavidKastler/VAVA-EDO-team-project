package vava.edo.schema;


import com.sun.istack.NotNull;
import lombok.Data;

/**
 * Class used to update relations in re
 */
@Data
public class RelationshipCreate {
    @NotNull
    private Integer senderId;
    @NotNull
    private Integer receiverId;

    /**
     * Create new relationship, status is "pending" by default
     * @param firstUserId  user who send request
     * @param secondUserId user who received request
     */
    public RelationshipCreate(Integer firstUserId, Integer secondUserId) {
        this.senderId = firstUserId;
        this.receiverId = secondUserId;
    }
}
