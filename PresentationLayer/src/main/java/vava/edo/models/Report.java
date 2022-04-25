package vava.edo.models;

public class Report {
    private ReporterViolator reporter;
    private ReporterViolator violator;
    private Integer reportId;
    private String reportMessage;
    private String status;

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
}
