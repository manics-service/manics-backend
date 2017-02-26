package by.tsvrko.manics.dao.interfaces.db;

import by.tsvrko.manics.model.hibernate.UserSession;

/**
 * Created main.by tsvrko on 11/30/2016.
 */
public interface SessionDAO {

    boolean addUserSession (String session);

    UserSession getSession(String session);

    }
