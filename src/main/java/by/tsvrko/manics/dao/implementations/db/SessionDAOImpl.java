package by.tsvrko.manics.dao.implementations.db;

import by.tsvrko.manics.dao.interfaces.db.SessionDAO;
import by.tsvrko.manics.model.hibernate.User;
import by.tsvrko.manics.model.hibernate.UserSession;
import by.tsvrko.manics.service.interfaces.db.UserService;
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

@Repository
public class SessionDAOImpl implements SessionDAO {

    private SessionFactory sessionFactory;
   private UserService userService;


    @Autowired
    public SessionDAOImpl(SessionFactory sessionFactory, UserService userService) {
        this.sessionFactory = sessionFactory;
        this.userService = userService;
    }

    private Session openSession() {
        return sessionFactory.openSession();
    }
    private static Logger log = Logger.getLogger(SessionDAOImpl.class.getName());

    @Override
    public boolean addUserSession(String session_id, String userId) {

        Session session = null;

        try {

            session = openSession();
            session.beginTransaction();
            User user = userService.getUserByIdentifier(userId);
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
    public UserSession getSession(String userSession) {
        Session session = null;
        UserSession dbUserSession = new UserSession();
        try {
            session = openSession();
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserSession> criteria = builder.createQuery(UserSession.class);
            Root<UserSession> from = criteria.from(UserSession.class);

            criteria.select(from);
            criteria.where(builder.equal(from.get("session"),userSession));

            dbUserSession = session.createQuery(criteria).getSingleResult();
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
        return dbUserSession;
    }
}
