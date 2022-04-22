package vava.edo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Todo;
import vava.edo.schema.TaskCreate;
import vava.edo.schema.TaskUpdate;
import vava.edo.service.GroupService;
import vava.edo.service.TodosService;

import java.util.List;
import java.util.Objects;

/**
 * Class that provides endpoints for operation with tasks
 */
@RestController
@RequestMapping("/todos")
public class TodosController {

    private final TodosService todosService;
    private final GroupService groupService;

    @Autowired
    public TodosController(TodosService todosService, GroupService groupService) {
        this.todosService = todosService;
        this.groupService = groupService;
    }

    /**
     * Endpoint used to create a new task
     * @param token   user account id
     * @param taskDto data transfer object for Task class
     * @return response entity containing task and http status 201 / 401 / 404
     */
    @PostMapping("/create")
    public ResponseEntity<Todo> createTask(@RequestParam(value = "token") Integer token,
                                           @RequestBody TaskCreate taskDto) {
        if (!token.equals(taskDto.getUserId()) &&
                !groupService.isUserCreatorsGroupMember(token, taskDto.getUserId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cant access foreign todos.");
        }

        return new ResponseEntity<>(todosService.createTask(taskDto), HttpStatus.CREATED);
    }

    @PutMapping("/complete/{task_id}")
    public ResponseEntity<Todo> completeTaskById(@RequestParam(value = "token") Integer token,
                                                 @PathVariable(value = "task_id") Integer taskId) {
        return new ResponseEntity<>(todosService.invertCompleted(token, taskId), HttpStatus.OK);
    }

    /**
     * Endpoint used to delete a specific task
     * @param token     user account id
     * @param taskId    id of task we want to delete
     * @return response entity containing deleted task and http status 204 / 401 / 404
     */
    @DeleteMapping("/delete/{task_id}")
    public ResponseEntity<Object> deleteTaskById(@RequestParam(value = "token") Integer token, @PathVariable(value = "task_id") Integer taskId) {
        if (todosService.getUserIdFromTask(taskId) != token) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be the owner of the account.");
        return new ResponseEntity<>(todosService.deleteTask(taskId), HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint used to edit a specific task
     * @param token     user account id
     * @param taskId    id of task we want to update
     * @param taskDto   data transfer object for Task class
     * @return response entity containing task and http status 200 / 400 / 404
     */
    @PutMapping("/edit/{task_id}")
    public ResponseEntity<Todo> updateTaskById(@RequestParam(value = "token") Integer token,
                                               @PathVariable(value = "task_id") Integer taskId,
                                               @RequestBody TaskUpdate taskDto) {
        if (!Objects.equals(token, taskDto.getUserId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cant edit foreign todo");
        }
        return new ResponseEntity<>(todosService.updateTask(taskId, taskDto), HttpStatus.OK);
    }

    /**
     * Endpoint returning a list of all users tasks
     * @param token   user account id whose messages we want to show
     * @return list of users tasks and http status 200 / 404
     */
    @GetMapping("/get")
    public ResponseEntity<List<Todo>> getAllTasks(@RequestParam(value = "token")  Integer token) {
        return new ResponseEntity<>(todosService.getAllTasksForUser(token), HttpStatus.OK);
    }

    /*
     * Endpoint returning a list of completed tasks between two indexes
     * @param token     user account id
     * @param fromIndex index of first task
     * @param toIndex   index of last task
     * @return  response entity contaning a list of completed tasks and http status 200 / 401 / 404
     */
    /*
    @GetMapping("/getMoreCompletedTasks")
    public ResponseEntity<List<Task>> getCompletedTasks(@RequestParam(value = "token") int token, @RequestParam(value = "from", required = false) Integer fromIndex, @RequestParam(value = "to", required = false) Integer toIndex) {
        if (fromIndex == null) fromIndex = 0;
        if (toIndex == null) toIndex = 20;
        return new ResponseEntity<>(taskService.getCompletedTasks(token, fromIndex, toIndex), HttpStatus.OK);
    }*/

    /**
     * Endpoint returning a list of tasks between times in unix formats
     * @param token     user account id
     * @param from      unix format of time we want to search from
     * @param to        unix format of time we want to search to
     * @return          list of found task objects
     */
    @GetMapping("/load")
    public ResponseEntity<List<Todo>> getTasksByTimeRange(@RequestParam(value = "token") Integer token, @RequestParam(value = "from", required = false) long from, @RequestParam(value = "to", required = false) long to) {
        return new ResponseEntity<>(todosService.getTasksByTimeRange(token, from, to), HttpStatus.OK);
    }
}
