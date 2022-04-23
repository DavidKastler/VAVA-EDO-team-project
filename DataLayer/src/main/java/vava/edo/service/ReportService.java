package vava.edo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vava.edo.model.Report;
import vava.edo.model.enums.ReportStatus;
import vava.edo.model.exeption.TaskNotFoundException;
import vava.edo.repository.ReportRepository;
import vava.edo.schema.ReportCreate;

import java.util.List;

/**
 * Service that operates over reports database table
 */
@Service
public class ReportService {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    /**
     * Method used to create a new report
     * @param reportDto     report data transfer object
     * @return  created report object
     */
    public Report createReport(ReportCreate reportDto) {

        Report report = Report.from(reportDto);
        reportRepository.save(report);

        return report;
    }

    /**
     * Method that returns all reports
     * @return list of report objects
     */
    public List<Report> getAllReports(){
        return reportRepository.findAll();
    }

    /**
     * Method that returns report from reportId or throws if not found
     * @param reportId  id of searched report
     * @return  Report object
     */
    public Report getReport(Integer reportId)
    {
        return reportRepository.findById(reportId).orElseThrow(
                () -> new TaskNotFoundException(reportId));
    }

    /**
     * Method used to change report status to accepted
     * @param reportId  id of report we want to accept
     * @return      resulting report object
     */
    @Transactional
    public Report acceptReport(Integer reportId)
    {
        Report report = getReport(reportId);

        report.setStatus(ReportStatus.ACCEPTED);

        return report;
    }

    /**
     * Method used to change report status to rejected
     * @param reportId  id of report we want to reject
     * @return      resulting report object
     */
    @Transactional
    public Report rejectReport(Integer reportId)
    {
        Report report = getReport(reportId);

        report.setStatus(ReportStatus.REJECTED);
        return report;
    }

}
