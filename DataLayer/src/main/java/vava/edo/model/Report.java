package vava.edo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vava.edo.schema.ReportCreate;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rep_id", nullable = false)
    private int reportId;
    @Column(name = "ch_id", nullable = false)
    private int chatId;
    @Column(name = "reporter_id", nullable = false)
    private int reporterId;
    @Column(name = "violator_id", nullable = false)
    private int violatorId;
    @Column(name = "rep_message", nullable = false)
    private String reportMessage;
    @Column(name = "report_status", nullable = false)
    private String reportStatus;

    /**
     * Static casting method from ReportCreate object
     * @param reportDto    ReportCreate object that you want to cast
     * @return          cast Report object
     */
    public static Report from(ReportCreate reportDto) {
        Report report = new Report();
        report.setChatId(reportDto.getChatId());
        report.setReporterId(reportDto.getReporterId());
        report.setViolatorId(reportDto.getViolatorId());
        report.setReportMessage(reportDto.getReportMessage());
        report.setReportStatus(reportDto.getReportStatus());
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
                ", chatId=" + chatId +
                ", reporterId=" + reporterId +
                ", violatorId=" + violatorId +
                ", reportMessage=" + reportMessage +
                ", reportStatus=" + reportStatus +
                '}';
    }
}
