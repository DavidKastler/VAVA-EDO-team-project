package vava.edo.schema;
import com.sun.istack.NotNull;
import lombok.Data;
import vava.edo.model.User;

/**
 * It is used to get only user's username and ID
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
