package vava.edo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vava.edo.model.Report;
import vava.edo.model.enums.ReportStatus;
import vava.edo.model.exeption.TaskNotFoundException;
import vava.edo.repository.ReportRepository;
import vava.edo.schema.reports.ReportCreate;

import java.util.List;

/**
 * Service that operates over reports database table
 */
@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserService userService;

    @Autowired
    public ReportService(ReportRepository reportRepository, UserService userService) {
        this.reportRepository = reportRepository;
        this.userService = userService;
    }

    /**
     * Method that returns report from reportId or throws if not found
     * @param reportId  id of searched report
     * @return  Report object
     */
    public Report getReport(Integer reportId) {
        return reportRepository.findById(reportId).orElseThrow(() -> new TaskNotFoundException(reportId));
    }

    /**
     * Method that returns all reports
     * @return list of report objects
     */
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    /**
     * Method that returns all reports
     * @return list of report objects
     */
    public List<Report> getAllPendingReports() {
        return reportRepository.findAllByStatusIsPending();
    }

    /**
     * Method used to create a new report
     * @param reportDto     report data transfer object
     * @return  created report object
     */
    public Report addReport(ReportCreate reportDto) {
        Report report = Report.from(reportDto);
        report.setReporter(userService.getUser(reportDto.getReporterId()));
        report.setViolator(userService.getUser(reportDto.getViolatorId()));
        return reportRepository.save(report);
    }

    /**
     * Method used to change report status to accepted
     * @param reportId  id of report we want to accept
     * @return      resulting report object
     */
    @Transactional
    public Report acceptReport(Integer reportId) {
        Report report = getReport(reportId);
        userService.deleteUser(report.getViolator().getUId());
        report.setStatus(ReportStatus.accepted);
        return report;
    }

    /**
     * Method used to change report status to rejected
     * @param reportId  id of report we want to reject
     * @return      resulting report object
     */
    @Transactional
    public Report rejectReport(Integer reportId) {
        Report report = getReport(reportId);
        report.setStatus(ReportStatus.rejected);
        return report;
    }
}
