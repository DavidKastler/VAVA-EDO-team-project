package vava.edo.models;

public class User {
    private Integer uid = null;
    private String username = null;
    private String password = null;
    private Role userRole = null;

    public Integer getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getUserRole() {
        return userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRol=" + userRole +
                '}';
    }
}
