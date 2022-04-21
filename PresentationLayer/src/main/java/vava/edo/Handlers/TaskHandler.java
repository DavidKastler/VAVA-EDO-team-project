package vava.edo.Handlers;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import vava.edo.models.Task;
import vava.edo.models.User;
import java.util.ArrayList;


public class TaskHandler {

    /**
     * This method runs all of the necessary methods to prepare the user data for display
     *
     * @param user Object of user which is going to be initialized
     */
    public static void startUp(User user) {

        user.setTasks(getAllTasks(user.getUid()));

    }



    /**
     *
     * This method returns all the tasks which are assigned to the selected user by uid
     *
     * @param uid Id of the user for which you are retrieving the tasks
     * @return ArrayList of all tasks
     */
    private static ArrayList<Task> getAllTasks(Integer uid) {

        try {
            HttpResponse<JsonNode> tasksJson = Unirest.get("http://localhost:8000/todos/{uid}/all")
                    .routeParam("uid", String.valueOf(uid))
                    .asJson();

            //TODO parse json into the ArrayList<Task>

        }catch (UnirestException e){
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        }


        return null;
    }
}
