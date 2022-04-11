package vava.edo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vava.edo.model.Group;
import vava.edo.model.Task;
import vava.edo.model.exeption.RoleNotFoundException;
import vava.edo.model.Role;
import vava.edo.repository.GroupRepository;
import vava.edo.repository.RoleRepository;

import java.util.List;

/**
 * Service that operates over groups database table
 */
@Service
public class GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository= groupRepository;
    }

    /**
     * Method used to check whether user is part of given group
     * @return  boolean true/false
     */
    public boolean checkUserGroup(int userId, int groupId)
    {
        Group group = groupRepository.findByMemberIdsAndGroupId(userId, groupId);
        if (group != null) return true;
        return false;
    }

    /**
     * Method returning a list of all users groups
     * @param memberId  user id
     * @return  list of group objects
     */
    public List<Group> getAllUsersGroups(int memberId) { return groupRepository.findAllByMemberIds(memberId); }

}