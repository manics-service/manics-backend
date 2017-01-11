package by.tsvrko.manics.service.services.dbdaoservice;

import by.tsvrko.manics.dao.database.HibernateFactory;
import by.tsvrko.manics.dao.database.interfaces.MessageDAO;
import by.tsvrko.manics.model.Chat;
import by.tsvrko.manics.model.Message;
import java.util.ArrayList;

/**
 * Created by tsvrko on 1/7/2017.
 */
public class MessageService {

    private static MessageDAO messageDAOInstance = HibernateFactory.getInstance().getMessageDAO();

    public boolean addMessages(ArrayList<Message> list, Chat chat) {

        messageDAOInstance.addMessages(list, chat);
        return true;

    }





}
