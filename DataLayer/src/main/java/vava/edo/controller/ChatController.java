package vava.edo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Chat;
import vava.edo.schema.chats.MessageCreate;
import vava.edo.service.*;

import java.util.List;
import java.util.Objects;

/**
 * Class that provides endpoints for operations with chat
 */
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
     * @param token     user account id
     * @param messageDto    message body containing all necessary information
     * @return      chat object
     */
    @PostMapping("/send")
    public ResponseEntity<Chat> sendMessage(@RequestParam(value = "token") Integer token,
                                            @RequestBody MessageCreate messageDto) {

        if (!Objects.equals(token, messageDto.getSenderId()) ||
                !groupMembersService.isUserInGroup(token, messageDto.getGroupId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be the owner of the selected account and part of the group.");
        }
        return new ResponseEntity<>(chatService.saveMessageToDatabase(messageDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Chat> deleteMessage(@RequestParam(value = "token") Integer token,
                                                     @RequestParam(value = "chatId") Integer chatId) {
        return new ResponseEntity<>(chatService.deleteMessage(token, chatId), HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint returning a list of last X messages in a specific chat room
     * @param token     user account id
     * @param groupId   chat room / group id
     * @param fromIndex optional parameter used to select specific message index range
     * @param size   optional parameter used to select specific message index range
     * @return          list of chat objects
     */
    @GetMapping("/get/{groupId}")
    public ResponseEntity<List<Chat>> getLastMessages(@RequestParam(value = "token") int token,
                                                      @RequestParam(value = "from", required = false) Integer fromIndex,
                                                      @RequestParam(value = "size", required = false) Integer size,
                                                      @PathVariable(value = "groupId") int groupId) {
        if (!groupMembersService.isUserInGroup(token, groupId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Combination of userId and groupId not found.");
        }

        if (fromIndex == null) {
            fromIndex = 0;
        }
        if (size == null) {
            size = 20;
        }

        return new ResponseEntity<>(chatService.getMessagesFromRange(groupId, fromIndex, size), HttpStatus.OK);
    }
}
