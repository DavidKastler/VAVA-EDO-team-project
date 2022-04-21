package vava.edo.schema.users;

import lombok.Data;

/**
 * Data transfer object for User class
 * It is used for logging in user
 */
@Data
public class UserLogin {
    private String username;
    private String password;

    @Override
    public String toString() {
        return "UserLogin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
