package by.tsvrko.manics.dao.implementations.db;

import by.tsvrko.manics.dao.interfaces.db.ChatDAO;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.hibernate.Chat;
import by.tsvrko.manics.model.hibernate.User;
import by.tsvrko.manics.model.hibernate.UserSession;
import by.tsvrko.manics.service.interfaces.db.SessionService;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static by.tsvrko.manics.dao.EncodingUtil.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created main.by tsvrko on 1/8/2017.
 */

@Repository
public class ChatDAOImpl implements ChatDAO {

    private SessionFactory sessionFactory;
    private SessionService sessionService;



    @Autowired
    public ChatDAOImpl(SessionFactory sessionFactory, SessionService sessionService) {
        this.sessionFactory = sessionFactory;
        this.sessionService = sessionService;
    }

    private Session openSession() {
        return sessionFactory.openSession();
    }
    private static Logger log = Logger.getLogger(ChatDAOImpl.class.getName());

    @Override

    public boolean addChat(ChatInfo chatInfo, String userSession){

        Session session = null;
        try {
            session = openSession();
            session.beginTransaction();
            List<Chat> userChats = getBySession(userSession);
            boolean marker = false;

            if (userChats.size()!=0) {
                Iterator iterator = userChats.iterator();
                while (iterator.hasNext()) {
                    Chat chat = (Chat) iterator.next();
                    if (chatInfo.getChatId() == chat.getChatId()) {
                        marker = true;
                        break;
                    }
                }
            }
            if (!marker){

                User user = sessionService.getByValue(userSession).getUser();
                Chat chat = new Chat();
                chat.setUser(user);
                chat.setChatId(chatInfo.getChatId());
                chat.setTitle(encodeText(chatInfo.getTitle()));
                session.save(chat);

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
    public boolean deleteChat(long chatId){
        Session session = null;
        try {

            session = openSession();
            session.beginTransaction();
            getByChatId(chatId);
            Chat chat = getByChatId(chatId);
            session.merge(chat);
            session.delete( chat);
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
    public Chat getByChatId(long chatId) {
        Session session = null;
        Chat chat=null;
        try {
            session = openSession();
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Chat> criteria = builder.createQuery(Chat.class);
            Root<Chat> from = criteria.from(Chat.class);

            criteria.select(from);
            criteria.where(builder.equal(from.get("chatId"),chatId));

            chat = session.createQuery(criteria).getSingleResult();
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
        return chat;
    }

    public List<Chat> getBySession(String userSession) {
        User dbUser = sessionService.getByValue(userSession).getUser();
        Session session = null;
        List<Chat> list = new ArrayList<>();
        try {
            session = openSession();
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Chat> criteria = builder.createQuery(Chat.class);
            Root<Chat> from = criteria.from(Chat.class);

            criteria.select(from);
            criteria.where(builder.equal(from.get("user"), dbUser.getId()));

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

}
