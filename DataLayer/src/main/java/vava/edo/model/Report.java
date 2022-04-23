package vava.edo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vava.edo.model.enums.ReportStatus;
import vava.edo.schema.ReportCreate;

import javax.persistence.*;

/**
 * Class representing user in reports table
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rep_id", nullable = false)
    private Integer reportId;
    @Column(name = "reporter_id", nullable = false)
    private Integer reporterId;
    @Column(name = "violator_id", nullable = false)
    private Integer violatorId;
    @Column(name = "rep_message", nullable = false)
    private String reportMessage;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReportStatus status;


    /**
     * Static casting method from ReportCreate object
     * @param reportDto    ReportCreate object that you want to cast
     * @return          cast Report object
     */
    public static Report from(ReportCreate reportDto) {
        Report report = new Report();
        report.setReporterId(reportDto.getReporterId());
        report.setViolatorId(reportDto.getViolatorId());
        report.setReportMessage(reportDto.getReportMessage());
        report.setStatus(reportDto.getReportStatus());
        return report;
    }


    /**
     * Debugging method
     * @return  string with method variables
     */
    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", reporterId=" + reporterId +
                ", violatorId=" + violatorId +
                ", reportMessage=" + reportMessage +
                ", reportStatus=" + status +
                '}';
    }
}
