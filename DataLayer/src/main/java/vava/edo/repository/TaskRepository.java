package vava.edo.repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vava.edo.model.Task;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findAllByUserId(Integer userId);

    List<Task> findAllByUserIdAndCompleted(Integer userId, boolean completed, Pageable pageable);

    List<Task> findAllByUserIdAndDueTime(Integer userId, Integer Month);

}
