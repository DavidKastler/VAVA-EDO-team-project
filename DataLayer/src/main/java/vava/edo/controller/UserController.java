package vava.edo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.User;
import vava.edo.schema.UserLogin;
import vava.edo.schema.UserRegister;
import vava.edo.service.UserService;

import java.util.List;

/**
 * Class that provides endpoints for user operations
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/login")
    public ResponseEntity<User> loginUser(@RequestParam(value = "token") int token,
                                          @RequestBody UserLogin userLogin) {
        if (!userService.isPleb(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires \n" +
                    "at least pleb privileges.");
        }

        User user = userService.getUserByUserName(userLogin.getUsername());
        userService.checkPassword(user, userLogin.getPassword());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(value = "token") int token) {
        if (!userService.isAdmin(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin privileges.");
        }
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }


    @GetMapping(value = "/get/{userId}")
    public ResponseEntity<User> getUserById(@RequestParam(value = "token") int token,
                                            @PathVariable(value = "userId", required = false) Integer userId){
        int wantedUserId = token;
        // if userId is used and is not equal to token and user is admin
        if (userId != null && userId != token && userService.isAdmin(token)) {
            wantedUserId = userId;
        }

        User user = userService.getUser(wantedUserId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PutMapping("/change/{userId}")
    public ResponseEntity<User> editUser(@RequestParam(value = "token") int token,
                                         @PathVariable("userId") Integer userId,
                                         @RequestBody UserLogin updatedUser) {
        int wantedUserId = token;
        // if userId is used and is not equal to token and user is admin
        if (userId != null && userId != token) {
            if (userService.isAdmin(token)) {
                wantedUserId = userId;
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have rights to do this.");
            }
        }

        if (!userService.isPleb(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires \n" +
                    "at least pleb privileges.");
        }
        // no need to chceck if user exists, it is handled inside this method
        User user = userService.editUser(wantedUserId, updatedUser);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/del/{id}")
    public ResponseEntity<Object> deleteUserById(@RequestParam(value = "token") int token,
                                                 @PathVariable(value = "id") int userId) {
        if (!(userService.isAdmin(token) || userService.isAccountManager(token))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin privileges.");
        }

        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.NO_CONTENT);
    }


    @PostMapping(value = "/register")
    public ResponseEntity<Object> registerNewUser(@RequestBody UserRegister userDto) {
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.CREATED);
    }
}
