package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vava.edo.model.Chat;
import vava.edo.model.Group;
import vava.edo.model.Task;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    List<Group> findAllByMemberIds(Integer id);

    Group findByMemberIdsAndGroupId(int userId, int groupId);
}
