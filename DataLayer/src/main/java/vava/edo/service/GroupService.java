package vava.edo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import vava.edo.model.Group;
import vava.edo.model.User;
import vava.edo.model.exeption.GroupNotFoundException;
import vava.edo.repository.GroupRepository;
import vava.edo.schema.GroupCreate;
import vava.edo.schema.GroupEdit;


import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserService userService;

    @Autowired
    public GroupService(GroupRepository groupRepository, UserService userService) {
        this.groupRepository = groupRepository;
        this.userService = userService;
    }


    /**
     * Method finds group by its name
     * @param groupName name of group you are looking for
     * @return          found group by name
     */
    public Group getGroupByName(String groupName){
        if (groupName == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group name is none.");
        }
        Group group = groupRepository.findByGroupName(groupName);

        if (group == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found.");
        }

        return group;
    }

    //TODO talk to David about this
    public List<Group> getGroupByNameAsList(String groupName){
        if (groupName == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group name is none.");
        }
        List<Group> groups = groupRepository.findGroupByGroupName(groupName);

        if (groups == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found.");
        }

        return groups;
    }


    /**
     * Method returns all groups in database
     * @return  list of all groups
     */
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }


    /**
     * Method finds group by its ID, if it does not exist throws exception
     * @param groupId   group ID you are looking for
     * @return          found group
     */
    public Group getGroup(int groupId) {
        return groupRepository.findById(groupId).orElseThrow(
                () -> new GroupNotFoundException(groupId));
    }




    //TODO talk to David about non-unique group name
    //TODO spravi editGroup a dat optional parametre s tym ze dostanes GroupUpdate triedu
    @Transactional
    public Group editGroupName(int groupID, GroupEdit groupDto) {
        Group groupToEdit = getGroup(groupID);

        groupToEdit.setGroupName(groupDto.getGroupName());

        return groupToEdit;
    }


    /**
     * Method converts DTO object to Group object and saves it to database
     * @param groupDto  group Data Transfer Object you want to convert to group
     * @return          created group
     */
    public Group addGroup(GroupCreate groupDto) {
        User user = userService.getUser(groupDto.getCreatorId());
        Group group = Group.from(groupDto);
        group.setGroupCreatorId(user);

        groupRepository.save(group);
        return group;
    }

    /**
     * Method for deleting group from database by ID
     * @param groupId   group ID you want to delete
     * @return          deleted group
     */
    public Group deleteGroup(int groupId){
        Group group = getGroup(groupId);

        groupRepository.delete(group);
        return group;
    }

}
