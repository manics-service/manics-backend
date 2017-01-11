package by.tsvrko.manics.dao.database.interfaces;

import by.tsvrko.manics.model.UserSession;

/**
 * Created by tsvrko on 11/30/2016.
 */
public interface SessionDAO {

    boolean addUserSession (String session_id, String login);

    UserSession getUserSessionByToken(String token);

    }
