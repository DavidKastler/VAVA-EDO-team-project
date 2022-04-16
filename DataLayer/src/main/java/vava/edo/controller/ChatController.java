package vava.edo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Chat;
import vava.edo.model.Group;
import vava.edo.model.GroupMembers;
import vava.edo.model.exeption.UserNotInGroupException;
import vava.edo.schema.MessageCreate;
import vava.edo.service.*;

import java.util.List;

/**
 * Class that provides endpoints for operations with chat
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;
    private final GroupService groupService;
    private final GroupMembersService groupMembersService;

    @Autowired
    public ChatController(ChatService chatService, UserService userService, GroupService groupService, GroupMembersService groupMembersService) {
        this.chatService = chatService;
        this.userService = userService;
        this.groupService = groupService;
        this.groupMembersService = groupMembersService;
    }

    /**
     * Endpoint returning a list of all users groups
     * @param token     user id of account
     * @return          list of group objects
     */
    @GetMapping("/all")
    public ResponseEntity<List<Group>> getAllChatsForUserId(@RequestParam(value = "token") int token) {
        return new ResponseEntity<>(groupMembersService.getMyGroups(token), HttpStatus.OK);
    }

    /**
     * Endpoint returning a list of last X messages in a specific chat room
     * @param token     user account id
     * @param groupId   chat room / group id
     * @param fromIndex optional parameter used to select specific message index range
     * @param toIndex   optional parameter used to select specific message index range
     * @return          list of chat objects
     */

    @GetMapping("/open")
    public ResponseEntity<List<Chat>> getLastMessages(@RequestParam(value = "token") int token,
                                                      @RequestParam(value = "chat_id") int groupId,
                                                      @RequestParam(value = "from", required = false) Integer fromIndex,
                                                      @RequestParam(value = "to", required = false) Integer toIndex) {

        if (groupMembersService.checkUserGroup(token, groupId)) {
            if (fromIndex == null) fromIndex = 0;
            if (toIndex == null) toIndex = 20;
            return new ResponseEntity<>(chatService.getLastMessages(groupId, fromIndex, toIndex), HttpStatus.OK);
        }
        else throw new UserNotInGroupException();

    }

    /**
     * Endpoint used to create a new message
     * @param token     user account id
     * @param messageDto    message body containing all necessary information
     * @return      chat object
     */
    @PostMapping("/send")
    public ResponseEntity<Chat> sendMessage(@RequestParam(value = "token") int token, @RequestBody MessageCreate messageDto) {
        if (chatService.verifyIfUserOwnsAccount(messageDto, token) && groupMembersService.checkUserGroup(token, messageDto.getGroupId()))
            return new ResponseEntity<>(chatService.sendMessage(messageDto), HttpStatus.OK);
        else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be the owner of the selected account and part of the group.");
    }

}
