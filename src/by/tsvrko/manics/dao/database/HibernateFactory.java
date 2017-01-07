package by.tsvrko.manics.dao.database;

import by.tsvrko.manics.dao.database.implementations.SessionDAOImpl;
import by.tsvrko.manics.dao.database.implementations.UserDAOImpl;
import by.tsvrko.manics.dao.database.interfaces.SessionDAO;
import by.tsvrko.manics.dao.database.interfaces.UserDAO;

/**
 * Created by irats on 1/5/2017.
 */
public class HibernateFactory {

    private static HibernateFactory instance = null;
    private static UserDAO userDAO = null;
    private static SessionDAO sessionDAO = null;

    public static synchronized HibernateFactory getInstance() {
        if (instance == null) {
            instance = new HibernateFactory();
        }
        return instance;
    }

    public UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }

    public SessionDAO getSessionDAO() {
        if (sessionDAO == null) {
            sessionDAO = new SessionDAOImpl();
        }
        return sessionDAO;
    }

}
