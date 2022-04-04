package vava.edo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vava.edo.model.User;
import vava.edo.model.dto.UserDto;
import vava.edo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") int userId){
        User user = userService.getUser(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable(value = "id") int userId) {
        User user = userService.deleteUser(userId);

        return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
    }


    @PostMapping(value = "/create")
    public ResponseEntity<Object> createNewUser(@RequestBody UserDto userDto) {
        User user = userService.addUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
