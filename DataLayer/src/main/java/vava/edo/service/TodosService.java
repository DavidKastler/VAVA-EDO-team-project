package vava.edo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Todo;
import vava.edo.repository.TodoRepository;
import vava.edo.schema.todos.TodoCreate;
import vava.edo.schema.todos.TodoUpdate;

import java.util.List;

/**
 * Service that operates over todos database table
 */
@Service
public class TodosService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodosService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /**
     * Method returns all todos in database for given user id
     *
     * @param userId id of user whose todos you want to see
     * @return list of users todos
     */
    public List<Todo> getAllTodosForUser(int userId) {
        return todoRepository.findAllByUserId(userId);
    }

    /**
     * Method returns to-do from to-do id if to-do exists, otherwise throws
     *
     * @param todoId id of to-do we want to find
     * @return found to-do object
     */
    public Todo getTodo(Integer todoId) {
        return todoRepository.findById(todoId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo with this id does not exist."));
    }

    /**
     * Method converts DTO object to To-do object
     * and saves it to database
     *
     * @param todoDto to-do Data Transfer Object you want to convert to To-do
     * @return created user
     */
    public Todo createTodo(TodoCreate todoDto) {

        Todo todo = Todo.from(todoDto);
        todoRepository.save(todo);

        return todo;
    }

    /**
     * Method for deleting todos from database by ID
     *
     * @param todoId ID of to-do you want to delete
     * @return deleted to-do
     */
    public Todo deleteTodo(Integer todoId) {
        Todo todo = getTodo(todoId);
        todoRepository.delete(todo);
        return todo;
    }

    /**
     * Method for updating todos from database by ID
     *
     * @param todoId  ID of to-do you want to update
     * @param todoDto to-do Data Transfer Object for updating to-do
     * @return updated to-do
     */
    @Transactional
    public Todo updateTodo(Integer todoId, TodoUpdate todoDto) {
        Todo todoToUpdate = getTodo(todoId);

        todoToUpdate.setTodoName(todoDto.getTodoName());
        todoToUpdate.setTodoDescription(todoDto.getTodoDescription());
        todoToUpdate.setToTime(todoDto.getToTime());
        todoToUpdate.setFromTime(todoDto.getFromTime());
        todoToUpdate.setCompleted(todoDto.isCompleted());
        todoToUpdate.setGroupName(todoDto.getGroupName());

        return todoToUpdate;
    }

    /**
     * Method to invert completed variable in to-do
     *
     * @param userId user id
     * @param todoId to-do id
     * @return updated to-do
     */
    @Transactional
    public Todo invertCompleted(Integer userId, Integer todoId) {
        Todo todoToInvertComplete = todoRepository.findByUserIdAndTodoId(userId, todoId);
        if (todoToInvertComplete == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with this id does not exist.");
        }
        todoToInvertComplete.setCompleted(!todoToInvertComplete.isCompleted());

        return todoToInvertComplete;
    }
}
