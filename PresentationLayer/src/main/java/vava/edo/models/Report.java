package vava.edo.models;

import lombok.Getter;

@Getter
public class Report {
    private ReporterViolator reporter;
    private ReporterViolator violator;
    private Integer reportId;
    private String reportMessage;
    private String status;

    private String violatorName;

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", reporter=" + reporter +
                ", violator=" + violator +
                ", reportMessage=" + reportMessage +
                ", status=" + status +
                '}';
    }

    public void setViolatorName() {
        this.violatorName = this.violator.getUsername();}
}
