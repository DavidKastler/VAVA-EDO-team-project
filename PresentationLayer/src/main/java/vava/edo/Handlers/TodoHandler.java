package vava.edo.Handlers;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONObject;
import vava.edo.Exepctions.TodoScreen.MandatoryFieldNotInputted;
import vava.edo.Exepctions.TodoScreen.TodoDatabaseFail;
import vava.edo.models.Todo;
import vava.edo.models.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class TodoHandler {

    static ResourceBundle resourceBundle = ResourceBundle.getBundle("Localization Bundle", Locale.getDefault());

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
        reqTodo.put("userId", user.getUid());
        reqTodo.put("todoName", todoName);
        reqTodo.put("todoDescription", todoDesc);
        reqTodo.put("toTime", toTime);
        reqTodo.put("fromTime", fromTime);
        reqTodo.put("completed", false);
        reqTodo.put("groupName", groupName);


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
     */
    public static void addTodoToUser(User user, TextField todoName,
                                        TextArea todoDesc, DatePicker fromTime,
                                        DatePicker toTime, TextField groupName)
                                        throws TodoDatabaseFail, MandatoryFieldNotInputted{

        if(toTime.getValue() == null || fromTime.getValue() == null || todoName.getText().equals("")){
            resourceBundle = ResourceBundle.getBundle("Localization Bundle", Locale.getDefault());
            throw new MandatoryFieldNotInputted(resourceBundle.getString("TodoHandler.mandatoryFieldNotInputted"));
        }

        Todo new_todo = createTodo(user, todoName.getText(), todoDesc.getText(),
                                    fromTime.getValue().toEpochSecond(LocalTime.MIDNIGHT, ZoneOffset.UTC),
                                    toTime.getValue().toEpochSecond(LocalTime.MIDNIGHT, ZoneOffset.UTC),
                                    groupName.getText());

        if (new_todo.getTodoName() != null){
            user.addTodo(new_todo);
        }
        else {
            resourceBundle = ResourceBundle.getBundle("Localization Bundle", Locale.getDefault());
            throw new TodoDatabaseFail(resourceBundle.getString("TodoHandler.todoDatabaseFailCreate"));
        }

    }


    /**
     * Method creates a PUT request for the BE
     *
     * @param todoId to_do object which will be edited
     * @param user user which owns the to_do
     * @param todoName name of the to_do
     * @param todoDesc description text of to_do
     * @param fromTime starting time of to_do
     * @param toTime ending time of to_do
     * @param groupName name of the to_do group
     * @return returns a update to_do object
     */
    private static Todo editTodoPut(int todoId, User user,
                                    String todoName,
                                    String todoDesc, long fromTime,
                                    long toTime, String groupName){

        JSONObject reqTodo = new JSONObject();
        reqTodo.put("userId", user.getUid());
        reqTodo.put("todoName", todoName);
        reqTodo.put("todoDescription", todoDesc);
        reqTodo.put("toTime", toTime);
        reqTodo.put("fromTime", fromTime);
        reqTodo.put("completed", false);
        reqTodo.put("groupName", groupName);

        try{
            HttpResponse<JsonNode> apiResponse = Unirest.put("http://localhost:8080/todos" +
                    "/edit/{task_id}/?token={token}")
                    .routeParam("token", String.valueOf(user.getUid()))
                    .routeParam("task_id", String.valueOf(todoId))
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
     * Method calls editTodoPut which is responsible for the database update
     *
     * @param todoId to_do object which will be edited
     * @param user user which owns the to_do
     * @param todoName name of the to_do
     * @param todoDesc description text of to_do
     * @param fromTime starting time of to_do
     * @param toTime ending time of to_do
     * @param groupName name of the to_do group
     */
    public static Todo editTodo(int todoId, User user,
                                TextField todoName,
                                TextArea todoDesc, DatePicker fromTime,
                                DatePicker toTime, TextField groupName) throws MandatoryFieldNotInputted, TodoDatabaseFail{

        if(toTime.getValue() == null || fromTime.getValue() == null || todoName.getText().equals("")){
            resourceBundle = ResourceBundle.getBundle("Localization Bundle", Locale.getDefault());
            throw new MandatoryFieldNotInputted(resourceBundle.getString("TodoHandler.mandatoryFieldNotInputted"));

        }

        Todo updated_todo = editTodoPut(todoId, user, todoName.getText(), todoDesc.getText(),
                fromTime.getValue().toEpochSecond(LocalTime.MIDNIGHT, ZoneOffset.UTC),
                toTime.getValue().toEpochSecond(LocalTime.MIDNIGHT, ZoneOffset.UTC),
                groupName.getText());

        if(updated_todo.getTodoName() != null){
            user.updateTodo(updated_todo);
            return updated_todo;
        }
        else {
            resourceBundle = ResourceBundle.getBundle("Localization Bundle", Locale.getDefault());
            throw new TodoDatabaseFail(resourceBundle.getString("TodoHandler.todoDatabaseFailEdit"));
        }
    }


    /**
     * Method responsible for deleting the selected to_do from the database
     *
     * @param todoId to_doId of a to_do which is being deleted
     * @param user User to which the to_do belongsRe
     */
    public static void deleteTodo(int todoId, User user) throws TodoDatabaseFail {

        try {

            HttpResponse<JsonNode> apiResponse = Unirest.delete("http://localhost:8080/" +
                                                                "todos/delete/{task_id}/?token={token}")
                    .routeParam("task_id", String.valueOf(todoId))
                    .routeParam("token", String.valueOf(user.getUid()))
                    .asJson();

            Todo deletedTodo = new Gson().fromJson(apiResponse.getBody().toString(), Todo.class);

            if (deletedTodo.getTodoName() != null){
                user.removeTodo(deletedTodo);
            }
            else {
                resourceBundle = ResourceBundle.getBundle("Localization Bundle", Locale.getDefault());
                throw new TodoDatabaseFail(resourceBundle.getString("TodoHandler.todoDatabaseFailDelete"));
            }

        }catch (UnirestException e){
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        }

    }

    /**
     * Gathers all the todos which are completed
     *
     * @param user from which you want to filter out the todos
     * @return ArrayList with completed todos only
     */
    public static ArrayList<Todo> getCompletedTodos(User user){

        ArrayList<Todo> completedTodos = new ArrayList<>();

        for(Todo todo : user.getTodos()){
            if (todo.isCompleted()){
                completedTodos.add(todo);
            }
        }

        return completedTodos;
    }

    /**
     * Method which returns todos only which have due time today
     *
     * @param user from which you want to filter out the todos
     * @return ArrayList with todos that have dueTime today
     */
    public static ArrayList<Todo> getTodayTodos(User user) {

        ArrayList<Todo> todaysTodos = new ArrayList<>();

        Date todays_midnight = Date.from(LocalDateTime.now().toLocalDate().atStartOfDay()
                                            .atZone(ZoneId.of("Europe/Bratislava")).toInstant());

        try {
            for(Todo todo : user.getTodos()){
                if (DateUtils.isSameDay(todays_midnight, new SimpleDateFormat("yyyy-MM-dd").parse(todo.getToTime()))){
                    todaysTodos.add(todo);
                }
            }
        } catch (ParseException e){
            System.out.println("Parsing of the date was unsuccessful");
        }

        return todaysTodos;
    }


    /**
     * Method which returns todos only which have due time tomorrow
     *
     * @param user from which you want to filter out the todos
     * @return ArrayList with todos that have dueTime tomorrow
     */
    public static ArrayList<Todo> getTomorrowTodos(User user){

        ArrayList<Todo> tomorrowsTodos = new ArrayList<>();

        Date tomorrows_midnight = DateUtils.addDays(Date.from(LocalDateTime.now().toLocalDate().atStartOfDay()
                .atZone(ZoneId.of("Europe/Bratislava")).toInstant()), 1);

        try {
            for(Todo todo : user.getTodos()){
                if (DateUtils.isSameDay(tomorrows_midnight, new SimpleDateFormat("yyyy-MM-dd").parse(todo.getToTime()))){
                    tomorrowsTodos.add(todo);
                }
            }
        } catch (ParseException e){
            System.out.println("Parsing of the date was unsuccessful");
        }

        return tomorrowsTodos;
    }

    /**
     * Method which selects the todos which are from specific time frame
     *
     * @param user object who's todos you want to filter out
     * @param fromDate starting date of the given range
     * @param toDate ending date of the given range
     * @return ArrayList of all todos which are from the given range
     */
    public static ArrayList<Todo> getTodosByDate(User user, LocalDate fromDate, LocalDate toDate){
        ArrayList<Todo> selectedTodos = new ArrayList<>();

        for (Todo todo: user.getTodos()){
            // Checks whether the to_do time is from desired range
            if(fromDate.compareTo(LocalDate.parse(todo.getFromTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))) >= 0 &&
                    toDate.compareTo(LocalDate.parse(todo.getToTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))) <= 0){
                selectedTodos.add(todo);
            }
        }

        return selectedTodos;
    }

    /**
     * Method which creates a PUT request to update the status of to_do
     *
     * @param todo id of a to_do which will have changed it's status
     */
    public static void changeTodoStatus(Todo todo, int userId) throws TodoDatabaseFail{

        try {
            HttpResponse<JsonNode> apiResponse = Unirest.put("http://localhost:8080/" +
                    "todos/complete/{todoId}/?token={token}")
                    .routeParam("todoId", String.valueOf(todo.getTodoId()))
                    .routeParam("token", String.valueOf(userId))
                    .asJson();

            Todo completed_todo = new Gson().fromJson(apiResponse.getBody().toString(), Todo.class);

            if(completed_todo.getTodoName() != null){
                todo.setCompleted(completed_todo.isCompleted());
            }else {
                resourceBundle = ResourceBundle.getBundle("Localization Bundle", Locale.getDefault());
                throw new TodoDatabaseFail(resourceBundle.getString("TodoHandler.todoDatabaseFailChangeStatus"));
            }

        }catch (UnirestException e){
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        }

    }
}
