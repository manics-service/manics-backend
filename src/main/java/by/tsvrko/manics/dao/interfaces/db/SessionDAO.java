package main.java.by.tsvrko.manics.dao.interfaces.db;

import main.java.by.tsvrko.manics.model.hibernate.UserSession;

/**
 * Created main.main.java.by tsvrko on 11/30/2016.
 */
public interface SessionDAO {

    boolean addUserSession (String session,String userId);

    UserSession getSession(String session);

    }
