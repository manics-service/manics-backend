package by.tsvrko.manics.dao.database.implementations;

import by.tsvrko.manics.dao.database.interfaces.MessageDAO;
import by.tsvrko.manics.model.hibernate.Chat;
import by.tsvrko.manics.model.hibernate.Message;
import by.tsvrko.manics.model.UserInfo;
import by.tsvrko.manics.service.services.dao.db.ChatService;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import static by.tsvrko.manics.dao.database.EncodingUtil.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsvrko on 1/7/2017.
 */


@Repository("messageDAO")
public class MessageDAOImpl implements MessageDAO{

    private static Logger log = Logger.getLogger(MessageDAOImpl.class.getName());

    @Autowired
    private SessionFactory sessionFactory;
    private Session openSession() {
        return sessionFactory.openSession();
    }
    @Autowired
    private ChatService chatService;

    public boolean addMessages(ArrayList<Message> list, Chat chat){

        Session session = null;
        Chat localChat = chatService.getChatById(chat);

        try {
            session = openSession();
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


    @Override
    public List<Message> getMessages(Chat chat) {
        Session session = null;
        List<Message> list = new ArrayList();
        try {
            session = openSession();
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
    public List<Message> getMessagesByUser(UserInfo userInfo, Chat chat) {

        Chat dbChat= chatService.getChatById(chat);
        Session session = null;
        List <Message> messageList = new ArrayList<>();
        try {
            session = openSession();
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Message> criteria = builder.createQuery(Message.class);
            Root<Message> from = criteria.from(Message.class);


            criteria.select(from);
            criteria.where(builder.equal(from.get("user_id"), userInfo.getId()),builder.equal(from.get("chat"),dbChat.getId()));

            messageList = session.createQuery(criteria).getResultList();
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
        return messageList;
    }


}




