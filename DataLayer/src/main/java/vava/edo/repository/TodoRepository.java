package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vava.edo.model.Todo;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    @Query("select t from Todo t where t.userId = ?1")
    List<Todo> findAllByUserId(Integer userId);

    @Query("select t from Todo t where t.userId = ?1 and t.todoId = ?2")
    Todo findByUserIdAndTodoId(Integer userId, Integer taskId);
}
