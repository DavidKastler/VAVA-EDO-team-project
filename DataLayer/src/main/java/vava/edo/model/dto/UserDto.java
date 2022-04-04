package vava.edo.model.dto;

import com.sun.istack.NotNull;
import lombok.Data;


/**
 * Data transfer object for User class
 */
@Data
public class UserDto{
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private int roleId;
}
