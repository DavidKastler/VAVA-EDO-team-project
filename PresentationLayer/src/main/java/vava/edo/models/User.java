package vava.edo.models;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class User implements Serializable {
    private Integer uid = null;
    private String username = null;
    private String password = null;
    private Role userRole = null;
    private boolean rememberMe = false;  // potrebné pre serializáciu dát (mimo db)
    private boolean isLogged = false;  // potrebné pre serializáciu dát (mimo db)
    private long lastActivity = 0;  // potrebné pre serializáciu dát (mimo db)
    private ArrayList<Todo> todos = null;

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

    public String getLastActivity() {
        return Instant.ofEpochSecond(this.lastActivity)
                .atZone(ZoneId.of("GMT"))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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

    public void setLastActivity(long lastActivity) {
        this.lastActivity = lastActivity;
    }

    public void setTodos(ArrayList<Todo> todos){this.todos = todos;}

    public ArrayList<Todo> getTodos(){return this.todos;}

    public void addTodo(Todo todo){this.todos.add(todo);}

    public void removeTodo(Todo todo){
        this.todos.remove(todo);
    }

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
