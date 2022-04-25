package vava.edo.schema.chats;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * Data transfer object for Chat class
 * It is used for sending groups you recently chatted with
 */
@Data
@AllArgsConstructor
public class RecentChatGroup {
    @NotNull
    private Integer groupId;
    @NotNull
    private String groupName;
    @NotNull
    private Long lastSentMessage;

    private final static String SEPARATOR = ",";

    public RecentChatGroup(String queryOutput) {
        String[] columns = queryOutput.split(SEPARATOR);
        if (columns.length != 3) {
            // TODO log error
            return;
        }
        this.groupId = Integer.valueOf(columns[0]);
        this.groupName = columns[1];
        this.lastSentMessage = Long.valueOf(columns[2]);
    }
}
