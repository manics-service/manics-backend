package by.tsvrko.manics.service.services.dbservice;

import by.tsvrko.manics.dao.database.HibernateFactory;
import by.tsvrko.manics.dao.database.interfaces.MessageDAO;
import by.tsvrko.manics.model.Chat;
import by.tsvrko.manics.model.Message;
import by.tsvrko.manics.model.UserInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsvrko on 1/7/2017.
 */

@Service("messageService")
public class MessageService {

    private static MessageDAO messageDAOInstance = HibernateFactory.getInstance().getMessageDAO();

    public static List<Message> getMessages(UserInfo userInfo, Chat chat) {
        return messageDAOInstance.getMessagesByUser(userInfo,chat);
    }

    public List<Message> getMessages(Chat chat) {
        return messageDAOInstance.getMessages(chat);
    }

    public boolean addMessages(ArrayList<Message> list, Chat chat) {

        messageDAOInstance.addMessages(list, chat);
        return true;


    }





}
