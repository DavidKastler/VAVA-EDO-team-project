package vava.edo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vava.edo.model.Chat;
import vava.edo.repository.ChatRepository;
import vava.edo.repository.TaskRepository;

import java.util.List;

@Service
public class ChatService {

    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public List<Chat> getChatIds(Integer userId){
        return chatRepository.findAllBySenderId(userId);
    }
}
