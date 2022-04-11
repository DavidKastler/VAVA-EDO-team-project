package vava.edo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vava.edo.model.Chat;
import vava.edo.model.Report;
import vava.edo.repository.ChatRepository;
import vava.edo.repository.TaskRepository;
import vava.edo.schema.MessageCreate;
import vava.edo.schema.ReportCreate;

import java.sql.Date;
import java.util.List;

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
     * @return list of chat objects
     */
    public List<Chat> getLastMessages(Integer userId, int fromIndex, int toIndex){
        return chatRepository.findAllByGroupIdOrderByTimeSent(userId, PageRequest.of(fromIndex, toIndex));
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
