package vava.edo.Handlers;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import vava.edo.models.Todo;
import vava.edo.models.User;
import java.util.ArrayList;


public class TaskHandler {

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
            HttpResponse<JsonNode> tasksJson = Unirest.get("http://localhost:8080/todos/{uid}/all/?token={token}")
                    .routeParam("uid", String.valueOf(uid))
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
}
