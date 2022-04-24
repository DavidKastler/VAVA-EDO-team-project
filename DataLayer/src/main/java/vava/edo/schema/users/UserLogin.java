package vava.edo.schema.users;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Data transfer object for User class
 * It is used for logging in user
 */
@Data
@NoArgsConstructor
public class UserLogin {
    @NonNull
    private String username;
    @NonNull
    private String password;

    @Override
    public String toString() {
        return "UserLogin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
