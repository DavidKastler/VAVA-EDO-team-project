package vava.edo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Group;
import vava.edo.model.User;
import vava.edo.repository.GroupMembersRepository;
import vava.edo.repository.GroupRepository;
import vava.edo.schema.GroupCreate;

import java.util.List;

/**
 * Service that operates over 'group' database table
 */
@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserService userService;
    private final GroupMembersRepository groupMembersRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository, UserService userService, GroupMembersRepository groupMembersRepository) {
        this.groupRepository = groupRepository;
        this.userService = userService;
        this.groupMembersRepository = groupMembersRepository;
    }

    /**
     * Method finds group by its name
     * @param groupName name of group you are looking for
     * @return found group by name
     */
    public Group getGroupByName(String groupName) {
        if (groupName == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group name is none.");
        }
        Group group = groupRepository.findByGroupName(groupName);

        if (group == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found.");
        }

        return group;
    }

    /**
     * Method finds all groups which name contains given string
     * @param groupName string which will be looking for
     * @return list of groups
     */
    public List<Group> findGroupsMatchingName(String groupName) {
        if (groupName == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group name is none.");
        }
        List<Group> groups = groupRepository.findAllByGroupNameLike(groupName);

        if (groups == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found.");
        }

        return groups;
    }

    /**
     * Method returns all groups in database
     * @return list of all groups
     */
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }


    /**
     * Method finds group by its ID, if it does not exist throws exception
     * @param groupId group ID you are looking for
     * @return found group
     */
    public Group getGroup(int groupId) {
        return groupRepository.findById(groupId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group with this id does not exist."));
    }

    /**
     * Method that updates found group by ID based on given GroupEdit class parameter
     * @param groupID  group ID you want to change
     * @param groupDto groupDto class with updated parameter
     * @return updated group
     */
    @Transactional
    public Group editGroup(int groupID, GroupCreate groupDto) {
        Group groupToEdit = getGroup(groupID);
        User user = userService.getUser(groupDto.getCreatorId());

        groupToEdit.setGroupName(groupDto.getGroupName());
        groupToEdit.setGroupCreator(user);

        return groupToEdit;
    }

    /**
     * Method converts DTO object to Group object and saves it to database
     * @param groupDto group Data Transfer Object you want to convert to group
     * @return created group
     */
    public Group addGroup(GroupCreate groupDto) {
        User user = userService.getUser(groupDto.getCreatorId());
        Group group = Group.from(groupDto);
        group.setGroupCreator(user);

        return groupRepository.save(group);
    }

    /**
     * Method for deleting group from database by ID
     * @param groupId group ID you want to delete
     * @return deleted group
     */
    public Group deleteGroup(int groupId) {
        Group group = getGroup(groupId);

        groupRepository.delete(group);
        return group;
    }

    /**
     * Method that checks if user is creator
     * @param userId    id of user you want to check
     * @return          true/ false
     */
    public boolean isUserCreator(Integer userId) {
        return groupRepository.existsByGroupCreatorUId(userId);
    }

    /**
     * Method that chcecks if user is group creator and other user is his member
     * @param creatorId id of a creator
     * @param userId    if of a member
     * @return          true/ false
     */
    public boolean isUserCreatorsGroupMember(Integer creatorId, Integer userId) {
        List<Group> creatorsGroups = groupRepository.findAllByGroupCreatorUId(creatorId);
        if (creatorsGroups.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Given user does not own any group.");
        }
        for (Group group : creatorsGroups) {
            // due to cyclic import has to be repo
            if (groupMembersRepository.existsByGroupIdAndMemberId(group.getGrId(), userId)) {
                return true;
            }
        }
        return false;
    }

}
