package vava.edo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Group;
import vava.edo.model.GroupMembers;
import vava.edo.model.User;
import vava.edo.schema.GroupCreate;
import vava.edo.schema.GroupUpdate;
import vava.edo.service.GroupMembersService;
import vava.edo.service.GroupService;
import vava.edo.service.UserService;

import java.util.List;

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
     * Endpoint returning a list of all groups
     * @param token     user account rights verification
     * @return          list of groups
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
     * @param token     user account rights verification
     * @param groupId   id of wanted group
     * @return          group
     */
    @GetMapping(value = "/get/{groupId}")
    public ResponseEntity<Group> getGroupById(@RequestParam(value = "token") int token,
                                              @PathVariable(value = "groupId") int groupId) {
        if (!userService.isAdmin(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin privileges.");
        }


        Group group = groupService.getGroup(groupId);
//        System.out.println(group.toString());
        return new ResponseEntity<>(group, HttpStatus.OK);
    }


    /**
     * Endpoint returning a group with given name
     * @param token     user account rights verification
     * @param groupName name of wanted group
     * @return          group
     */
    @GetMapping(value = "/get/name/{groupName}")
    public ResponseEntity<Group> getGroupByName(@RequestParam(value = "token") int token,
                                              @PathVariable(value = "groupName", required = false) String groupName) {
        String wantedGroupName = null;
        if (groupName != null && userService.isAdmin(token)) {
            wantedGroupName = groupName;
        }

        Group group = groupService.getGroupByName(wantedGroupName);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }


    //TODO Talk to David about non-unique group name
    /**
     *
     * @param token
     * @param groupName
     * @return
     */
    @GetMapping(value = "/get/list/name/{groupName}")
    public ResponseEntity<List<Group>> getGroupByNameAsList(@RequestParam(value = "token") int token,
                                                @PathVariable(value = "groupName", required = false) String groupName) {
        String wantedGroupName = null;
        if (groupName != null && userService.isAdmin(token)) {
            wantedGroupName = groupName;
        }

        List<Group> group = groupService.getGroupByNameAsList(wantedGroupName);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }


    /**
     * Endpoint creating a group
     * @param groupDto  new group
     * @return          response entity containing new group
     */
    @PostMapping(value = "/create")
    public ResponseEntity<Object> createNewGroup(@RequestBody GroupCreate groupDto) {
        if (!(  userService.isAdmin(groupDto.getCreatorId()) ||
                userService.isAccountManager(groupDto.getCreatorId()) ||
                userService.isTeamLeader(groupDto.getCreatorId()))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin, account manager" +
                    " or team leader privileges.");
        }
        Group newGroup = groupService.addGroup(groupDto);
        return new ResponseEntity<>(newGroup, HttpStatus.CREATED);
    }


    //TODO talk to Adam about deleting chats
    /**
     * Endpoint deleting a group with given id
     * @param token     user account rights verification
     * @param groupId   id of deleting group
     * @return          response entity containing deleted group
     */
    @DeleteMapping(value = "/del/{groupId}")
    public ResponseEntity<Object> deleteGroupById(@RequestParam(value = "token") int token,
                                                 @PathVariable(value = "groupId") int groupId) {
        if (!(userService.isAdmin(token) || userService.isAccountManager(token) || userService.isTeamLeader(token))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin, account manager" +
                    " or team leader privileges.");
        }
        groupMembersService.deleteAllMember(groupId);
        return new ResponseEntity<>(groupService.deleteGroup(groupId), HttpStatus.NO_CONTENT);
    }


    /**
     * Endpoint getting all group members
     * @param token user account rights verification
     * @return      list of all members with their groups
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
     * @param token     user account rights verification
     * @param groupId   id of group
     * @return          list of group members
     */
    @GetMapping(value = "/get/groupMembers/byID/{groupId}")
    public ResponseEntity<List<GroupMembers>> getGroupMembersById(@RequestParam(value = "token") int token,
                                                          @PathVariable(value = "groupId") int groupId) {
        if (!userService.isPleb(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires at least pleb privileges.");
        }
        Group group = groupService.getGroup(groupId);
        return new ResponseEntity<>(groupMembersService.getGroupMembersById(group), HttpStatus.OK);
    }


    /**
     * Endpoint getting user's groups
     * @param token     user account rights verification
     * @return          list of groups
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
     * @param token             user account rights verification
     * @param groupMemberDto    new group member
     * @return                  response entity containing new group member
     */
    @PostMapping(value = "/add/memberToGroup")
    public ResponseEntity<Object> addMemberToGroup(@RequestParam(value = "token") int token,
                                                                  @RequestBody GroupUpdate groupMemberDto) {
        if (!(userService.isAdmin(token) || userService.isAccountManager(token) || userService.isTeamLeader(token))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin, account manager" +
                    " or team leader privileges.");
        }
        GroupMembers groupMember = groupMembersService.addMember(groupMemberDto);
        return new ResponseEntity<>(groupMember, HttpStatus.OK);
    }


    /**
     * Endpoint deleting a group member
     * @param token     user account rights verification
     * @param userId    id of deleting member
     * @param groupId   id of group with given user
     * @return          response entity containing deleted group member
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
