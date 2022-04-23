package vava.edo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Group;
import vava.edo.model.GroupMember;
import vava.edo.service.GroupMembersService;
import vava.edo.service.GroupService;
import vava.edo.service.UserService;

import java.util.List;

/**
 * Class that provides endpoints for groupMembers operations
 */
@Log4j2
@RestController
@RequestMapping("/groupMembers")
public class GroupMemberController {
    private final GroupMembersService groupMembersService;
    private final GroupService groupService;
    private final UserService userService;

    @Autowired
    public GroupMemberController(GroupMembersService groupMembersService, GroupService groupService,
                                 UserService userService) {
        this.groupMembersService = groupMembersService;
        this.groupService = groupService;
        this.userService = userService;
    }

    @GetMapping("/groups")
    public ResponseEntity<List<Group>> getGroupsForUser(@RequestParam(value = "token") Integer token) {
        log.info("Get all groups for user.");
        return new ResponseEntity<>(groupMembersService.getMyGroups(token), HttpStatus.OK);
    }

    @GetMapping("/members/{group_id}")
    public ResponseEntity<List<GroupMember>> getMembersOfGroup(@RequestParam(value = "token") Integer token,
                                                               @PathVariable(value = "group_id") Integer groupId) {
        log.info("Members of chosen group.");
        if (!groupMembersService.isUserInGroup(token, groupId)) {
            log.warn("Request denied, insufficient rights to get all members of user.");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Your are not part of the group");
        }
        log.info("Users for group with id:{}.", groupId);
        return new ResponseEntity<>(groupMembersService.getGroupMembersById(groupId), HttpStatus.OK);
    }

    @PostMapping("/members/add/{group_id}")
    public ResponseEntity<List<GroupMember>> addMembersToGroup(@RequestParam(value = "token") Integer token,
                                                               @PathVariable(value = "group_id") Integer groupId,
                                                               @RequestBody List<Integer> groupMemberIds) {
        log.info("Add users to group with id:{}.",groupId);
        if (!groupService.isUserCreator(token) && !userService.isAccountManager(token)) {
            log.warn("Request denied, insufficient rights to add members.");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only group creator or admin can do this.");
        }

        List<GroupMember> groupMembers = groupMembersService.addMembers(groupId, groupMemberIds);
        log.info("Adding users to group finished successfully.");
        return new ResponseEntity<>(groupMembers, HttpStatus.CREATED);
    }
}
