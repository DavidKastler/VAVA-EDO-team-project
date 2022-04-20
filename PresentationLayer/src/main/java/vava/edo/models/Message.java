package vava.edo.models;

import java.sql.Date;

public class Message {
    private int chatId;
    private int groupId;
    private int senderId;
    private Date timeSent;
    private String message;

    @Override
    public String toString() {
        return "Message{" +
                "chatId=" + chatId +
                ", groupId=" + groupId +
                ", senderId=" + senderId +
                ", timeSent=" + timeSent +
                ", message='" + message + '\'' +
                '}';
    }
}
