package vava.edo.schema;


import com.sun.istack.NotNull;
import lombok.Data;
import vava.edo.model.User;


import java.sql.Date;

@Data
public class RelationshipCreate {
    @NotNull
    private User firstUserId;
    @NotNull
    private User secondUserId;
    @NotNull
    private String status;
    @NotNull
    private Date since;

    /**
     * Create new relationship
     * @param firstUserId   user who send request
     * @param secondUserId  user who received request
     */
    public RelationshipCreate(User firstUserId, User secondUserId) {
        this.firstUserId = firstUserId;
        this.secondUserId = secondUserId;
        this.status = "pending";
        this.since = new java.sql.Date(System.currentTimeMillis());
    }
}
