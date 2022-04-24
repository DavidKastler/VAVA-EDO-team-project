package vava.edo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Chat;
import vava.edo.schema.chats.Message;
import vava.edo.schema.chats.RecentChatGroup;
import vava.edo.service.ChatService;
import vava.edo.service.GroupMembersService;

import java.util.List;
import java.util.Objects;

/**
 * Class that provides endpoints for operations with chat
 */
@Log4j2
@RestController
@RequestMapping("/chats")
public class ChatController {

    private final ChatService chatService;
    private final GroupMembersService groupMembersService;

    @Autowired
    public ChatController(ChatService chatService, GroupMembersService groupMembersService) {
        this.chatService = chatService;
        this.groupMembersService = groupMembersService;
    }

    /**
     * Endpoint used to create a new message
     *
     * @param token      user account id
     * @param messageDto message body containing all necessary information
     * @return chat object
     */
    @PostMapping("/send")
    public ResponseEntity<Chat> sendMessage(@RequestParam(value = "token") Integer token,
                                            @RequestBody Message messageDto) {
        log.info("Sending a message.");
        if (!Objects.equals(token, messageDto.getSenderId()) ||
                !groupMembersService.isUserInGroup(token, messageDto.getGroupId())) {
            log.warn("Message must be sent by sending user and it must be sent to different user within same group.");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be the owner of the selected account and part of the group.");
        }
        log.info("User with id:{} send message in group with id:{}",
                messageDto.getSenderId(), messageDto.getGroupId());
        return new ResponseEntity<>(chatService.saveMessageToDatabase(messageDto), HttpStatus.CREATED);
    }

    /**
     * Endpoint to delete message from database
     *
     * @param token  user id
     * @param chatId chat id you want to remove
     * @return deleted message
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Chat> deleteMessage(@RequestParam(value = "token") Integer token,
                                              @RequestParam(value = "chatId") Integer chatId) {
        log.info("Deleting a message with id:{}.", chatId);
        return new ResponseEntity<>(chatService.deleteMessage(token, chatId), HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint returning a list of last X messages in a specific chat room
     *
     * @param token     user account id
     * @param groupId   chat room / group id
     * @param fromIndex optional parameter used to select specific message index range
     * @param size      optional parameter used to select specific message index range
     * @return list of chat objects
     */
    @GetMapping("/get/{groupId}")
    public ResponseEntity<List<Chat>> getLastMessages(@RequestParam(value = "token") Integer token,
                                                      @RequestParam(value = "from", required = false) Integer fromIndex,
                                                      @RequestParam(value = "size", required = false) Integer size,
                                                      @PathVariable(value = "groupId") Integer groupId) {
        log.info("Getting last messages from user {} in group with id:{}", token, groupId);
        if (!groupMembersService.isUserInGroup(token, groupId)) {
            log.warn("There is no user with id: {} in group with id:{}", token, groupId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Combination of userId and groupId not found.");
        }
        if (fromIndex == null) {
            fromIndex = 0;
        }
        if (size == null) {
            size = 20;
        }
        log.info("Last messages from user {} in group with id:{}", token, groupId);
        return new ResponseEntity<>(chatService.getMessagesFromRange(groupId, fromIndex, size), HttpStatus.OK);
    }

    /**
     * Endpoint gets all recent chat groups for user
     *
     * @param token user id
     * @return list of recently chatted groups
     */
    @GetMapping("/get/recent")
    public ResponseEntity<List<RecentChatGroup>> getRecentChatGroups(@RequestParam(value = "token") int token) {
        log.info("Getting recent chat groups for user with id:{}", token);
        return new ResponseEntity<>(chatService.getLastChatGroups(token), HttpStatus.OK);
    }
}
