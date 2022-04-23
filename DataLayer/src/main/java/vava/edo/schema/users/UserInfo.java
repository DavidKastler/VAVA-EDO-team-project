package vava.edo.schema.users;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import vava.edo.model.User;

/**
 * Data transfer object for User class
 * It is used for sending only needed user data
 */
@Data
public class UserInfo {
    @NotNull
    private int userId;
    @NotNull
    private String username;


    public static UserInfo from(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getUId());
        userInfo.setUsername(user.getUsername());
        return userInfo;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
