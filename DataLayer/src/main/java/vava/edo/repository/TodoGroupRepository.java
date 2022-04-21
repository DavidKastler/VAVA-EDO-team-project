package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vava.edo.model.TodoGroup;

public interface TodoGroupRepository extends JpaRepository<TodoGroup, Integer> {
}
