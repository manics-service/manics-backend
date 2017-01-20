package by.tsvrko.manics.service.services.dbservice;

import by.tsvrko.manics.dao.database.HibernateFactory;
import by.tsvrko.manics.dao.database.interfaces.ChatDAO;
import by.tsvrko.manics.model.Chat;
import by.tsvrko.manics.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tsvrko on 1/8/2017.
 */
@Service("chatService")
public class ChatService {

    private static ChatDAO chatDAOInstance = HibernateFactory.getInstance().getChatDAO();

    public boolean addChat(Chat chat, String token) {
        chatDAOInstance.addChat(chat, token);
        return true;
    }

    public Chat getChatById(Chat chat){
        return chatDAOInstance.getChatById(chat);

    }

    public static boolean deleteChat(Chat chat) {
        return chatDAOInstance.deleteChat(chat);
    }

    public static List<Chat> getChats(User user) {
        return chatDAOInstance.getChats(user);
    }
}
