package vava.edo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.TodoGroup;
import vava.edo.schema.todoGroup.CreateTodoGroup;
import vava.edo.service.TodoGroupService;
import vava.edo.service.UserService;

import java.util.List;

/**
 * Class that provides endpoints for operation with to-do groups
 */
@RestController
@RequestMapping("/todoGroups")
public class TodoGroupController {

    TodoGroupService todoGroupService;
    UserService userService;

    @Autowired
    public TodoGroupController(TodoGroupService todoGroupService, UserService userService) {
        this.todoGroupService = todoGroupService;
        this.userService = userService;
    }


    @PostMapping("create")
    public TodoGroup createTodoGroup(@RequestParam(value = "token") Integer token,
                                     @RequestBody CreateTodoGroup createTodoGroup) {
        // if it is for other user you have to be team-leader at least
        if (!token.equals(createTodoGroup.getUserId()) && !userService.isTeamLeader(token)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You need to be team-leader at least");
        }

        return todoGroupService.createTodoGroup(createTodoGroup);
    }


    @GetMapping("get")
    public List<TodoGroup> getTodoGroupsForUserId(@RequestParam(value = "token") Integer token) {
        return todoGroupService.getTodoGroupsForUserId(token);
    }


    @PutMapping("edit/{todoGroupId}")
    public TodoGroup editTodoGroup(@RequestParam(value = "token") Integer token,
                                   @PathVariable(value = "todoGroupId") Integer todoGroupId,
                                   @RequestBody CreateTodoGroup todoGroup) {
        return todoGroupService.editTodoGroup(todoGroupId, todoGroup);
    }

    @DeleteMapping("delete/{todoGroupId}")
    public TodoGroup deleteTodoGroup(@RequestParam(value = "token") Integer token,
                                     @PathVariable(value = "todoGroupId") Integer todoGroupId) {
        //if (todoGroupService.getTodoGroupsForUserId(token))
        // TODO skontrolovat ci userovi patri todogroup
        return todoGroupService.deleteGroup(todoGroupId);
    }

}
