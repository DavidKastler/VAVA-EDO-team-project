package vava.edo.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class User implements Serializable {
    private Integer uid = null;
    private String username = null;
    private String password = null;
    private Role userRole = null;
    private boolean rememberMe = false;  // potrebné pre serializáciu dát (mimo db)
    private boolean isLogged = false;  // potrebné pre serializáciu dát (mimo db)
    private LocalDateTime lastActivity = null;  // potrebné pre serializáciu dát (mimo db)
    private ArrayList<Task> tasks = null;

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

    public boolean isLogged() {
        return isLogged;
    }

    public LocalDateTime getLastActivity() {
        return lastActivity;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public void setLastActivity(LocalDateTime lastActivity) {
        this.lastActivity = lastActivity;
    }

    public void setTasks(ArrayList<Task> tasks){this.tasks = tasks;}

    public ArrayList<Task> getTasks(){return this.tasks;}

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                ", rememberMe=" + rememberMe +
                ", isLogged=" + isLogged +
                ", lastActivity=" + lastActivity +
                '}';
    }
}
