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
     * @return list of users tasks
     */
    public List<Task> getAllTasks(int userId) { return taskRepository.findAllByUserId(userId); }

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
    public List<Task> getCompletedTasks(int userId, int fromIndex, int toIndex)
    {
        List<Task> pageOfTasks = taskRepository.findAllByUserIdAndCompletedOrderByDueTime(userId, true, PageRequest.of(fromIndex, toIndex));

        return pageOfTasks;

    }


    public List<Task> getTasksByMonth(int userId, Integer month){

        Map<Integer, Date[]> months = new HashMap<>();
        months.put(1, new Date[]{Date.valueOf("2022-01-01"), Date.valueOf("2022-01-31")});
        months.put(2, new Date[]{Date.valueOf("2022-02-01"), Date.valueOf("2022-02-28")});
        months.put(3, new Date[]{Date.valueOf("2022-03-01"), Date.valueOf("2022-03-31")});
        months.put(4, new Date[]{Date.valueOf("2022-04-01"), Date.valueOf("2022-04-30")});
        months.put(5, new Date[]{Date.valueOf("2022-05-01"), Date.valueOf("2022-05-31")});
        months.put(6, new Date[]{Date.valueOf("2022-06-01"), Date.valueOf("2022-06-30")});
        months.put(7, new Date[]{Date.valueOf("2022-07-01"), Date.valueOf("2022-07-31")});
        months.put(8, new Date[]{Date.valueOf("2022-08-01"), Date.valueOf("2022-08-31")});
        months.put(9, new Date[]{Date.valueOf("2022-09-01"), Date.valueOf("2022-09-30")});
        months.put(10, new Date[]{Date.valueOf("2022-10-01"), Date.valueOf("2022-10-31")});
        months.put(11, new Date[]{Date.valueOf("2022-11-01"), Date.valueOf("2022-11-30")});
        months.put(12, new Date[]{Date.valueOf("2022-12-01"), Date.valueOf("2022-12-31")});

        return taskRepository.findAllByUserIdAndDueTimeBetween(userId, months.get(month)[0], months.get(month)[1]);
    }

    @Transactional
    /**
     * Method converts DTO object to Task object
     * and saves it to database
     * @param userDto   task Data Transfer Object you want to convert to task
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
     * Method for updating tasks from database by ID
     * @param taskId    ID of task you want to update
     * @param taskDto   task Data Transfer Object for updating task
     * @return          updated task
     */
    public Task updateTask(int taskId, TaskUpdate taskDto) {
        Task taskToUpdate = getTask(taskId);

        taskToUpdate.setTaskName(taskDto.getTaskName());
        taskToUpdate.setTaskDescription(taskDto.getTaskDescription());
        taskToUpdate.setCompleted(taskDto.isCompleted());

        taskRepository.save(taskToUpdate);
        return taskToUpdate;
    }


}
