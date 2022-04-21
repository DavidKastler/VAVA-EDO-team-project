package vava.edo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vava.edo.model.Group;
import vava.edo.model.GroupMembers;
import vava.edo.service.GroupMembersService;

import java.util.List;

@RestController
@RequestMapping("/groupMembers")
public class GroupMemberController {
    GroupMembersService groupMembersService;

    @Autowired
    public GroupMemberController(GroupMembersService groupMembersService) {
        this.groupMembersService = groupMembersService;
    }

    @GetMapping("groups")
    public ResponseEntity<List<Group>> getGroupsForUser(@RequestParam(value = "token") Integer token) {
        return new ResponseEntity<>(groupMembersService.getMyGroups(token), HttpStatus.OK);
    }

    @GetMapping("members/{group_id}")
    public ResponseEntity<List<GroupMembers>> getMembersOfGroup(@RequestParam(value = "token") Integer token,
                                                                @PathVariable(value = "group_id") Integer groupId) {
        return new ResponseEntity<>(groupMembersService.getGroupMembersById(groupId), HttpStatus.OK);
    }
}
