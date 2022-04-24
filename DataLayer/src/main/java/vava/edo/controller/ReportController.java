package vava.edo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Report;
import vava.edo.schema.reports.ReportCreate;
import vava.edo.service.ReportService;
import vava.edo.service.UserService;

import java.util.List;

/**
 * Class that provides endpoints for operations with reports
 */
@Log4j2
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
     * Endpoint used to create a new report
     * @param reportDto     report body
     * @return      created report object
     */
    @PostMapping("/create")
    public ResponseEntity<Report> createReport(@RequestBody ReportCreate reportDto) {
        log.info("Sending new report.");
        return new ResponseEntity<>(reportService.addReport(reportDto), HttpStatus.CREATED);
    }

    /**
     * Endpoint used to accept a report
     * @param token     verification of user privileges
     * @param reportId      id of report we want to accept
     * @return      resulting report object
     */
    @PutMapping("/accept/{rep_id}")
    public ResponseEntity<Report> acceptReport(@RequestParam(name = "token") Integer token,
                                               @PathVariable(name = "rep_id") Integer reportId) {
        log.info("Mark report as ACCEPTED.");
        if (!userService.isAccountManager(token)) {
            log.warn("Request rejected, insufficient rights to accept report.");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be admin.");
        }
        log.info("Report accepted.");
        return new ResponseEntity<>(reportService.acceptReport(reportId), HttpStatus.OK);
    }

    /**
     * Endpoint used to reject a report
     * @param token     verification of user privileges
     * @param reportId      id of report we want to reject
     * @return      resulting report object
     */
    @PutMapping("/reject/{rep_id}")
    public ResponseEntity<Report> rejectReport(@RequestParam(name = "token") Integer token,
                                               @PathVariable(name = "rep_id") Integer reportId) {
        log.info("Mark report as REJECTED.");
        if (userService.isAdmin(token)) {
            log.warn("Request rejected, insufficient rights to reject report.");
            return new ResponseEntity<>(reportService.rejectReport(reportId), HttpStatus.OK);
        }
        log.info("Report rejected.");
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be admin.");
    }

    /**
     * Endpoint returning a list of all reports
     * @param token     user account rights verification
     * @return          list of reports
     */
    @GetMapping("/pending")
    public ResponseEntity<List<Report>> getAllPendingReports(@RequestParam(value = "token") int token) {
        log.info("Show all pending reports.");
        if (!userService.isAccountManager(token)) {
            log.warn("Permission denied, insufficient rights to see all pending reports.");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be admin.");
        }
        log.info("Found all pending reports.");
        return new ResponseEntity<>(reportService.getAllPendingReports(), HttpStatus.OK);
    }

    /**
     * Endpoint returning a list of all reports
     * @param token     user account rights verification
     * @return          list of reports
     */
    @GetMapping("/all")
    public ResponseEntity<List<Report>> getAllReports(@RequestParam(value = "token") int token) {
        log.info("Show all reports.");
        if (!userService.isAdmin(token)) {
            log.warn("Permission denied, insufficient rights to see all reports.");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User needs to be admin.");
        }
        log.info("Found all reports.");
        return new ResponseEntity<>(reportService.getAllReports(), HttpStatus.OK);
    }
}
