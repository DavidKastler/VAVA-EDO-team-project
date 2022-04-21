package vava.edo.schema;


import com.sun.istack.NotNull;
import lombok.Data;


/**
 * Data transfer object for Group class
 * Used in addMember
 */
@Data
public class GroupAddMember {
    @NotNull
    private int userId;
    @NotNull
    private int groupId;


    /**
     * Debugging method
     *
     * @return string with method variables
     */
    @Override
    public String toString() {
        return "GroupUpdate{" +
                "userId=" + userId +
                ", groupId=" + groupId +
                '}';
    }
}