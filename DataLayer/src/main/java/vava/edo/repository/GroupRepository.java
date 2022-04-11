package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vava.edo.model.Group;

public interface GroupRepository extends JpaRepository<Group, Integer>{

    Group findByGroupName(String username);
}
