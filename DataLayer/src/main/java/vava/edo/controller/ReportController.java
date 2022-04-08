package vava.edo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Report;
import vava.edo.service.ReportService;
import vava.edo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;
    private final UserService userService;

    @Autowired
    public ReportController(ReportService reportService, UserService userService) {
        this.reportService = reportService;
        this.userService = userService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Report>> getAllTasks(@RequestParam(value = "token") int token, @PathVariable(value = "id") Integer userId) {

        if (userId != null && userService.isAdmin(token)) {
            return new ResponseEntity<>(reportService.getAllReports(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be the owner of the selected account.");
    }

    //NEDOKONCENE
    @PostMapping("/get/all")
    public ResponseEntity<List<Report>> createReport(@RequestParam(value = "token") int token, @PathVariable(value = "id") Integer userId) {

        if (userId != null && userService.isAdmin(token)) {
            return new ResponseEntity<>(reportService.getAllReports(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be the owner of the selected account.");
    }

}