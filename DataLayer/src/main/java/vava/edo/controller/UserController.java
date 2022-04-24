package vava.edo.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.User;
import vava.edo.schema.users.UserLogin;
import vava.edo.schema.users.UserEdit;
import vava.edo.service.UserService;

import java.util.List;

/**
 * Class that provides endpoints for user operations
 */
@Log4j2
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint to log in user based on sending login credentials
     * @param userLogin user login credentials
     * @return          user entity from database
     */
    @PostMapping(value = "/login")
    public ResponseEntity<User> loginUser(@RequestBody UserLogin userLogin) {
        log.info("Logging user {}.", userLogin.getUsername());
        User user = userService.getUserByUserName(userLogin.getUsername());
        userService.checkPassword(user, userLogin.getPassword());
        log.info("User {} has successfully logged in.", user.getUsername());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Endpoint for registering new user, works same as login but here it saves to database
     * @param userDto   register credentials
     * @return          user entity from database
     */
    @PostMapping(value = "/register")
    public ResponseEntity<Object> registerNewUser(@RequestBody UserEdit userDto) {
        log.info("Register new user {}.", userDto.getUsername());
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.CREATED);
    }

    /**
     * Endpoint to update user credentials, its also for user itself
     * @param token         user id
     * @param userId        used id you want to check as admin required = false
     * @param updatedUser   
     * @return
     */
    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@RequestParam(value = "token") Integer token,
                                           @PathVariable(value = "userId", required = false) Integer userId,
                                           @RequestBody UserLogin updatedUser) {
        log.info("Updating user profile.");
        int wantedUserId = token;
        // if userId is used and is not equal to token and user is admin
        if (userId != null && !userId.equals(token)) {
            if (userService.isAdmin(token)) {
                wantedUserId = userId;
            } else {
                log.warn("Update rejected, insufficient rights to update user profile.");
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have rights to do this.");
            }
        }

        // no need to check if user exists, it is handled inside this method
        User user = userService.editUser(wantedUserId, updatedUser);
        log.info("User {} updated.", user.getUsername());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/edit/{userId}")
    public ResponseEntity<User> editUser(@RequestParam(value = "token") Integer token,
                                             @PathVariable(value = "userId") Integer userId,
                                             @RequestBody UserEdit editedUser) {
        log.info("Editing user profile.");
        if (!userService.isAdmin(token)) {
            log.warn("Edit rejected, insufficient rights to edit user profile.");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have rights to do this.");
        }

        // no need to check if user exists, it is handled inside this method
        User user = userService.editUser(userId, editedUser);
        log.info("User {} edited.", user.getUsername());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{u_id}")
    public ResponseEntity<Object> deleteUserById(@RequestParam(value = "token") Integer token,
                                                 @PathVariable(value = "u_id") Integer userId) {
        log.info("Deleting user.");
        if (!(userService.isAccountManager(token))) {
            log.warn("Deleted request rejected, insufficient rights to delete user.");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin privileges.");
        }
        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(value = "token") Integer token) {
        log.info("Getting all users from database.");
        if (!userService.isAdmin(token)) {
            log.warn("Request rejected, insufficient rights to get all users.");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin privileges.");
        }
        log.info("Found all users.");
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{userId}")
    public ResponseEntity<User> getUserById(@RequestParam(value = "token") Integer token,
                                            @PathVariable(value = "userId", required = false) Integer userId){
        log.info("Searching for user with id:{}.", userId);
        int wantedUserId = token;
        // if userId is used and is not equal to token and user is admin
        if (userId != null && !userId.equals(token) && userService.isAdmin(token)) {
            wantedUserId = userId;
        }
        else {
            log.warn("Permission to perform this action has been denied. Changing search id to {}.", token);
        }

        User user = userService.getUser(wantedUserId);
        log.info("Found user {} with id:{}.", user.getUsername(), user.getUId());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //TODO user search
}
