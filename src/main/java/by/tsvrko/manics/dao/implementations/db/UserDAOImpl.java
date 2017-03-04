package main.java.by.tsvrko.manics.dao.implementations.db;

import main.java.by.tsvrko.manics.dao.interfaces.db.UserDAO;
import main.java.by.tsvrko.manics.model.dataimport.UserInfo;
import main.java.by.tsvrko.manics.model.hibernate.User;
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

/**
 * Created main.java.by tsvrko on 2/27/2017.
 */
@Repository
public class UserDAOImpl  implements UserDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }


    private Session openSession() {
        return sessionFactory.openSession();
    }
    private static Logger log = Logger.getLogger(SessionDAOImpl.class.getName());

    public User getBySession(String session) {
        return null;
    }



    @Override
    public User getByIdentifier(String userId) {
        Session session = null;
        User dbUser = new User();
        try {
            session = openSession();
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> from = criteria.from(User.class);

            criteria.select(from);
            criteria.where(builder.equal(from.get("identifier"),userId));

            dbUser = session.createQuery(criteria).getSingleResult();
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
        return dbUser;
    }


    public User getByDbId(int id) {
        Session session = null;
        User dbUser = new User();
        try {
            session = openSession();
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> from = criteria.from(User.class);

            criteria.select(from);
            criteria.where(builder.equal(from.get("id"),id));

            dbUser = session.createQuery(criteria).getSingleResult();
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
        return dbUser;
    }


    @Override
    public boolean addUser(UserInfo userInfo) {
        Session session = null;
        User user = new User();
        try {
            session = openSession();
            session.beginTransaction();

            user.setIdentifier(userInfo.getId());
            user.setFirstName(userInfo.getFirstName());
            user.setLastName(userInfo.getLastName());
            session.save(user);

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
        return true;
    }
    @Override
    public boolean persistUser(UserInfo userInfo) {
        Session session = null;
        User user = new User();
        try {
            session = openSession();
            session.beginTransaction();

            user.setId(9999999);
            user.setIdentifier(userInfo.getId());
            user.setFirstName(userInfo.getFirstName());
            user.setLastName(userInfo.getLastName());
            session.save(user);

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
        return true;
    }
}
