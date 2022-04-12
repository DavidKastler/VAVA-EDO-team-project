package vava.edo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vava.edo.schema.MessageCreate;
import vava.edo.schema.TaskCreate;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ch_id", nullable = false)
    private int chatId;
    @Column(name = "group_id", nullable = false)
    private int groupId;
    @Column(name = "sender_id", nullable = false)
    private int senderId;
    @Column(name = "time_sent", nullable = false)
    private Date timeSent;
    @Column(name = "message", nullable = false)
    private String message;

    /**
     * Static casting method from MessageCreate object
     * @param messageDto    MessageCreate object that you want to cast
     * @return          cast Chat object
     */
    public static Chat from(MessageCreate messageDto) {
        Chat chat = new Chat();
        chat.setGroupId(messageDto.getGroupId());
        chat.setSenderId(messageDto.getSenderId());
        chat.setTimeSent(messageDto.getTimeSent());
        chat.setMessage(messageDto.getMessage());
        return chat;
    }
    /**
     * Debugging method
     * @return  string with method variables
     */
    @Override
    public String toString() {
        return "Chat{" +
                "chatId=" + chatId +
                ", groupId=" + groupId +
                ", senderId=" + senderId +
                ", timeSent=" + timeSent +
                ", message=" + message +
                '}';
    }
}
