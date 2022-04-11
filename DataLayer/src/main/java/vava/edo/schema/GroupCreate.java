package vava.edo.schema;

import com.sun.istack.NotNull;
import lombok.Data;
import vava.edo.model.User;

@Data
public class GroupCreate {
    @NotNull
    private User creatorId;
    private String groupName;

    @Override
    public String toString() {
        return "GroupCreate{" +
                ", userId='" + creatorId.getUId() + '\'' +
                ", groupName=" + groupName +
                '}';
    }
}
