package vava.edo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vava.edo.model.Role;
import vava.edo.model.Task;
import vava.edo.model.User;
import vava.edo.model.exeption.TaskNotFoundException;
import vava.edo.model.exeption.UserNotFoundException;
import vava.edo.repository.TaskRepository;
import vava.edo.schema.TaskCreate;
import vava.edo.schema.TaskUpdate;
import vava.edo.schema.UserRegister;

import javax.persistence.Tuple;
import java.lang.invoke.CallSite;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.sql.Date;

/**
 * Service that operates over todos database table
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    /**
     * Method returns all tasks in database for given user
     * @param userId    id of user whose tasks you want to see
     * @return list of users tasks
     */
    public List<Task> getAllTasks(int userId) { return taskRepository.findAllByUserId(userId); }

    /**
     * Method returns task from taskId if task exists, otherwise throws
     * @param taskId    id of task we want to find
     * @return  task object
     */
    public Task getTask(int taskId) {
        return taskRepository.findById(taskId).orElseThrow(
                () -> new TaskNotFoundException(taskId));
    }

    /**
     * Method returns users completed tasks from first to second index
     * @param userId id of user whose tasks we wish to return
     * @param fromIndex index of first task in list
     * @param toIndex index of last task in list
     * @return list of task objects
     */
    public List<Task> getCompletedTasks(int userId, int fromIndex, int toIndex) {
        return taskRepository.findAllByUserIdAndCompletedOrderByDueTime(userId, true, PageRequest.of(fromIndex, toIndex));
    }

    /**
     * Method returns all users tasks between two times
     * @param userId    id of searched user
     * @param fromTime  time to search from in unix format
     * @param toTime    time to search to in unix format
     * @return          list of found task objects
     */
    public List<Task> getTasksByTimeRange(int userId, long fromTime, long toTime) {
        Timestamp fromTimestamp = new Timestamp(fromTime*1000);
        Timestamp toTimestamp = new Timestamp(toTime*1000);
        return taskRepository.findAllByUserIdAndDueTimeBetween(userId, fromTimestamp, toTimestamp);
    }


    /**
     * Method converts DTO object to Task object
     * and saves it to database
     * @param taskDto   task Data Transfer Object you want to convert to task
     * @return          created user
     */
    public Task createTask(TaskCreate taskDto) {

        Task task = Task.from(taskDto);
        taskRepository.save(task);

        return task;
    }

    /**
     * Method for deleting tasks from database by ID
     * @param taskId    ID of task you want to delete
     * @return          deleted task
     */
    public Task deleteTask(int taskId) {
        Task task = getTask(taskId);
        taskRepository.delete(task);
        return task;
    }

    /**
     * Method returns userId from given task identified by taskId
     * @param taskId    id of searched task
     * @return  int userId
     */
    public int getUserIdFromTask(int taskId) {
        Task task = getTask(taskId);

        return task.getUserId();
    }

    /**
     * Method for updating tasks from database by ID
     * @param taskId    ID of task you want to update
     * @param taskDto   task Data Transfer Object for updating task
     * @return          updated task
     */
    @Transactional
    public Task updateTask(int taskId, TaskUpdate taskDto) {
        Task taskToUpdate = getTask(taskId);

        taskToUpdate.setTaskName(taskDto.getTaskName());
        taskToUpdate.setTaskDescription(taskDto.getTaskDescription());
        taskToUpdate.setCompleted(taskDto.isCompleted());

        return taskToUpdate;
    }


}
