package vava.edo.Handlers;

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

        user.setTasks(getAllTasks(user.getUid()));

    }

    /**
     *
     * This method returns all the tasks which are assigned to the selected user by uid
     *
     * @param uid Id of the user for which you are retrieving the tasks
     * @return ArrayList of all tasks
     */
    private static ArrayList<Todo> getAllTasks(Integer uid) {
        ArrayList<Todo> todoArrayList = new ArrayList<>();

        Todo todo1 = new Todo();
        todo1.setTaskName("Vysypať smeti");
        todo1.setDueTime("Today");
        todoArrayList.add(todo1);

        Todo todo2 = new Todo();
        todo2.setTaskName("Umyť riad");
        todoArrayList.add(todo2);

        Todo todo3 = new Todo();
        todo3.setTaskName("Robiť semestrálny projekt");
        todoArrayList.add(todo3);

        Todo todo4 = new Todo();
        todo4.setTaskName("Spraviť dizajnový návrh");
        todo4.setDueTime("Today");
        todoArrayList.add(todo4);

        /*try {
            HttpResponse<JsonNode> tasksJson = Unirest.get("http://localhost:8000/todos/{uid}/all")
                    .routeParam("uid", String.valueOf(uid))
                    .asJson();

            //TODO parse json into the ArrayList<Task>

        }catch (UnirestException e){
            System.out.println("Connection to localhost:8080 failed ! (PLease start backend server)");
        }*/

        return todoArrayList;

        // return null;
    }
}
