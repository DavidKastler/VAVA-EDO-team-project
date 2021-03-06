package vava.edo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Todo;
import vava.edo.schema.todos.TodoCreate;
import vava.edo.schema.todos.TodoUpdate;
import vava.edo.service.GroupService;
import vava.edo.service.TodosService;
import vava.edo.service.UserService;

import java.util.List;
import java.util.Objects;

/**
 * Class that provides endpoints for operation with todos
 */
@Log4j2
@RestController
@RequestMapping("/todos")
public class TodosController {

    private final TodosService todosService;
    private final GroupService groupService;
    private final UserService userService;

    @Autowired
    public TodosController(TodosService todosService, GroupService groupService, UserService userService) {
        this.todosService = todosService;
        this.groupService = groupService;
        this.userService = userService;
    }

    /**
     * Endpoint used to create a new to-do
     *
     * @param token   user account id
     * @param todoDto data transfer object for to-do class
     * @return response entity containing to-do and http status 201 / 401 / 404
     */
    @PostMapping("/create")
    public ResponseEntity<Todo> createTodo(@RequestParam(value = "token") Integer token,
                                           @RequestBody TodoCreate todoDto) {
        log.info("Creating new element to ToDo list.");
        if (!token.equals(todoDto.getUserId()) &&
                !groupService.isUserCreatorsGroupMember(token, todoDto.getUserId())) {
            log.warn("Request failed, todos can be created only in your own ToDo list.");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cant access foreign todos.");
        }
        log.info("Creating new ToDo finished successfully.");
        return new ResponseEntity<>(todosService.createTodo(todoDto), HttpStatus.CREATED);
    }

    /**
     * Endpoint that completes to-do for user
     *
     * @param token  user account id
     * @param todoId id of to-do we want to complete
     * @return updated to-do
     */
    @PutMapping("/complete/{todoId}")
    public ResponseEntity<Todo> completeTodoById(@RequestParam(value = "token") Integer token,
                                                 @PathVariable(value = "todoId") Integer todoId) {
        log.info("Completing a ToDo with id:{}", todoId);
        return new ResponseEntity<>(todosService.invertCompleted(token, todoId), HttpStatus.OK);
    }

    /**
     * Endpoint used to delete a specific to-do
     *
     * @param token  user account id
     * @param todoId id of to-do we want to delete
     * @return response entity containing deleted to-do and http status 200 / 401 / 404
     */
    @DeleteMapping("/delete/{todoId}")
    public ResponseEntity<Todo> deleteTodoById(@RequestParam(value = "token") Integer token,
                                               @PathVariable(value = "todoId") Integer todoId) {
        log.info("Deleting ToDo from list.");
        if (!Objects.equals(todosService.getTodo(todoId).getUserId(), token) && userService.isAdmin(token)) {
            log.warn("Only owner can delete ToDo from his/her ToDo list.");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be the owner of the account.");
        }
        log.info("ToDo with id:{} deleted successfully.", todoId);
        return new ResponseEntity<>(todosService.deleteTodo(todoId), HttpStatus.OK);
    }

    /**
     * Endpoint used to edit a specific to-do
     *
     * @param token   user account id
     * @param todoId  id of to-do we want to update
     * @param todoDto data transfer object for To-do class
     * @return response entity containing to-do and http status 200 / 400 / 404
     */
    @PutMapping("/edit/{todoId}")
    public ResponseEntity<Todo> updateTodoById(@RequestParam(value = "token") Integer token,
                                               @PathVariable(value = "todoId") Integer todoId,
                                               @RequestBody TodoUpdate todoDto) {
        log.info("Request to edit ToDo with id:{} based on data given in data transfer object.", todoId);
        if (!Objects.equals(token, todoDto.getUserId())) {
            log.warn("Only owner can edit ToDo from his/her ToDo list");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You cant edit foreign todo");
        }
        log.info("ToDo with id:{} edited successfully.", todoId);
        return new ResponseEntity<>(todosService.updateTodo(todoId, todoDto), HttpStatus.OK);
    }

    /**
     * Endpoint returning a list of all users todos
     *
     * @param token user account id whose messages we want to show
     * @return list of users todos and http status 200 / 404
     */
    @GetMapping("/get")
    public ResponseEntity<List<Todo>> getAllTodos(@RequestParam(value = "token") Integer token) {
        log.info("Getting all ToDos for user with id:{}.", token);
        // TODO handle if admin checks todos
        return new ResponseEntity<>(todosService.getAllTodosForUser(token), HttpStatus.OK);
    }
}
