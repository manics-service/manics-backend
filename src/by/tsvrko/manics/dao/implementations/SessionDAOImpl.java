package by.tsvrko.manics.dao.implementations;

import by.tsvrko.manics.dao.HibernateUtil;
import by.tsvrko.manics.dao.interfaces.SessionDAO;
import by.tsvrko.manics.model.User;
import by.tsvrko.manics.model.UserSession;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class SessionDAOImpl implements SessionDAO{
    private static Logger log = Logger.getLogger(UserDAOImpl.class.getName());

    @Override
    public boolean addUserSession(String session_id, String login) {

        Session session = null;
        UserSession userSession = new UserSession();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            User user = new UserDAOImpl().getUser(login);
            userSession.setSession_id(session_id);
            userSession.setUser(user);
            session.saveOrUpdate(userSession);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            log.debug("can't add user to DB", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return true;
    }
}
