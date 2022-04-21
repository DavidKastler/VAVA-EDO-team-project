package vava.edo.schema.users;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class to edit all parameters of user for admin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserEdit extends UserLogin {
    private int roleId;
}
