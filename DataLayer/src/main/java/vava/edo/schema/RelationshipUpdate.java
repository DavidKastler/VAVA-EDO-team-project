package vava.edo.schema;

import com.sun.istack.NotNull;
import lombok.Data;
import vava.edo.model.User;

import java.util.Date;

@Data
public class RelationshipUpdate {
    @NotNull
    private User firstUserId;
    @NotNull
    private User secondUserId;
    @NotNull
    private String status;
    @NotNull
    private Date since;


    @Override
    public String toString() {
        return "RelationshipUpdate{" +
                "firstUserId=" + firstUserId +
                ", secondUserId=" + secondUserId +
                ", status='" + status + '\'' +
                ", since=" + since +
                '}';
    }
}
