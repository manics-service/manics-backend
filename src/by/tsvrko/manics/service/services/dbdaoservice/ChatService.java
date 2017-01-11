package by.tsvrko.manics.service.services.dbdaoservice;

import by.tsvrko.manics.dao.database.HibernateFactory;
import by.tsvrko.manics.dao.database.interfaces.ChatDAO;
import by.tsvrko.manics.model.Chat;
import by.tsvrko.manics.model.User;
import by.tsvrko.manics.model.UserSession;

import java.util.ArrayList;

/**
 * Created by tsvrko on 1/8/2017.
 */
public class ChatService {

    private static ChatDAO chatDAOInstance = HibernateFactory.getInstance().getChatDAO();

    public boolean addChats(ArrayList<Chat> list, String token) {
        chatDAOInstance.addChats(list, token);
        return true;
    }

    public Chat getChatById(Chat chat){
        return chatDAOInstance.getChatById(chat);

    }

    public boolean deleteChats(User user){
        chatDAOInstance.deleteChats(user);
        return true;

    }

}
