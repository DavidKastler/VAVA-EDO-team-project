package vava.edo.schema;

import com.sun.istack.NotNull;
import lombok.Data;
import vava.edo.model.User;

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


    @Override
    public String toString() {
        return "GroupCreate{" +
                ", creatorId='" + creatorId +
                ", groupName=" + groupName +
                '}';
    }
}
