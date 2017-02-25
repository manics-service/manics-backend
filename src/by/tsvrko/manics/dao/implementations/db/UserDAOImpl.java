package by.tsvrko.manics.dao.implementations.db;

import by.tsvrko.manics.dao.interfaces.db.UserDAO;
import by.tsvrko.manics.model.dataimport.UserInfo;
import by.tsvrko.manics.model.hibernate.Chat;
import by.tsvrko.manics.model.hibernate.User;
import by.tsvrko.manics.model.hibernate.UserSession;
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
import java.util.Iterator;
import java.util.List;

import static by.tsvrko.manics.dao.EncodingUtil.encodeText;

/**
 * Created main.by tsvrko on 11/22/2016.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    private static Logger log = Logger.getLogger(UserDAOImpl.class.getName());

    private SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session openSession() {
        return sessionFactory.openSession();
    }

    @Override
    public User getByLogin(String login)  {

        Session session = null;
        User user = new User();

        try {
            session = openSession();
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> from = criteria.from(User.class);

            criteria.select(from);
            criteria.where(builder.equal(from.get("login"),login));

            user = session.createQuery(criteria).getSingleResult();
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
        return user;
    }

    @Override
    public boolean addUser(User user) {
        Session session = null;

        try {
            session = openSession();
            session.beginTransaction();
            session.saveOrUpdate(user);
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
