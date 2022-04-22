package vava.edo.Handlers;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.json.JSONObject;
import vava.edo.Exepctions.TodoScreen.FailedToCreateTodo;
import vava.edo.models.Todo;
import vava.edo.models.User;

import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class TodoHandler {

    /**
     * This method runs all of the necessary methods to prepare the user data for display
     *
     * @param user Object of user which is going to be initialized
     */
    public static void startUp(User user) {
        user.setTodos(getAllTodos(user.getUid()));
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
     * @param user user object which will have a new to_do assigned
     * @param todoName name of the to_do
     * @param todoDesc description text of to_do
     * @param fromTime starting time of to_do
     * @param toTime ending time of to_do
     * @param groupName name of the to_do group
     * @return returns a newly created to_do object
     */
    private static Todo createTodo(User user, String todoName, String todoDesc,
                                   long fromTime, long toTime, String groupName){

        JSONObject reqTodo = new JSONObject();
        reqTodo.put("todo_name", todoName);
        reqTodo.put("todo_description", todoDesc);
        reqTodo.put("from_time", fromTime);
        reqTodo.put("to_time", toTime);
        reqTodo.put("group_name", groupName);
        System.out.println(reqTodo);

        try{
            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/todos/create/?token={token}")
                    .routeParam("token", String.valueOf(user.getUid()))
                    .header("Content-Type", "application/json")
                    .body(reqTodo)
                    .asJson();

            return new Gson().fromJson(apiResponse.getBody().toString(), Todo.class);
        }
        catch (UnirestException e){
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        }
        return null;
    }

    /**
     * Calls createTo_do and add the newly created to_do object to the appropriate user
     *
     * @param user user object which will have a new to_do assigned
     * @param todoName name of the to_do
     * @param todoDesc description text of to_do
     * @param fromTime starting time of to_do
     * @param toTime ending time of to_do
     * @param groupName name of the to_do group
     * @return ture operation was successful / false operation wasn't successful
     */
    public static boolean addTodoToUser(User user, TextField todoName,
                                        TextField todoDesc, DatePicker fromTime,
                                        DatePicker toTime, TextField groupName) throws FailedToCreateTodo{

        Todo new_todo = createTodo(user, todoName.getText(), todoDesc.getText(),
                                    fromTime.getValue().toEpochSecond(LocalTime.now(), ZoneOffset.of("+02:00")),
                                    toTime.getValue().toEpochSecond(LocalTime.now(), ZoneOffset.of("+02:00")),
                                    groupName.getText());

        if (new_todo != null){
            user.addTodo(new_todo);
        }
        else {
            throw new FailedToCreateTodo("Failed to create a new ToDo");
        }

        return true;
    }
}
