package by.tsvrko.manics.service.services.dao.db;

import by.tsvrko.manics.dao.database.interfaces.ChatDAO;
import by.tsvrko.manics.model.hibernate.Chat;
import by.tsvrko.manics.model.hibernate.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tsvrko on 1/8/2017.
 */
@Service("chatService")
public class ChatService {

    @Autowired
    ChatDAO chatDAO ;

    public boolean addChat(Chat chat, String token) {
        chatDAO.addChat(chat, token);
        return true;
    }

    public Chat getChatById(Chat chat){
        return chatDAO.getChatById(chat);

    }

    public boolean deleteChat(Chat chat) {
        return chatDAO.deleteChat(chat);
    }

    public List<Chat> getChats(User user) {
        return chatDAO.getChats(user);
    }
}
