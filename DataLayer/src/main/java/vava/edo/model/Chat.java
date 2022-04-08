package vava.edo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
