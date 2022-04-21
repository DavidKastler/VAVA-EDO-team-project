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
    private int chatId;
    private int reporterId;
    private int violatorId;
    private String reportMessage;
    private ReportStatus reportStatus;

    @Override
    public String toString() {
        return "ReportCreate{" +
                ", chatId=" + chatId +
                ", reporterId=" + reporterId +
                ", violatorId" + violatorId +
                ", reportMessage=" + reportMessage +
                ", reportStatus=" + reportStatus +
                '}';
    }
}