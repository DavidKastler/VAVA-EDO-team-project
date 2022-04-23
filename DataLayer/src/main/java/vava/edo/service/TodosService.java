package vava.edo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Todo;
import vava.edo.model.exeption.TaskNotFoundException;
import vava.edo.repository.TodoRepository;
import vava.edo.schema.TaskCreate;
import vava.edo.schema.TaskUpdate;

import java.util.*;

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
     * Method returns all tasks in database for given user id
     * @param userId    id of user whose tasks you want to see
     * @return list of users tasks
     */
    public List<Todo> getAllTasksForUser(int userId) {
        return todoRepository.findAllByUserId(userId);
    }

    /**
     * Method returns task from taskId if task exists, otherwise throws
     * @param taskId    id of task we want to find
     * @return  task object
     */
    public Todo getTask(int taskId) {
        return todoRepository.findById(taskId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with this id does not exist."));
    }

    /**
     * Method converts DTO object to Task object
     * and saves it to database
     * @param taskDto   task Data Transfer Object you want to convert to task
     * @return          created user
     */
    public Todo createTask(TaskCreate taskDto) {

        Todo todo = Todo.from(taskDto);
        todoRepository.save(todo);

        return todo;
    }

    /**
     * Method for deleting tasks from database by ID
     * @param taskId    ID of task you want to delete
     * @return          deleted task
     */
    public Todo deleteTask(int taskId) {
        Todo todo = getTask(taskId);
        todoRepository.delete(todo);
        return todo;
    }

    /**
     * Method for updating tasks from database by ID
     * @param taskId    ID of task you want to update
     * @param taskDto   task Data Transfer Object for updating task
     * @return          updated task
     */
    @Transactional
    public Todo updateTask(int taskId, TaskUpdate taskDto) {
        Todo todoToUpdate = getTask(taskId);

        todoToUpdate.setTodoName(taskDto.getTodoName());
        todoToUpdate.setTodoDescription(taskDto.getTodoDescription());
        todoToUpdate.setToTime(taskDto.getToTime());
        todoToUpdate.setFromTime(taskDto.getFromTime());
        todoToUpdate.setCompleted(taskDto.getCompleted());
        todoToUpdate.setGroupName(taskDto.getGroupName());

        return todoToUpdate;
    }

    /**
     * Method to invert completed variable
     * @param userId    user id
     * @param taskId    task id
     * @return          updated to-do
     */
    @Transactional
    public Todo invertCompleted(Integer userId, Integer taskId) {
        Todo todoToInvertComplete = todoRepository.findByUserIdAndTodoId(userId, taskId);
        if (todoToInvertComplete == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with this id does not exist.");
        }
        todoToInvertComplete.setCompleted(!todoToInvertComplete.isCompleted());

        return todoToInvertComplete;
    }
}
