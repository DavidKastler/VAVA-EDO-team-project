package vava.edo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Chat;
import vava.edo.model.Group;
import vava.edo.model.Task;
import vava.edo.model.exeption.UserNotInGroupException;
import vava.edo.schema.MessageCreate;
import vava.edo.service.ChatService;
import vava.edo.service.GroupService;
import vava.edo.service.TaskService;
import vava.edo.service.UserService;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;
    private final GroupService groupService;

    @Autowired
    public ChatController(ChatService chatService, UserService userService, GroupService groupService) {
        this.chatService = chatService;
        this.userService = userService;
        this.groupService = groupService;
    }

    /**
     * Endpoint returning a list of all users groups
     * @param token     user verification
     * @param userId    id of user whose groups we want to show
     * @return          list of group objects
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<Group>> getAllChats(@RequestParam(value = "token") int token, @PathVariable(value = "id") Integer userId) {

        if (userId != null && (userService.isAdmin(token) || userId == token)) {
            return new ResponseEntity<>(groupService.getAllUsersGroups(userId), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be the owner of the selected account.");
    }

    /**
     * Endpoint returning a list of last X messages in a specific chat room
     * @param token     user verification
     * @param userId    id of user whose messages we want to show
     * @param groupId   chat room / group id
     * @param fromIndex optional parameter used to select specific message index range
     * @param toIndex   optional parameter used to select specific message index range
     * @return          list of chat objects
     */
    @GetMapping("/{id}/{chat_id}")
    public ResponseEntity<List<Chat>> getLastMessages(@RequestParam(value = "token") int token, @PathVariable(value = "id") Integer userId,
                                                      @PathVariable(value = "chat_id") Integer groupId, @RequestParam(value = "from", required = false) Integer fromIndex,
                                                      @RequestParam(value = "to", required = false) Integer toIndex) {

        if (userId != null && (userService.isAdmin(token) || userId == token)) {
            if (groupService.checkUserGroup(userId, groupId)) {
                if (fromIndex == null) fromIndex = 0;
                if (toIndex == null) toIndex = 20;
                return new ResponseEntity<>(chatService.getLastMessages(groupId, fromIndex, toIndex), HttpStatus.OK);
            }
            else throw new UserNotInGroupException();
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be the owner of the selected account.");
    }

    /**
     * Endpoint used to create a new message
     * @param token     user verification
     * @param userId    sender ID
     * @param groupId   id of chat room / group
     * @param messageDto    message body containing all necessary information
     * @return      chat object
     */
    @PostMapping("/{id}/{chat_id}/send")
    public ResponseEntity<Chat> sendMessage(@RequestParam(value = "token") int token, @PathVariable(value = "id") Integer userId,
                                                      @PathVariable(value = "chat_id") Integer groupId, @RequestBody MessageCreate messageDto) {

        if (userId != null && (userService.isAdmin(token) || userId == token)) {
            if (groupService.checkUserGroup(userId, groupId))
                return new ResponseEntity<>(chatService.sendMessage(messageDto), HttpStatus.OK);
                //return new ResponseEntity<>(chatService.sendMessageSimplified(groupId, userId, ), HttpStatus.OK);
            else throw new UserNotInGroupException();
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be the owner of the selected account.");
    }

    /**
     * Simplified endpoint for sending messages
     * @param message   message text
     * @return      chat object
     */
    @PostMapping("/{id}/{chat_id}/sendSimplified")
    public ResponseEntity<Chat> sendMessageSimplified(@RequestParam(value = "token") int token, @PathVariable(value = "id") Integer userId,
                                            @PathVariable(value = "chat_id") Integer groupId, @RequestBody String message) {

        if (userId != null && (userService.isAdmin(token) || userId == token)) {
            if (groupService.checkUserGroup(userId, groupId))
                return new ResponseEntity<>(chatService.sendMessageSimplified(groupId, userId, Date.valueOf(LocalDate.now()), message), HttpStatus.OK);
            else throw new UserNotInGroupException();
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be the owner of the selected account.");
    }
}
