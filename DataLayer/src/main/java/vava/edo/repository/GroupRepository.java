package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vava.edo.model.Group;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    Group findByGroupName(String username);
    List<Group> findAllByGroupNameLike(String username);
}
