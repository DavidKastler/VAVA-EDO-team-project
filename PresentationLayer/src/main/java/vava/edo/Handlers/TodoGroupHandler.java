package vava.edo.Handlers;

import vava.edo.models.TodoGroup;
import vava.edo.models.Todo;
import vava.edo.models.User;

import java.util.ArrayList;

public class TodoGroupHandler {


    /**
     * Method for creation of ArrayList of groups and then sorting all of the todos to the appropriate groups
     *
     * @param user object of which todos are going to be sorted
     * @return ArrayList of groups which have their sorted todos by groupName
     */
    public static ArrayList<TodoGroup> getGroupsWithTodos(User user){

        ArrayList<TodoGroup> todoGroups = new ArrayList<>();

        // Creates ArrayList of groups with all of the unique group names
        for(String group_name: getGroupNames(user.getTodos())){
            todoGroups.add(new TodoGroup(group_name));
        }

        for(Todo todo: user.getTodos()){
            for(TodoGroup todoGroup : todoGroups){
                if(todo.getGroupName().equals(todoGroup.getName())){
                    todoGroup.addTodo(todo);
                }
            }
        }

        return todoGroups;
    }


    /**
     * Method which find and returns all of the uniq groupNames that are in the todos list
     *
     * @param todos ArrayList of all the user todos
     * @return returns a ArrayList with all of the unique group names
     */
    private static ArrayList<String> getGroupNames(ArrayList<Todo> todos){
        ArrayList<String> groupNames = new ArrayList<>();

        for (Todo todo: todos){
            if(!groupNames.contains(todo.getGroupName())){
                groupNames.add(todo.getGroupName());
            }
        }

        return groupNames;
    }
}
