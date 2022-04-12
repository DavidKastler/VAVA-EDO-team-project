package vava.edo.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Group;
import vava.edo.model.GroupMembers;
import vava.edo.model.User;
import vava.edo.repository.GroupMembersRepository;
import vava.edo.schema.GroupUpdate;

import java.util.List;

@Service
public class GroupMembersService {
    private final GroupMembersRepository groupMembersRepository;
    private final UserService userService;
    private final GroupService groupService;

    public GroupMembersService(GroupMembersRepository groupMembersRepository, UserService userService, GroupService groupService) {
        this.groupMembersRepository = groupMembersRepository;
        this.userService = userService;
        this.groupService = groupService;
    }


    public List<GroupMembers> getAllGroupMembers() {
        return groupMembersRepository.findAll();
    }


    public List<GroupMembers> getGroupMembersById(Group group) {
        if (group == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group id is none.");
        }
        return groupMembersRepository.findAllByGroupId(group);
    }


    public GroupMembers addMember(GroupUpdate groupMemberDto) {
        GroupMembers groupMembers = GroupMembers.from();
        groupMembers.setMemberId(userService.getUser(groupMemberDto.getUserId()));
        groupMembers.setGroupId(groupService.getGroup(groupMemberDto.getGroupId()));

        groupMembersRepository.save(groupMembers);
        return groupMembers;
    }


    public GroupMembers deleteMember(int groupId, int userId){
        Group group = groupService.getGroup(groupId);
        User member = userService.getUser(userId);
        GroupMembers groupMember = groupMembersRepository.findByGroupIdAndMemberId(group, member);

        groupMembersRepository.delete(groupMember);
        return groupMember;
    }


    public List<GroupMembers> deleteAllMember(int groupId){
        Group group = groupService.getGroup(groupId);
        List<GroupMembers> groupMember = groupMembersRepository.findAllByGroupId(group);

        groupMembersRepository.deleteAll(groupMember);
        return groupMember;
    }
}
