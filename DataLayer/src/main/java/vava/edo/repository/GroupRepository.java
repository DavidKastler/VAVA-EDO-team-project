package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vava.edo.model.Group;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    Group findByGroupName(String username);
    List<Group> findAllByGroupNameLike(String username);

    @Query("select (count(g) > 0) from Group g where g.groupCreator.uId = ?1")
    Boolean existsByGroupCreatorUId(Integer creatorId);

    @Query("select g from Group g where g.groupCreator.uId = ?1")
    List<Group> findAllByGroupCreatorUId(Integer creatorId);
}
