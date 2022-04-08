package vava.edo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Chat;
import vava.edo.model.Task;
import vava.edo.service.ChatService;
import vava.edo.service.TaskService;
import vava.edo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;

    @Autowired
    public ChatController(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Chat>> getAllTasks(@RequestParam(value = "token") int token, @PathVariable(value = "id") Integer userId) {

        if (userId != null && (userService.isAdmin(token) || userId == token)) {
            return new ResponseEntity<>(chatService.getChatIds(userId), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be the owner of the selected account.");
    }
}
