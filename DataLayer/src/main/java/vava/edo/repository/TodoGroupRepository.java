package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vava.edo.model.TodoGroup;

import java.util.List;

public interface TodoGroupRepository extends JpaRepository<TodoGroup, Integer> {

    @Query("select t from TodoGroup t where t.uId = ?1")
    List<TodoGroup> findTodoGroupsByUId(Integer userId);
}
