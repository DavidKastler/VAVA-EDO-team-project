package vava.edo.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Group;
import vava.edo.model.GroupMembers;
import vava.edo.model.User;
import vava.edo.repository.GroupMembersRepository;
import vava.edo.schema.GroupAddMember;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that operates over 'group_members' database table
 */
@Service
public class GroupMembersService {
    private final GroupMembersRepository groupMembersRepository;
    private final UserService userService;
    private final GroupService groupService;

    public GroupMembersService
            (GroupMembersRepository groupMembersRepository, UserService userService, GroupService groupService) {
        this.groupMembersRepository = groupMembersRepository;
        this.userService = userService;
        this.groupService = groupService;
    }


    /**
     * Method returns all group and their members in database
     * @return list of all group members
     */
    public List<GroupMembers> getAllGroupMembers() {
        return groupMembersRepository.findAll();
    }


    /**
     * Method returns members within given group
     * @param groupId group id which will be checked
     * @return list of all members of given group
     */
    public List<GroupMembers> getGroupMembersById(Integer groupId) {
        if (groupId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group id is none.");
        }
        return groupMembersRepository.findAllGroupMembersByGroupId(groupId);
    }


    /**
     * Method converts DTO object to GroupMembers object, finds wanted group for it if exists and saves it to it
     * @param groupMemberDto group member Data Transfer Object you want to convert to GroupMembers
     * @return added user to a group
     */
    public GroupMembers addMember(GroupAddMember groupMemberDto) {
        User user = userService.getUser(groupMemberDto.getUserId());
        Group group = groupService.getGroup(groupMemberDto.getGroupId());
        GroupMembers groupMembers = new GroupMembers();
        groupMembers.setMember(user);
        groupMembers.setGroup(group);

        groupMembersRepository.save(groupMembers);
        return groupMembers;
    }


    /**
     * Method for removing member from a group
     *
     * @param groupId ID of group where user is
     * @param userId  ID of user who will be removed from group
     * @return removed group member
     */
    public GroupMembers deleteMember(int groupId, int userId) {
        GroupMembers groupMember = groupMembersRepository.findByGroupIdAndMemberId(groupId, userId);

        groupMembersRepository.delete(groupMember);
        return groupMember;
    }


    /**
     * Method for removing all members from a group
     *
     * @param groupId ID of group which will be cleaned
     */
    public List<GroupMembers> deleteAllMember(int groupId) {
        List<GroupMembers> groupMember = groupMembersRepository.findAllGroupMembersByGroupId(groupId);

        groupMembersRepository.deleteAll(groupMember);
        return groupMember;
    }


    /**
     * Method for finding all group of given member
     *
     * @param userId user ID
     * @return list of groups where the given user is
     */
    public List<Group> getMyGroups(int userId) {
        List<GroupMembers> groupsWithMembers = groupMembersRepository.findAllByMemberId(userId);
        List<Group> myGroups = new ArrayList<>();
        for (GroupMembers gm : groupsWithMembers) {
            myGroups.add(gm.getGroup());
        }

        return myGroups;
    }


    /**
     * Method used to check whether user is part of given group
     * @param userId    id of user who you want to check
     * @param  groupId  id of group to check egains
     * @return  boolean true/false
     */
    public boolean isUserInGroup(int userId, int groupId) {
        return groupMembersRepository.existsByGroupIdAndMemberId(groupId, userId);
    }
}
