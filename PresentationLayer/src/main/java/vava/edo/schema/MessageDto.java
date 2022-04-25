package vava.edo.schema;

import vava.edo.models.User;

import java.time.Instant;

public class MessageDto {
    private Integer senderId;
    private Integer groupId;
    private Long timeSent;
    private String message;

    public MessageDto(Integer senderId, Integer groupId, String message) {
        this.senderId = senderId;
        this.groupId = groupId;
        //TODO dat prec 1000
        this.timeSent = Instant.now().getEpochSecond()*1000;
        this.message = message;
    }
    @Override
    public String toString() {
        return "{" +
                "\"senderId\" : \"" + senderId + '\"' +
                ",\"groupId\" : \"" + groupId + '\"' +
                ",\"timeSent\" : \"" + timeSent + '\"' +
                ",\"message\" : \"" + message + '\"' +
                '}';
    }
}
