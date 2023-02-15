package vava.edo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vava.edo.schema.chats.Message;

import javax.persistence.*;
import java.time.Instant;

/**
 * Class representing one chat in chat table
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ch_id", nullable = false)
    private Integer chatId;
    @Column(name = "group_id", nullable = false)
    private Integer groupId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;
    @Column(name = "time_sent", nullable = false)
    private Long timeSent;
    @Column(name = "message", nullable = false)
    private String message;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id", nullable = false, insertable = false, updatable = false)
    private Group group;


    /**
     * Static casting method from MessageCreate object
     *
     * @param messageDto MessageCreate object that you want to cast
     * @return cast Chat object
     */
    public static Chat from(Message messageDto) {
        Chat chat = new Chat();
        chat.setChatId(0);
        chat.setGroupId(messageDto.getGroupId());
        chat.setMessage(messageDto.getMessage());
        chat.setTimeSent(Instant.now().getEpochSecond());

        return chat;
    }


    /**
     * Debugging method
     *
     * @return string with method variables
     */
    @Override
    public String toString() {
        return "Chat{" +
                "chatId=" + chatId +
                ", groupId=" + groupId +
                ", senderId=" + sender +
                ", timeSent=" + timeSent +
                ", message=" + message +
                '}';
    }
}
