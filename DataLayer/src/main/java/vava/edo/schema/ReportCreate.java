package vava.edo.schema;

import com.sun.istack.NotNull;
import lombok.Data;
import vava.edo.model.enums.ReportStatus;

import java.sql.Date;


/**
 * Data transfer object for Report class
 * It is used for creating a report
 */

@Data
public class ReportCreate{
    @NotNull
    private Integer reporterId;
    private Integer violatorId;
    private String reportMessage;

    @Override
    public String toString() {
        return "ReportCreate{" +
                ", reporterId=" + reporterId +
                ", violatorId=" + violatorId +
                ", reportMessage=" + reportMessage +
                '}';
    }
}