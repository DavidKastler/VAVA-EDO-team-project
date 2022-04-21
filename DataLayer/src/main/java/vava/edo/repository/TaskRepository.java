package vava.edo.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vava.edo.model.Todo;

import java.sql.Timestamp;
import java.util.List;

public interface TaskRepository extends JpaRepository<Todo, Integer> {

    List<Todo> findAllByUserId(Integer userId);

    List<Todo> findAllByUserIdAndCompletedOrderByDueTime(Integer userId, boolean completed, Pageable pageable);

    List<Todo> findAllByUserIdAndDueTimeBetween(Integer userId, Timestamp fromTime, Timestamp toTime);

}
