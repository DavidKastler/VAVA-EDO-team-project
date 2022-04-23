package vava.edo.schema.reports;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NonNull;
import vava.edo.model.enums.ReportStatus;

import java.sql.Date;


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

    @Override
    public String toString() {
        return "ReportCreate{" +
                ", reporterId=" + reporterId +
                ", violatorId" + violatorId +
                ", reportMessage=" + reportMessage +
                '}';
    }
}