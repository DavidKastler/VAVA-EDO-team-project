package vava.edo.schema;

import com.sun.istack.NotNull;
import lombok.Data;

import java.sql.Date;


/**
 * Data transfer object for Chat class
 * It is used for creating a message
 */

@Data
public class MessageCreate{
    @NotNull
    private Integer groupId;

    private Integer senderId;
    private Date timeSent;
    private String message;

    @Override
    public String toString() {
        return "Message{" +
                ", groupId=" + groupId +
                ", senderId" + senderId +
                ", timeSent=" + timeSent +
                ", message=" + message +
                '}';
    }
}