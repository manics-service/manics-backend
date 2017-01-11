package by.tsvrko.manics.dao.database.implementations;

import by.tsvrko.manics.dao.database.HibernateUtil;
import by.tsvrko.manics.dao.database.interfaces.MessageDAO;
import by.tsvrko.manics.model.Chat;
import by.tsvrko.manics.model.Message;
import by.tsvrko.manics.service.services.dbdaoservice.ChatService;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import static by.tsvrko.manics.dao.database.EncodingUtil.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tsvrko on 1/7/2017.
 */
public class MessageDAOImpl implements MessageDAO{


    private static Logger log = Logger.getLogger(UserDAOImpl.class.getName());
    private static ChatService chatService = new ChatService();

    public boolean addMessages(ArrayList<Message> list, Chat chat){

        Session session = null;
        Chat localChat = chatService.getChatById(chat);

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            if (localChat.getMessageList().size()!=0) {
                deleteMessages(localChat);
            }

            Iterator iterator = list.iterator();
            while (iterator.hasNext())
            {
                Message message = (Message)iterator.next();
                message.setChat(localChat);
                message.setBody(encodeMessages(message.getBody()));
                session.save(message);

            }
            session.getTransaction().commit();

        } catch (HibernateException e) {
            log.debug("can't add user to database", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return true;
    }


    public boolean deleteMessages(Chat chat){

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            List<Message> chatMessages= chat.getMessageList();

            Iterator iterator = chatMessages.iterator();
            while(iterator.hasNext()){
                Message message = (Message)iterator.next();
                session.delete(message);
            }
            session.getTransaction().commit();

        } catch (HibernateException e) {
            log.debug("can't add user to database", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return true;
    }


}
