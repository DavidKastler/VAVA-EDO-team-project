package vava.edo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vava.edo.model.GroupMember;

import java.util.List;


public interface GroupMembersRepository extends JpaRepository<GroupMember, Integer> {

    @Query("select g from GroupMember g where g.group.groupId = ?1 and g.member.uId = ?2")
    GroupMember findByGroupIdAndMemberId(Integer groupId, Integer userId);

    @Query("select g from GroupMember g where g.group.groupId = ?1")
    List<GroupMember> findAllGroupMembersByGroupId(Integer groupId);

    @Query("select g from GroupMember g where g.member.uId = ?1")
    List<GroupMember> findAllByMemberId(Integer userId);

    @Query("select (count(g) > 0) from GroupMember g where g.group.groupId = ?1 and g.member.uId = ?2")
    Boolean existsByGroupIdAndMemberId(Integer groupId, Integer userId);
}