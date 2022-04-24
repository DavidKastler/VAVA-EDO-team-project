package vava.edo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Group;
import vava.edo.schema.groups.GroupCreate;
import vava.edo.service.GroupMembersService;
import vava.edo.service.GroupService;
import vava.edo.service.UserService;

import java.util.List;

/**
 * Class that provides endpoints for groups operations
 */
@Log4j2
@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;
    private final UserService userService;
    private final GroupMembersService groupMembersService;

    @Autowired
    public GroupController(GroupService groupService, UserService userService, GroupMembersService groupMembersService) {
        this.groupService = groupService;
        this.userService = userService;
        this.groupMembersService = groupMembersService;
    }

    /**
     * Endpoint creating a group
     * @param groupDto new group
     * @return response entity containing new group
     */
    @PostMapping(value = "/create")
    public ResponseEntity<Object> createNewGroup(@RequestParam(value = "token") int token,
                                                 @RequestBody GroupCreate groupDto) {
        log.info("Creating new group.");
        if (!userService.isTeamLeader(token)) {
            log.warn("Cannot create new group due to a low access rights.");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "This action requires at least team leader privileges.");
        }
        Group newGroup = groupService.addGroup(groupDto);
        log.info("New group with name:{} created.", newGroup.getGroupName());
        return new ResponseEntity<>(newGroup, HttpStatus.CREATED);
    }

    /**
     * Endpoint deleting a group with given id
     * @param token   user account rights verification
     * @param groupId id of deleting group
     * @return response entity containing deleted group
     */
    @DeleteMapping(value = "/delete/{groupId}")
    public ResponseEntity<Group> deleteGroupById(@RequestParam(value = "token") int token,
                                                  @PathVariable(value = "groupId") int groupId) {
        log.info("Deleting group from database.");
        if (!userService.isTeamLeader(token)) {
            log.warn("Permission denied, to delete a group need to be team leader.");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin, account manager" +
                    " or team leader privileges.");
        }
        groupMembersService.deleteAllMember(groupId);
        log.info("Group with {} was successfully deleted", groupId);
        return new ResponseEntity<>(groupService.deleteGroup(groupId), HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint updating group based on given group id
     * @param token         user id
     * @param group_id      group id
     * @param updatedGroup  updated DTO object
     * @return              updated group
     */
    @PutMapping("/update/{group_id}")
    public ResponseEntity<Group> updateGroup(@RequestParam(value = "token") Integer token,
                                             @PathVariable String group_id,
                                             @RequestBody GroupCreate updatedGroup) {
        log.info("Updating group.");
        if (updatedGroup.getCreatorId() != token) {
            log.warn("Request failed, only group owner can update group.");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not the creator");
        }
        log.info("Group successfully updated.");
        return new ResponseEntity<Group>(groupService.editGroup(token, updatedGroup), HttpStatus.OK);
    }

    /**
     * Endpoint returning a list of all groups
     * @param token user account rights verification
     * @return list of groups
     */
    @GetMapping(value = "/all")
    public ResponseEntity<List<Group>> getAllGroups(@RequestParam(value = "token") int token) {
        log.info("Looking for all groups.");
        if (!userService.isAdmin(token)) {
            log.warn("Permission denied, not enough rights to search other groups.");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin privileges.");
        }
        log.info("Found all groups.");
        return new ResponseEntity<>(groupService.getAllGroups(), HttpStatus.OK);
    }
}
