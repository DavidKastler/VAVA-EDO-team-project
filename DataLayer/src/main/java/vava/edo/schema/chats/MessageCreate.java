package vava.edo.schema.chats;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;

import java.sql.Date;


/**
 * Data transfer object for Chat class
 * It is used for creating a message
 */

@Data
public class MessageCreate{
    @NotNull
    private Integer groupId;
    private Long timeSent;
    private Integer senderId;
    private String message;

    @Override
    public String toString() {
        return "Message{" +
                " groupId=" + groupId +
                ", timeSent=" + timeSent +
                ", senderId" + senderId +
                ", message=" + message +
                '}';
    }
}