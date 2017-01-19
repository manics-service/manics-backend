package by.tsvrko.manics.dao.database.implementations;

import by.tsvrko.manics.dao.database.HibernateUtil;
import by.tsvrko.manics.dao.database.interfaces.MessageDAO;
import by.tsvrko.manics.model.Chat;
import by.tsvrko.manics.model.Message;
import by.tsvrko.manics.model.UserSession;
import by.tsvrko.manics.service.services.dbdaoservice.ChatService;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
            List<Message> dbMessageList = getMessages(localChat);

                for(int i=0;i<list.size();i++){
                    Message message = list.get(i);

                    if(dbMessageList.size()==0||dbMessageList.size()!=0&&message.getDate()>dbMessageList.get(dbMessageList.size()-1).getDate()){
                        message.setChat(localChat);
                        message.setBody(encodeText(message.getBody()));
                        session.save(message);
                    }
                    chat.setMessageList(list);
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

    //@Override
    public List<Message> getMessages(Chat chat) {
        Session session = null;
        List<Message> list = new ArrayList();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Message> criteria = builder.createQuery(Message.class);
            Root<Message> from = criteria.from(Message.class);

            criteria.select(from);
            criteria.where(builder.equal(from.get("chat"),chat.getId()));

            list = session.createQuery(criteria).getResultList();
            session.getTransaction().commit();

        } catch (HibernateException e) {
            log.debug("can't get user from database", e);
        }catch(NoResultException e){
            log.debug("user not found", e);

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }
    @Override
    public Message getMessage(long date) {
        Session session = null;
        Message message = new Message();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Message> criteria = builder.createQuery(Message.class);
            Root<Message> from = criteria.from(Message.class);

            criteria.select(from);
            criteria.where(builder.equal(from.get("date"),date));

            message = session.createQuery(criteria).getSingleResult();
            session.getTransaction().commit();

        } catch (HibernateException e) {
            log.debug("can't get user from database", e);
        }catch(NoResultException e){
            log.debug("user not found", e);

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return message;
    }
}




