package by.tsvrko.manics.dao.database.implementations;

import by.tsvrko.manics.dao.database.HibernateUtil;
import by.tsvrko.manics.dao.database.interfaces.ChatDAO;
import by.tsvrko.manics.model.Chat;
import by.tsvrko.manics.model.User;
import by.tsvrko.manics.service.services.dbservice.SessionService;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import static by.tsvrko.manics.dao.database.EncodingUtil.*;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tsvrko on 1/8/2017.
 */
public class ChatDAOImpl implements ChatDAO {

    private static Logger log = Logger.getLogger(UserDAOImpl.class.getName());

    //    @Resource(name="sessionService")
//    private SessionService sessionService;
    private static SessionService sessionService = new SessionService();
    @Override
    public boolean addChat(Chat chat, String token){

        Session session = null;
        User user = sessionService.getUserSessionByToken(token).getUser();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List<Chat> userChats = getChats(user);
            Iterator iterator = userChats.iterator();
            boolean marker=false;
            while (iterator.hasNext())
            {
                Chat dbchat = (Chat)iterator.next();
                if(chat.getChat_id()==dbchat.getChat_id()){
                    marker=true;
                    break;
                }
            }
            if (!marker){
                chat.setUser(user);
                chat.setTitle(encodeText(chat.getTitle()));
                session.save(chat);}
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
    public boolean deleteChat(Chat chat){

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(chat);
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
    public Chat getChatById(Chat chat) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Chat> criteria = builder.createQuery(Chat.class);
            Root<Chat> from = criteria.from(Chat.class);

            criteria.select(from);
            criteria.where(builder.equal(from.get("chat_id"),chat.getChat_id()));

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

    @Override
    public List<Chat> getChats(User user) {
        Session session = null;
        List<Chat> list = new ArrayList<>();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Chat> criteria = builder.createQuery(Chat.class);
            Root<Chat> from = criteria.from(Chat.class);

            criteria.select(from);
            criteria.where(builder.equal(from.get("user"), user.getId()));

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
