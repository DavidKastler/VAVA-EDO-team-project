package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vava.edo.model.GroupMembers;
import vava.edo.model.User;

import java.util.List;


public interface GroupMembersRepository extends JpaRepository<GroupMembers, Integer> {

    @Query("select g from GroupMembers g where g.group.grId = ?1 and g.member.uId = ?2")
    GroupMembers findByGroupIdAndMemberId(Integer groupId, Integer userId);
    @Query("select g from GroupMembers g where g.group.grId = ?1")
    List<GroupMembers> findAllGroupMembersByGroupId(Integer groupId);
    @Query("select g from GroupMembers g where g.member.uId = ?1")
    List<GroupMembers> findAllByMemberId(Integer userId);
    @Query("select (count(g) > 0) from GroupMembers g where g.group.grId = ?1 and g.member.uId = ?2")
    Boolean existsByGroupIdAndMemberId(Integer groupId, Integer userId);
}