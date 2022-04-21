package vava.edo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vava.edo.model.Todo;
import vava.edo.model.exeption.TaskNotFoundException;
import vava.edo.repository.TodoRepository;
import vava.edo.schema.TaskCreate;
import vava.edo.schema.TaskUpdate;

import java.sql.Timestamp;
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
     * Method returns all tasks in database for given user
     * @param userId    id of user whose tasks you want to see
     * @return list of users tasks
     */
    public List<Todo> getAllTasks(int userId) {
        return todoRepository.findAllByGroupId(userId);
    }

    /**
     * Method returns task from taskId if task exists, otherwise throws
     * @param taskId    id of task we want to find
     * @return  task object
     */
    public Todo getTask(int taskId) {
        return todoRepository.findById(taskId).orElseThrow(
                () -> new TaskNotFoundException(taskId));
    }

    /**
     * Method returns users completed tasks from first to second index
     * @param userId id of user whose tasks we wish to return
     * @param fromIndex index of first task in list
     * @param toIndex index of last task in list
     * @return list of task objects
     */
    public List<Todo> getCompletedTasks(int userId, int fromIndex, int toIndex) {
        return todoRepository.findAllByGroupIdAndCompletedOrderByFromTime(userId, true, PageRequest.of(fromIndex, toIndex));
    }

    /**
     * Method returns all users tasks between two times
     * @param userId    id of searched user
     * @param fromTime  time to search from in unix format
     * @param toTime    time to search to in unix format
     * @return          list of found task objects
     */
    public List<Todo> getTasksByTimeRange(Integer userId, long fromTime, long toTime) {
        return todoRepository.findAllByGroupIdAndFromTimeBetween(userId, fromTime, toTime);
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
     * Method returns userId from given task identified by taskId
     * @param taskId    id of searched task
     * @return  int userId
     */
    public int getUserIdFromTask(int taskId) {
        Todo todo = getTask(taskId);

        //return todo.getUserId();
        return 0;
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
        todoToUpdate.setCompleted(taskDto.isCompleted());

        return todoToUpdate;
    }


}
