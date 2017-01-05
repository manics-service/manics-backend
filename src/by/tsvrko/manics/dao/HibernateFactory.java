package by.tsvrko.manics.dao;

import by.tsvrko.manics.dao.implementations.SessionDAOImpl;
import by.tsvrko.manics.dao.implementations.UserDAOImpl;
import by.tsvrko.manics.dao.interfaces.SessionDAO;
import by.tsvrko.manics.dao.interfaces.UserDAO;

/**
 * Created by irats on 1/5/2017.
 */
public class HibernateFactory {


        private static UserDAO userDAO = null;
        private static SessionDAO sessionDAO = null;
        private static HibernateFactory instance = null;

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
