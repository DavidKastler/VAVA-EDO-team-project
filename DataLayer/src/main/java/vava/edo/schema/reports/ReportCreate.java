package vava.edo.schema.reports;

import lombok.Data;
import lombok.NonNull;


/**
 * Data transfer object for Report class
 * It is used for creating a report
 */

@Data
public class ReportCreate {
    @NonNull
    private Integer reporterId;
    @NonNull
    private Integer violatorId;
    @NonNull
    private String reportMessage;

    public ReportCreate() {}

    @Override
    public String toString() {
        return "ReportCreate{" +
                ", reporterId=" + reporterId +
                ", violatorId" + violatorId +
                ", reportMessage=" + reportMessage +
                '}';
    }
}