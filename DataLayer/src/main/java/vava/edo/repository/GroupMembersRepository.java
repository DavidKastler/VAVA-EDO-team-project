package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vava.edo.model.Group;
import vava.edo.model.GroupMembers;
import vava.edo.model.User;

import java.util.List;


public interface GroupMembersRepository extends JpaRepository<GroupMembers, Integer> {

    GroupMembers findByGroupIdAndMemberId(Group groupId, User userId);
    List<GroupMembers> findAllByGroupId(Group groupId);
    List<GroupMembers> findAllByMemberId(User userId);
}