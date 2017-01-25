package by.tsvrko.manics.dao.database.implementations;

import by.tsvrko.manics.dao.database.interfaces.SessionDAO;
import by.tsvrko.manics.model.hibernate.User;
import by.tsvrko.manics.model.hibernate.UserSession;
import by.tsvrko.manics.service.services.dao.db.UserService;
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

@Repository("sessionDAO")
public class SessionDAOImpl implements SessionDAO {

    private static Logger log = Logger.getLogger(UserDAOImpl.class.getName());


    @Autowired
    private SessionFactory sessionFactory;
    private Session openSession() {
        return sessionFactory.openSession();
    }
    @Autowired
    private UserService userService;

    @Override
    public boolean addUserSession(String session_id, User newUser) {

        Session session = null;

        try {

            session = openSession();
            session.beginTransaction();
            User user = userService.getUserByLogin(newUser.getLogin());
            UserSession userSession = user.getUserSession();
            if(userSession==null){
                userSession = new UserSession();
                userSession.setUser(user);
            }
                userSession.setSession(session_id);
                session.saveOrUpdate(userSession);

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
    public UserSession getUserSessionByToken(String token) {
        Session session = null;
        UserSession userSession = new UserSession();
        try {
            session = openSession();
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserSession> criteria = builder.createQuery(UserSession.class);
            Root<UserSession> from = criteria.from(UserSession.class);

            criteria.select(from);
            criteria.where(builder.equal(from.get("session"),token));

            userSession = session.createQuery(criteria).getSingleResult();
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
        return userSession;
    }
}
