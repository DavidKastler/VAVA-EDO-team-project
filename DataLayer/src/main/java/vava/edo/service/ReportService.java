package vava.edo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vava.edo.model.Report;
import vava.edo.repository.ChatRepository;
import vava.edo.repository.ReportRepository;
import vava.edo.schema.ReportCreate;
import vava.edo.schema.TaskCreate;

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
}
