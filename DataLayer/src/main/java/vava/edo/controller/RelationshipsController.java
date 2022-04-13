package vava.edo.controller;

import vava.edo.model.Relationships;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.service.RelationshipsService;
import vava.edo.service.UserService;

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


}
