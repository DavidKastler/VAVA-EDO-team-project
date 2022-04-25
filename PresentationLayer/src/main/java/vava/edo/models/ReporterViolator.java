package vava.edo.models;

import lombok.Getter;

@Getter
public class ReporterViolator {
    private Integer uid;
    private String username;
    private Role userRole;

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username +
                ", userRole='" + userRole +
                '}';
    }
}
