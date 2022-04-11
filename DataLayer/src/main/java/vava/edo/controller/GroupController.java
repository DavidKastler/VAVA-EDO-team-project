package vava.edo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Group;
import vava.edo.model.Role;
import vava.edo.model.User;
import vava.edo.model.exeption.UserNotFoundException;
import vava.edo.schema.GroupCreate;
import vava.edo.schema.UserLogin;
import vava.edo.service.GroupService;
import vava.edo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;
    private final UserService userService;

    @Autowired
    public GroupController(GroupService groupService, UserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    /**
     * Endpoint returning a list of all groups
     * @param token     user account rights verification
     * @return          list of groups
     */
    @GetMapping(value = "/all")
    public ResponseEntity<List<Group>> getAllGroups(@RequestParam(value = "token") int token){
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
                                              @PathVariable(value = "groupId") Integer groupId){
//        int wantedGroupId = token;
//        // if userId is used and is not equal to token and user is admin
//        if (&& groupId != token && userService.isAdmin(token)) {
//            wantedGroupId = groupId;
//        }

        Group group = groupService.getGroup(groupId);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }


    @GetMapping(value = "/get/{groupName}")
    public ResponseEntity<Group> getGroupByName(@RequestParam(value = "token") int token,
                                              @PathVariable(value = "groupName", required = false) String groupId){
        String wantedGroupName = null;
        if (groupId != null && userService.isAdmin(token)) {
            wantedGroupName = groupId;
        }

        Group group = groupService.getGroupByName(wantedGroupName);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }


    @PostMapping(value = "/create")
    public ResponseEntity<Object> createNewGroup(@RequestBody GroupCreate userDto) {
        return new ResponseEntity<>(groupService.addGroup(userDto), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/del/{groupId}")
    public ResponseEntity<Object> deleteGroupById(@RequestParam(value = "token") int token,
                                                 @PathVariable(value = "groupId") int groupId) {
        if (!userService.isAdmin(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin privileges.");
        }

        return new ResponseEntity<>(groupService.deleteGroup(groupId), HttpStatus.NO_CONTENT);
    }
}
