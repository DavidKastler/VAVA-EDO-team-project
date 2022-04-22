package vava.edo.Handlers;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import vava.edo.models.Todo;
import vava.edo.models.User;

import java.util.ArrayList;


public class TodoHandler {

    /**
     * This method runs all of the necessary methods to prepare the user data for display
     *
     * @param user Object of user which is going to be initialized
     */
    public static void startUp(User user) {
        user.setTasks(getAllTodos(user.getUid()));
    }


    /**
     * This method returns all the tasks which are assigned to the selected user by uid
     *
     * @param uid Id of the user for which you are retrieving the tasks
     * @return ArrayList of all tasks
     */
    private static ArrayList<Todo> getAllTodos(Integer uid) {

        ArrayList<Todo> tasks = new ArrayList<>();

        try {
            HttpResponse<JsonNode> tasksJson = Unirest.get("http://localhost:8080/todos/get/?token={token}")
                    .routeParam("token", String.valueOf(uid))
                    .asJson();

            for(Object task: tasksJson.getBody().getArray()){
                tasks.add(new Gson().fromJson(task.toString(), Todo.class));
            }

        }catch (UnirestException e){
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        }

        return tasks;
    }

    /**
     * Method which post newly created to_do and gets and gets the to_do from database
     *
     * @return returns a newly created to_do object
     */
    private static Todo createTodo(){
        return null;
    }

    /**
     * Calls createTo_do and add the newly created to_do object to the appropriate user
     *
     * @param user user object which will have a new to_do assigned
     * @return ture operation was successful / false operation wasn't successful
     */
    public static boolean addTodoToUser(User user){

        return false;
    }
}
