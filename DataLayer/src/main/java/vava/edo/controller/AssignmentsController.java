package vava.edo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Assignments;
import vava.edo.service.AssignmentsService;
import vava.edo.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/assignments")
public class AssignmentsController {

    private final AssignmentsService assignmentsService;
    private final UserService userService;

    @Autowired
    public AssignmentsController(AssignmentsService assignmentsService, UserService userService) {
        this.assignmentsService = assignmentsService;
        this.userService = userService;
    }


    /**
     * Endpoint returning a list of assignments
     * @param token     user account rights verification
     * @return          list of assignments
     */
    @GetMapping(value = "/all")
    public ResponseEntity<List<Assignments>> getAllAssignments(@RequestParam(value = "token") int token) {
        if (!userService.isAdmin(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin privileges.");
        }
        return new ResponseEntity<>(assignmentsService.getAllAssignments(), HttpStatus.OK);
    }


    /**
     * Endpoint returning an assignment
     * @param token     user account rights verification
     * @param assId     id of wanted assignment
     * @return          assignment
     */
    @GetMapping(value = "/get/{assId}")
    public ResponseEntity<Optional<Assignments>> getAssignmentById(@RequestParam(value = "token") int token,
                                                                   @PathVariable(value = "assId") int assId){
        if (!userService.isAdmin(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This action requires admin privileges.");
        }
        return new ResponseEntity<>(assignmentsService.getAssignmentsById(assId), HttpStatus.OK);
    }


    //TODO create
}
