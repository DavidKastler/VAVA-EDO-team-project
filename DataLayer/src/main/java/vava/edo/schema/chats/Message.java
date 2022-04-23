package vava.edo.schema.chats;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NonNull;


/**
 * Data transfer object for Chat class
 * It is used for creating and receiving a message
 */

@Data
public class Message {
    @NotNull
    private Integer groupId;
    @NonNull
    private Integer senderId;
    @NonNull
    private String message;

    @Override
    public String toString() {
        return "Message{" +
                ", groupId=" + groupId +
                ", senderId" + senderId +
                ", message=" + message +
                '}';
    }
}