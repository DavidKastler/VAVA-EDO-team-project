package vava.edo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Relationship;
import vava.edo.model.User;
import vava.edo.schema.UserInfo;
import vava.edo.service.RelationshipService;
import vava.edo.service.UserService;

import java.util.List;
import java.util.Objects;

/**
 * Class that provides endpoints for relationship operations
 */
@RestController
@RequestMapping("/relationship")
public class RelationshipController {

    private final RelationshipService relationshipService;
    private final UserService userService;

    @Autowired
    public RelationshipController(RelationshipService relationshipService, UserService userService) {
        this.relationshipService = relationshipService;
        this.userService = userService;
    }


    /**
     * Endpoint returning a list of all relationships
     *
     * @param token user account rights verification
     * @return list of all relationships
     */
    @GetMapping(value = "/all")
    public ResponseEntity<List<Relationship>> getAllRelationships(@RequestParam(value = "token") int token) {
        if (!userService.isAdmin(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin privileges.");
        }
        return new ResponseEntity<>(relationshipService.getAllRelationships(), HttpStatus.OK);
    }


    /**
     * Endpoint returning a list of friends
     *
     * @param token user account rights verification
     * @return list of friends {"username}
     */
    @GetMapping(value = "/friends")
    public ResponseEntity<List<UserInfo>> getAllFriends(@RequestParam(value = "token") int token) {
        if (!userService.isPleb(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "This action requires at least pleb privileges.");
        }
        User user = userService.getUser(token);
        return new ResponseEntity<>(relationshipService.getAllFriends(user), HttpStatus.OK);
    }


    /**
     * Endpoint returning a list of friend requests
     *
     * @param token user account rights verification
     * @return list of friend requests
     */
    @GetMapping(value = "/requests")
    public ResponseEntity<List<User>> getAllFriendRequests(@RequestParam(value = "token") int token) {
        if (!userService.isPleb(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "This action requires at least pleb privileges.");
        }
        User user = userService.getUser(token);
        return new ResponseEntity<>(relationshipService.getAllFriendRequests(user), HttpStatus.OK);
    }


    /**
     * Endpoint returning new friend request
     *
     * @param token  user account rights verification
     * @param userId user who receive request
     * @return friend request
     */
    @GetMapping(value = "/add/{userId}")
    public ResponseEntity<Object> createNewFriendRequest(@RequestParam(value = "token") int token,
                                                         @PathVariable(value = "userId") int userId) {
        if (!userService.isPleb(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "This action requires at least pleb privileges.");
        }
        if (!userService.isPleb(userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Wanted user must be at least pleb.");
        }
        User sender = userService.getUser(token);
        User receiver = userService.getUser(userId);

        // Cannot send friend request to user who blocked you
        if (relationshipService.checkFriendshipStatus(receiver, sender, "blocked")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cannot sent friend request.");
        }

        Relationship newRequest = relationshipService.getRelation(sender, receiver);

        // Create new request if there is non
        if (newRequest == null) {
            return new ResponseEntity<>
                    (relationshipService.addRelation(sender, receiver, "pending"), HttpStatus.CREATED);
        }
        // If blocked, need to send new request else is already friend or pending was sent
        if (Objects.equals(newRequest.getStatus(), "blocked")) {
            return new ResponseEntity<>
                    (relationshipService.updateRelationshipStatus(sender, receiver, "pending"),
                            HttpStatus.CREATED);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Requirement already satisfied.");
        }
    }


    /**
     * Endpoint returning new accepted friend request
     *
     * @param token  user account rights verification
     * @param userId user who accepted request
     * @return accepted friend request
     */
    @PutMapping(value = "accept/{userId}")
    public ResponseEntity<Relationship> acceptRequest(@RequestParam(value = "token") int token,
                                                      @PathVariable("userId") Integer userId) {
        if (!userService.isPleb(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "This action requires at least pleb privileges.");
        }
        User sender = userService.getUser(token);
        User receiver = userService.getUser(userId);
        Relationship requestAccepted = relationshipService.acceptRequest(sender, receiver);

        return new ResponseEntity<>(requestAccepted, HttpStatus.OK);
    }


    /**
     * Endpoint for rejecting friend request, rejected requests will be removed from database
     *
     * @param token  user account rights verification
     * @param userId user who reject request
     * @return rejected friend request
     */
    @DeleteMapping(value = "reject/{userId}")
    public ResponseEntity<Object> rejectRequest(@RequestParam(value = "token") int token,
                                                @PathVariable("userId") Integer userId) {
        if (!userService.isPleb(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "This action requires at least pleb privileges.");
        }
        User sender = userService.getUser(token);
        User receiver = userService.getUser(userId);
        relationshipService.rejectRequest(sender, receiver);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }


    /**
     * Endpoint returning blocking friends
     *
     * @param token  user account rights verification
     * @param userId user who will be blocked
     * @return blocked user
     */
    @PutMapping(value = "block/{userId}")
    public ResponseEntity<Relationship> blockUser(@RequestParam(value = "token") int token,
                                                  @PathVariable("userId") Integer userId) {
        if (!userService.isPleb(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "This action requires at least pleb privileges.");
        }
        User sender = userService.getUser(token);
        User receiver = userService.getUser(userId);
        Relationship userBlocked = relationshipService.blockUser(sender, receiver);

        return new ResponseEntity<>(userBlocked, HttpStatus.OK);
    }


}
