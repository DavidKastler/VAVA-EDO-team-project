package vava.edo.models;

import com.google.gson.Gson;
import com.mashape.unirest.http.JsonNode;

import java.sql.Date;

public class Message {
    private Long timeSent;
    private String message;
    private User sender;

    public long getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(long timeSent) {
        this.timeSent = timeSent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }


    @Override
    public String toString() {
        return "Message{" +
                "timeSent=" + timeSent +
                ", message=" + message +
                ", sender=" + sender +
                '}';
    }
}
