package vava.edo.schema;

import com.sun.istack.NotNull;
import lombok.Data;

/**
 * Data transfer object for Group class
 * Used in creatingGroup
 */
@Data
public class GroupCreate {
    @NotNull
    private int creatorId;
    @NotNull
    private String groupName;


    /**
     * Debugging method
     *
     * @return string with method variables
     */
    @Override
    public String toString() {
        return "GroupCreate{" +
                ", creatorId='" + creatorId +
                ", groupName=" + groupName +
                '}';
    }
}
