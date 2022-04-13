package vava.edo.schema;


import com.sun.istack.NotNull;
import lombok.Data;


/**
 * Data transfer object for Group class
 * Used in addMember
 */
@Data
public class GroupUpdate {
    @NotNull
    private int userId;
    @NotNull
    private int groupId;


    @Override
    public String toString() {
        return "GroupUpdate{" +
                "userId=" + userId +
                ", groupId=" + groupId +
                '}';
    }
}
