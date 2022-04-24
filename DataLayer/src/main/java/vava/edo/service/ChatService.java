package vava.edo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Chat;
import vava.edo.model.User;
import vava.edo.repository.ChatRepository;
import vava.edo.schema.chats.Message;
import vava.edo.schema.chats.RecentChatGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Service that operates over chat database table
 */
@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserService userService;

    @Autowired
    public ChatService(ChatRepository chatRepository, UserService userService) {
        this.chatRepository = chatRepository;
        this.userService = userService;
    }

    public Chat getChat(Integer chatId) {
        return chatRepository.findById(chatId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat not found"));
    }

    /**
     * Method that saves newly sent message do database
     *
     * @param messageDto dto of message object we want to save
     * @return saved object from db
     */
    public Chat saveMessageToDatabase(Message messageDto) {
        Chat chat = Chat.from(messageDto);
        User sender = userService.getUser(messageDto.getSenderId());
        chat.setSender(sender);

        return chatRepository.save(chat);
    }

    public Chat deleteMessage(Integer userId, Integer chatId) {
        Chat chat = getChat(chatId);

        if (!Objects.equals(chat.getSender().getUId(), userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat is not yours");
        }
        chatRepository.delete(chat);
        return chat;
    }

    /**
     * Method returns last X messages between index fromIndex and size
     *
     * @param userId    id of user whose messages we want to show
     * @param fromIndex index of first message we want to show
     * @param size      index of last message we want to show
     * @return list of chat objects
     */
    public List<Chat> getMessagesFromRange(Integer userId, int fromIndex, int size) {
        return chatRepository.findAllByGroupIdOrderByTimeSentDesc(userId, PageRequest.of(fromIndex, size));
    }

    /**
     * Method returns userId from given message data transfer object
     *
     * @param messageDto data transfer object of given message
     * @param userId     id of sender account
     * @return true/ false
     */
    public boolean verifyIfUserOwnsAccount(Message messageDto, Integer userId) {
        return Objects.equals(userId, messageDto.getSenderId());
    }

    /**
     * Method that finds all groups user chatted with by its id
     *
     * @param userId user id
     * @return list of recently chatted groups
     */
    public List<RecentChatGroup> getLastChatGroups(Integer userId) {
        // verification if user is in database
        // TODO log error
        userService.getUser(userId);
        List<String> queryOutputList = chatRepository.getRecentChatGroupsForUser(userId);
        List<RecentChatGroup> recentChatGroups = new ArrayList<>();

        for (String queryOutput : queryOutputList) {
            recentChatGroups.add(new RecentChatGroup(queryOutput));
        }
        return recentChatGroups;
    }
}
