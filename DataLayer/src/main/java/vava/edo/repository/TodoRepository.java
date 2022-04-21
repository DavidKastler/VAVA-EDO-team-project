package vava.edo.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vava.edo.model.Todo;

import java.sql.Timestamp;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    List<Todo> findAllByGroupId(Integer userId);

    List<Todo> findAllByGroupIdAndCompletedOrderByFromTime(Integer userId, boolean completed, Pageable pageable);

    //getTasksByTimeRange
    List<Todo> findAllByGroupIdAndFromTimeBetween(Integer userId, Timestamp fromTime, Timestamp toTime);
//    List<Todo> findAllByUserIdAndDueTimeBetween(Integer userId, Timestamp fromTime, Timestamp toTime);

}
