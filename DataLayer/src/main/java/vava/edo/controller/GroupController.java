package vava.edo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Group;
import vava.edo.model.GroupMembers;
import vava.edo.schema.GroupAddMember;
import vava.edo.schema.GroupCreate;
import vava.edo.service.GroupMembersService;
import vava.edo.service.GroupService;
import vava.edo.service.UserService;

import java.util.List;

/**
 * Class that provides endpoints for groups operations
 */
@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;
    private final UserService userService;
    private final GroupMembersService groupMembersService;

    @Autowired
    public GroupController(GroupService groupService, UserService userService, GroupMembersService groupMembersService) {

//    public GroupController(GroupService groupService, UserService userService) {
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
        if (!userService.isTeamLeader(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "This action requires at least team leader privileges.");
        }
        Group newGroup = groupService.addGroup(groupDto);
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
        if (!userService.isTeamLeader(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin, account manager" +
                    " or team leader privileges.");
        }
        groupMembersService.deleteAllMember(groupId);
        return new ResponseEntity<>(groupService.deleteGroup(groupId), HttpStatus.NO_CONTENT);
    }


    @PutMapping("/update/{group_id}")
    public ResponseEntity<Group> updateGroup(@RequestParam(value = "token") Integer token,
                                             @PathVariable String group_id,
                                             @RequestBody GroupCreate updatedGroup) {
        if (updatedGroup.getCreatorId() != token) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not the creator");
        }
        return new ResponseEntity<Group>(groupService.editGroup(token, updatedGroup), HttpStatus.OK);
    }

    @GetMapping("get")
    public List<Group> getUserOwnedGroups() {
        return null; //TODO spravit
    }


    /**
     * Endpoint returning a list of all groups
     * @param token user account rights verification
     * @return list of groups
     */
    @GetMapping(value = "/all")
    public ResponseEntity<List<Group>> getAllGroups(@RequestParam(value = "token") int token) {
        if (!userService.isAdmin(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin privileges.");
        }
        return new ResponseEntity<>(groupService.getAllGroups(), HttpStatus.OK);
    }

    /**
     * Endpoint returning a group with given id
     *
     * @param token   user account rights verification
     * @param groupId id of wanted group
     * @return group
     */
    @GetMapping(value = "/get/{groupId}")
    public ResponseEntity<Group> getGroupById(@RequestParam(value = "token") int token,
                                              @PathVariable(value = "groupId") int groupId) {
        if (!userService.isAdmin(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin privileges.");
        }

        Group group = groupService.getGroup(groupId);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }


    /**
     * Endpoint returning a groups which name contains given string
     *
     * @param token     user account rights verification
     * @param groupName string which will be looking for
     * @return list of groups
     */
    @GetMapping(value = "/get/name/{groupName}")
    public ResponseEntity<List<Group>> getGroupByName(@RequestParam(value = "token") int token,
                                                      @PathVariable(value = "groupName", required = false) String groupName) {
        String wantedGroupName = null;
        if (groupName != null && userService.isAdmin(token)) {
            wantedGroupName = groupName;
        }

        List<Group> groups = groupService.findGroupsMatchingName(wantedGroupName);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }


    /*
     * Endpoint returning a group with new name
     *
     * @param token    user account rights verification
     * @param groupId  id of wanted group
     * @param groupDto groupDto class with updated parameter
     * @return group with changes parameter
     */
    /*@PostMapping(value = "/changeGroupName/{groupId}")
    public ResponseEntity<Object> changeGroupName(@RequestParam(value = "token") int token,
                                                  @PathVariable("groupId") Integer groupId,
                                                  @RequestBody GroupEdit groupDto) {
        if (token != groupService.getGroup(groupId).getGroupCreatorId().getUId()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action require you to be group owner.");
        }
        return new ResponseEntity<>(groupService.editGroupName(groupId, groupDto), HttpStatus.OK);
    }*/


    /**
     * Endpoint getting all group members
     * @param token user account rights verification
     * @return list of all members with their groups
     */
    @GetMapping(value = "/allGroupMembers")
    public ResponseEntity<List<GroupMembers>> getAllGroupMembers(@RequestParam(value = "token") int token) {
        if (!(userService.isAdmin(token) || userService.isAccountManager(token) || userService.isTeamLeader(token))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin, account manager" +
                    " or team leader privileges.");
        }
        return new ResponseEntity<>(groupMembersService.getAllGroupMembers(), HttpStatus.OK);
    }


    /**
     * Endpoint getting group members
     *
     * @param token   user account rights verification
     * @param groupId id of group
     * @return list of group members
     */
    @GetMapping(value = "/get/groupMembers/byID/{groupId}")
    public ResponseEntity<List<GroupMembers>> getGroupMembersById(@RequestParam(value = "token") int token,
                                                                  @PathVariable(value = "groupId") int groupId) {
        if (!userService.isPleb(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires at least pleb privileges.");
        }
        return new ResponseEntity<>(groupMembersService.getGroupMembersById(groupId), HttpStatus.OK);
    }


    /**
     * Endpoint getting user's groups
     *
     * @param token user account rights verification
     * @return list of groups
     */
    @GetMapping(value = "/get/myGroups")
    public ResponseEntity<List<Group>> getMyGroups(@RequestParam(value = "token") int token) {
        if (!userService.isPleb(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires at least pleb privileges.");
        }

        return new ResponseEntity<>(groupMembersService.getMyGroups(token), HttpStatus.OK);
    }


    /**
     * Endpoint adding a group member
     *
     * @param token          user account rights verification
     * @param groupMemberDto new group member
     * @return response entity containing new group member
     */
    @PostMapping(value = "/add/memberToGroup")
    public ResponseEntity<Object> addMemberToGroup(@RequestParam(value = "token") int token,
                                                   @RequestBody GroupAddMember groupMemberDto) {
        if (!(userService.isAdmin(token) || userService.isAccountManager(token) || userService.isTeamLeader(token))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin, account manager" +
                    " or team leader privileges.");
        }
        GroupMembers groupMember = groupMembersService.addMember(groupMemberDto);
        return new ResponseEntity<>(groupMember, HttpStatus.OK);
    }


    /**
     * Endpoint deleting a group member
     *
     * @param token   user account rights verification
     * @param userId  id of deleting member
     * @param groupId id of group with given user
     * @return response entity containing deleted group member
     */
    @DeleteMapping(value = "/del/member/{userId}/in/{groupId}")
    public ResponseEntity<Object> deleteGroupMemberById(@RequestParam(value = "token") int token,
                                                        @PathVariable(value = "userId") int userId,
                                                        @PathVariable(value = "groupId") int groupId) {
        if (!(userService.isAdmin(token) || userService.isAccountManager(token) || userService.isTeamLeader(token))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin, account manager" +
                    " or team leader privileges.");
        }
        GroupMembers gr = groupMembersService.deleteMember(groupId, userId);
        return new ResponseEntity<>(gr, HttpStatus.NO_CONTENT);
    }


}
