package vava.edo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Role;
import vava.edo.model.Task;
import vava.edo.model.exeption.UserNotFoundException;
import vava.edo.schema.TaskCreate;
import vava.edo.schema.TaskUpdate;
import vava.edo.service.TaskService;
import vava.edo.service.UserService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/{id}/all")
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(value = "token") int token, @PathVariable(value = "id") Integer userId) {
        if (userId != null && (userService.isAdmin(token) || userId == token)) {
            return new ResponseEntity<>(taskService.getAllTasks(userId), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be the owner of the selected account.");
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<Task> createTask(@RequestParam(value = "token") int token, @PathVariable(value = "id") Integer userId, @RequestBody TaskCreate taskDto) {
        if (userId != null && (userService.isAdmin(token) || userId == token)) {
            return new ResponseEntity<>(taskService.createTask(taskDto), HttpStatus.CREATED);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be the owner of the selected account.");
    }

    @PutMapping("/{id}/update/{task_id}")
    public ResponseEntity<Task> updateTaskById(@RequestParam(value = "token") int token, @PathVariable(value = "id") Integer userId, @PathVariable(value = "task_id") Integer taskId,
                                                @RequestBody TaskUpdate taskDto) {

        if (userId != null && (userService.isAdmin(token) || userId == token)) {
            return new ResponseEntity<>(taskService.updateTask(taskId, taskDto), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be the owner of the selected account.");
    }

    @DeleteMapping("/{id}/delete/{task_id}")
    public ResponseEntity<Object> deleteTaskById(@RequestParam(value = "token") int token, @PathVariable(value = "id") Integer userId, @PathVariable(value = "task_id") Integer taskId) {

        if (userId != null && (userService.isAdmin(token) || userId == token)) {
            return new ResponseEntity<>(taskService.deleteTask(taskId), HttpStatus.NO_CONTENT);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be the owner of the selected account.");
    }

    @GetMapping("/{id}/getMoreCompletedTasks/{from}/{to}")
    public ResponseEntity<List<Task>> getCompletedTasks(@RequestParam(value = "token") int token, @PathVariable(value = "id") Integer userId
            , @PathVariable(value = "from") Integer fromIndex, @PathVariable(value = "to") Integer toIndex) {

        if (userId != null && (userService.isAdmin(token) || userId == token)) {
            return new ResponseEntity<>(taskService.getCompletedTasks(userId, fromIndex, toIndex), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be the owner of the selected account.");
    }

    //NEFUNKCNE
    @GetMapping("/{id}/{time_range}")
    public ResponseEntity<List<Task>> getTasksByMonth(@RequestParam(value = "token") int token, @PathVariable(value = "id") Integer userId, @PathVariable(value = "time_range") Integer month) {

        if (userId != null && (userService.isAdmin(token) || userId == token)) {
            return new ResponseEntity<>(taskService.getTasksByMonth(userId, month), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be the owner of the selected account.");
    }
}
