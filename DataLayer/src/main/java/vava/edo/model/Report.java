package vava.edo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import vava.edo.model.enums.ReportStatus;
import vava.edo.schema.reports.ReportCreate;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rep_id", nullable = false)
    private Integer reportId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reporter_id", nullable = false)
    private User reporter;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "violator_id", nullable = false)
    private User violator;
    @Column(name = "rep_message", nullable = false)
    private String reportMessage;
    @Enumerated(EnumType.STRING)
    @Type(type = "vava.edo.model.enums.EnumTypePostgreSql")
    @Column(name = "status", nullable = false)
    private ReportStatus status = ReportStatus.PENDING;

    /**
     * Static casting method from ReportCreate object
     *
     * @param reportDto ReportCreate object that you want to cast
     * @return cast Report object
     */
    public static Report from(ReportCreate reportDto) {
        Report report = new Report();
        report.setReportId(0);
        report.setReportMessage(reportDto.getReportMessage());
        return report;
    }

    /**
     * Debugging method
     *
     * @return string with method variables
     */
    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", reporterId=" + reporter +
                ", violatorId=" + violator +
                ", reportMessage=" + reportMessage +
                ", reportStatus=" + status +
                '}';
    }
}
