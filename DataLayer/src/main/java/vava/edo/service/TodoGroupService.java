package vava.edo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.TodoGroup;
import vava.edo.repository.TodoGroupRepository;
import vava.edo.schema.todoGroup.CreateTodoGroup;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Service that operates over todo_group database table
 */
@Service
public class TodoGroupService {

    private final TodoGroupRepository todoGroupRepository;
    private final UserService userService;

    @Autowired
    public TodoGroupService(TodoGroupRepository todoGroupRepository, UserService userService) {
        this.todoGroupRepository = todoGroupRepository;
        this.userService = userService;
    }


    public TodoGroup getTodoGroup(Integer todoGroupId) {
        return todoGroupRepository.findById(todoGroupId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo group not found"));
    }

    /**
     * Method that saves created todoGroup to database
     * @param todoGroupCreate   todoGroup you want to save
     * @return                  created todoGroup
     */
    public TodoGroup createTodoGroup(CreateTodoGroup todoGroupCreate) {
        TodoGroup todoGroup = TodoGroup.from(todoGroupCreate);
        return todoGroupRepository.save(todoGroup);
    }


    /**
     *
     * @param userId
     * @return
     */
    public List<TodoGroup> getTodoGroupsForUserId(Integer userId) {
        List<TodoGroup> todoGroups = todoGroupRepository.findTodoGroupsByUId(userId);
        if (todoGroups.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User has no todo groups");
        }
        return todoGroups;
    }


    /**
     *
     * @param todoGroupId
     * @param updatedTodoGroup
     * @return
     */
    @Transactional
    public TodoGroup editTodoGroup(Integer todoGroupId, CreateTodoGroup updatedTodoGroup) {
        TodoGroup todoGroup = getTodoGroup(todoGroupId);

        if (!userService.isTeamLeader(updatedTodoGroup.getUserId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "New creator has to be team-leader");
        }
        todoGroup.setUId(updatedTodoGroup.getUserId());
        todoGroup.setTdGroupName(updatedTodoGroup.getTodoGroupName());

        return todoGroup;
    }


    /**
     *
     * @param todoGroupId
     * @return
     */
    public TodoGroup deleteGroup(Integer todoGroupId) {
        TodoGroup todoGroup = getTodoGroup(todoGroupId);

        todoGroupRepository.delete(todoGroup);
        return todoGroup;
    }
}
