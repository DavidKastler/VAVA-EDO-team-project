package vava.edo.models;

import vava.edo.Exepctions.TodoScreen.MandatoryFieldNotInputted;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class User implements Serializable {
    private Integer userId = null;
    private String username = null;
    private String password = null;
    private Role userRole = null;
    private boolean rememberMe = false;  // potrebné pre serializáciu dát (mimo db)
    private boolean isLogged = false;  // potrebné pre serializáciu dát (mimo db)
    private long lastActivity = 0;  // potrebné pre serializáciu dát (mimo db)
    private ArrayList<Todo> todos = null;
    private ArrayList<TodoGroup> todoGroups = null;

    public Integer getUserId() {
        return userId;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public ArrayList<Todo> getTodos(){
        sortTodosByDate();
        return this.todos;
    }

    public void addTodo(Todo todo){this.todos.add(todo);}

    public void removeTodo(Todo todo){
        todos.removeIf(todoRem -> todoRem.getTodoId() == todo.getTodoId());
    }

    public void sortTodosByDate(){
        this.todos.sort(Comparator.comparing(Todo::getToTime));
    }

    /**
     * Removes the old to_do and add the new one into the todos ArrayList
     * @param todo object which has been edited
     */
    public void updateTodo(Todo todo){
        this.removeTodo(todo);
        this.todos.add(todo);
    }

    public ArrayList<TodoGroup> getGroups() {
        return todoGroups;
    }

    public void setGroups(ArrayList<TodoGroup> todoGroups) {
        this.todoGroups = todoGroups;
    }

    public void updateUserCred(String username, String password) throws MandatoryFieldNotInputted {

        if(username.equals("") || password.equals("")){
            throw new MandatoryFieldNotInputted("You have not inputted all of the mandatory fields (Username/Password)");
        }

        this.setUsername(username);
        this.setPassword(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                ", rememberMe=" + rememberMe +
                ", isLogged=" + isLogged +
                ", lastActivity=" + lastActivity +
                '}';
    }
}