package vava.edo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Report;
import vava.edo.schema.ReportCreate;
import vava.edo.service.ReportService;
import vava.edo.service.UserService;

import java.util.List;

/**
 * Class that provides endpoints for operations with reports
 */
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

    /**
     * Endpoint returning a list of all reports
     * @param token     user account rights verification
     * @return  list of reports
     */
    @GetMapping("/get/all")
    public ResponseEntity<List<Report>> getAllReports(@RequestParam(value = "token") int token) {

        if (userService.isAdmin(token)) {
            return new ResponseEntity<>(reportService.getAllReports(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be admin.");
    }

    /**
     * Endpoint used to create a new report
     * @param reportDto     report body
     * @return      created report object
     */
    @PostMapping("/create")
    public ResponseEntity<Report> createReport(@RequestBody ReportCreate reportDto) {
        return new ResponseEntity<>(reportService.createReport(reportDto), HttpStatus.OK);
    }

    /**
     * Endpoint used to accept a report
     * @param token     verification of user privileges
     * @param reportId      id of report we want to accept
     * @return      resulting report object
     */
    @PutMapping("/accept")
    public ResponseEntity<Report> acceptReport(@RequestParam(name = "token") Integer token, @RequestParam(name = "report_id") Integer reportId) {

        if (userService.isAdmin(token)) {
            return new ResponseEntity<>(reportService.acceptReport(reportId), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be admin.");
    }

    /**
     * Endpoint used to reject a report
     * @param token     verification of user privileges
     * @param reportId      id of report we want to reject
     * @return      resulting report object
     */
    @PutMapping("/reject")
    public ResponseEntity<Report> rejectReport(@RequestParam(name = "token") Integer token, @RequestParam(name = "report_id") Integer reportId) {
        if (userService.isAdmin(token)) {
            return new ResponseEntity<>(reportService.rejectReport(reportId), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be admin.");
    }

}