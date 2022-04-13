package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vava.edo.model.Assignments;

public interface AssignmentsRepository extends JpaRepository<Assignments, Integer> {
}
