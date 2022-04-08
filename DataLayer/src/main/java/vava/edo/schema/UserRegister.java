package vava.edo.schema;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Data transfer object for User class
 * It is used for registering user
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserRegister extends UserLogin{
    @NotNull
    private int roleId;

    @Override
    public String toString() {
        return "UserRegister{" +
                "roleId=" + roleId +
                "} " + super.toString();
    }
}
