package vava.edo.schema.users;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Data transfer object for User class
 * It is used for registering and editing user
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserEdit extends UserLogin {
    @NotNull
    private Integer roleId;

    @Override
    public String toString() {
        return "UserRegister{" +
                "roleId=" + roleId +
                "} " + super.toString();
    }
}
