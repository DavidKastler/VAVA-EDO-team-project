package vava.edo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vava.edo.model.Chat;
import vava.edo.repository.ChatRepository;
import vava.edo.schema.MessageCreate;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 * Service that operates over chat database table
 */
@Service
public class ChatService {

    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    /**
     * Method returns last X messages between index fromIndex and toIndex
     * @param userId id of user whose messages we want to show
     * @param fromIndex index of first message we want to show
     * @param toIndex   index of last message we want to show
     * @return list of chat objects
     */
    public List<Chat> getLastMessages(Integer userId, int fromIndex, int toIndex){
        return chatRepository.findAllByGroupIdOrderByTimeSent(userId, PageRequest.of(fromIndex, toIndex));
    }

    /**
     * Method returns userId from given message data transfer object
     * @param messageDto    data transfer object of given message
     * @param userId        id of sender account
     * @return  int userId
     */
    public boolean verifyIfUserOwnsAccount(MessageCreate messageDto, Integer userId) {
        Integer messageSenderId = messageDto.getSenderId();

        return Objects.equals(userId, messageSenderId);
    }

    /**
     * Method used to create a message using data transfer object
     * @return created chat object
     */
    public Chat sendMessage(MessageCreate messageDto) {

        Chat chat = Chat.from(messageDto);
        chatRepository.save(chat);

        return chat;
    }

    /**
     * Method used to create a message using parameters
     * @param groupId   id of chat group
     * @param message   message text
     * @return  created chat object
     */
    public Chat sendMessageSimplified(Integer groupId, Integer senderId, Date timeSent, String message) {

        Chat chat = new Chat();
        chat.setMessage(message);
        chat.setSenderId(senderId);
        chat.setGroupId(groupId);
        chat.setTimeSent(timeSent);

        chatRepository.save(chat);

        return chat;
    }
}
