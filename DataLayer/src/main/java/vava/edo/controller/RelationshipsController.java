package vava.edo.controller;

import org.springframework.web.bind.annotation.*;
import vava.edo.model.Relationships;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.User;
import vava.edo.schema.GroupCreate;
import vava.edo.schema.RelationshipCreate;
import vava.edo.service.RelationshipsService;
import vava.edo.service.UserService;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/relationship")
public class RelationshipsController {

    private final RelationshipsService relationshipsService;
    private final UserService userService;

    @Autowired
    public RelationshipsController(RelationshipsService relationshipsService, UserService userService) {
        this.relationshipsService = relationshipsService;
        this.userService = userService;
    }


    /**
     * Endpoint returning a list of all relationships
     * @param token     user account rights verification
     * @return          list of all relationships
     */
    @GetMapping(value = "/all")
    public ResponseEntity<List<Relationships>> getAllRelationships(@RequestParam(value = "token") int token) {
        if (!userService.isAdmin(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin privileges.");
        }
        return new ResponseEntity<>(relationshipsService.getAllRelationships(), HttpStatus.OK);
    }


    /**
     * Endpoint returning a list of friends
     * @param token     user account rights verification
     * @return          list of friends {"username}
     */
    @GetMapping(value = "/friends")
    public ResponseEntity<List<java.util.Map.Entry<String , Integer>>> getAllFriends(@RequestParam(value = "token") int token) {
        if (!userService.isPleb(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "This action requires at least pleb privileges.");
        }
        User user = userService.getUser(token);
        return new ResponseEntity<>(relationshipsService.getAllFriends(user), HttpStatus.OK);
    }


    /**
     * Endpoint returning a list of friend requests
     * @param token     user account rights verification
     * @return          list of friend requests
     */
    @GetMapping(value = "/requests")
    public ResponseEntity<List<User>> getAllFriendRequests(@RequestParam(value = "token") int token) {
        if (!userService.isPleb(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "This action requires at least pleb privileges.");
        }
        User user = userService.getUser(token);
        return new ResponseEntity<>(relationshipsService.getAllFriendRequests(user), HttpStatus.OK);
    }


    /**
     * Endpoint returning new friend request
     * @param token     user account rights verification
     * @param userId    user who receive request
     * @return          friend request
     */
    @GetMapping(value = "/add/{userId}")
    public ResponseEntity<Object> createNewFriendRequest(@RequestParam(value = "token") int token,
                                                         @PathVariable(value = "userId") int userId) {
        if(!userService.isPleb(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "This action requires at least pleb privileges.");
        }
        if(!userService.isPleb(userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Wanted user must be at least pleb.");
        }
        User sender = userService.getUser(token);
        User receiver = userService.getUser(userId);
        Relationships newRequest = relationshipsService.addRequest(sender, receiver);

        return new ResponseEntity<>(newRequest, HttpStatus.CREATED);
    }


    /**
     * Endpoint returning new accepted friend request
     * @param token     user account rights verification
     * @param userId    user who accepted request
     * @return          accepted friend request
     */
    @PutMapping(value = "accept/{userId}")
    public ResponseEntity<Relationships> acceptRequest(@RequestParam(value = "token") int token,
                                                       @PathVariable("userId") Integer userId) {
        if(!userService.isPleb(token)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "This action requires at least pleb privileges.");
        }
        User sender = userService.getUser(token);
        User receiver = userService.getUser(userId);
        Relationships requestAccepted = relationshipsService.acceptRequest(sender, receiver);

        return new ResponseEntity<>(requestAccepted, HttpStatus.OK);
    }


    /**
     * Endpoint returning rejected friend request
     * @param token     user account rights verification
     * @param userId    user who reject request
     * @return          rejected friend request
     */
    @DeleteMapping(value = "reject/{userId}")
    public ResponseEntity<Object> rejectRequest(@RequestParam(value = "token") int token,
                                                       @PathVariable("userId") Integer userId) {
        if(!userService.isPleb(token)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "This action requires at least pleb privileges.");
        }
        User sender = userService.getUser(token);
        User receiver = userService.getUser(userId);
        Relationships requestRejected = relationshipsService.rejectRequest(sender, receiver);

        return new ResponseEntity<>(requestRejected, HttpStatus.OK);
    }
}
