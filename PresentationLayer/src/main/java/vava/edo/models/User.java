package vava.edo.models;

public class User {
    private Integer uid = null;
    private String username = null;
    private String password = null;
    private Role userRole = null;

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
