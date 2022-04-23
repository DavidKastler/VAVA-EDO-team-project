package vava.edo.schema.chats;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;


/**
 * Data transfer object for Chat class
 * It is used for sending groups you recently chatted with
 */
@Data
@AllArgsConstructor
public class RecentChatGroup {
    @NotNull
    private Long lastSentMessage;
    @NotNull
    private String groupName;

    private final static String SEPARATOR = ",";

    public RecentChatGroup(String queryOutput) {
        String[] columns = queryOutput.split(SEPARATOR);
        if (columns.length != 2) {
            return;
        }
        this.groupName = columns[0];
        this.lastSentMessage = Long.valueOf(columns[1]);
    }
}
