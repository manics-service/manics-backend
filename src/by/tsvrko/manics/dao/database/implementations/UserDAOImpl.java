package by.tsvrko.manics.dao.database.implementations;

import by.tsvrko.manics.dao.database.interfaces.UserDAO;
import by.tsvrko.manics.model.hibernate.User;
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
 * Created by tsvrko on 11/22/2016.
 */
@Repository("userDao")
public class UserDAOImpl implements UserDAO {

    private static Logger log = Logger.getLogger(UserDAOImpl.class.getName());

    @Autowired
    private SessionFactory sessionFactory;
    private Session openSession() {
        return sessionFactory.openSession();
    }



    @Override
    public User getUserByLogin(String login)  {

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

}
