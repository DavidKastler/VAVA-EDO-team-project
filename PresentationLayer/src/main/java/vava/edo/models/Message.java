package vava.edo.models;

import java.sql.Date;

public class Message {
    private int chatId;
    private int groupId;
    private int senderId;
    private Date timeSent;
    private String message;

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public Date getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(Date timeSent) {
        this.timeSent = timeSent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


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
