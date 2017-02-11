package by.tsvrko.manics.service.implementations.db;

import by.tsvrko.manics.dao.interfaces.db.ChatDAO;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.hibernate.Chat;
import by.tsvrko.manics.model.hibernate.User;
import by.tsvrko.manics.service.interfaces.db.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tsvrko on 1/8/2017.
 */
@Service
@Transactional
public class ChatServiceImpl implements ChatService{

    private ChatDAO chatDAO ;

    @Autowired
    public ChatServiceImpl(ChatDAO chatDAO) {
        this.chatDAO = chatDAO;
    }

    @Override
    public boolean addChat(ChatInfo chat, String token) {
        chatDAO.addChat(chat, token);
        return true;
    }

    @Override
    public Chat getChatById(long chatId){
        return chatDAO.getChatById(chatId);

    }
    @Override
    public boolean deleteChat(Chat chat) {
        return chatDAO.deleteChat(chat);
    }

    @Override
    public List<Chat> getChats(User user) {
        return chatDAO.getChats(user);
    }
}
