package vava.edo.models;

import java.util.ArrayList;

public class Group {

    private String name;
    private ArrayList<Todo> todos;

    public Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Todo> getTodos() {
        return todos;
    }

    public void setTodos(ArrayList<Todo> todos) {
        this.todos = todos;
    }

    public void addTodo(Todo todo){
        this.todos.add(todo);
    }
}
